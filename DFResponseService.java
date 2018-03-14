package com.squarepolka.dialogflow;


import com.squarepolka.dialogflow.handler.DFDefaultSpeechHandler;
import com.squarepolka.dialogflow.handler.DFSpeechHandler;
import com.squarepolka.dialogflow.handler.DFSpeechInput;
import com.squarepolka.dialogflow.request.DFRequest;
import com.squarepolka.dialogflow.response.DFResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DFResponseService<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DFResponseService.class);

    @Autowired
    private List<DFSpeechHandler<T>> speechHandlers;

    @Autowired
    private DFDefaultSpeechHandler<T> defaultSpeechHandler;

    public DFResponse getResponseForRequest(DFRequest request) {
        return getResponseForRequest(request, null);
    }

    public DFResponse getResponseForRequest(DFRequest request, T inputDataOrNul) {

        // Assume a default speech handler unless overridden
        DFSpeechHandler<T> selectedSpeechHandler = defaultSpeechHandler;
        DFSpeechInput speechInput = new DFSpeechInput();

        try {
            // Create a simple speech input object
            speechInput = new DFSpeechInput(request.result.metadata.intentName, request.originalRequest.data.user.userId);

            LOGGER.debug("Looking for a speech handler for the intent " + speechInput.intentNameOrNull + " for userID " + speechInput.userIdOrNull);
            // Find a speech handler
            for (DFSpeechHandler<T> speechHandler : speechHandlers) {
                if (speechHandler.getIntentName().equalsIgnoreCase(speechInput.intentNameOrNull)) {
                    selectedSpeechHandler = speechHandler;
                    LOGGER.debug("Found a speech handler for the intent " + speechHandler.getIntentName() + " with class " + speechHandler.getClass().getName());
                }
            }

        } catch (NullPointerException ex) {
            LOGGER.error("While trying to find a speech handler caught " + ex.getMessage());
        }

        String speechResponse = selectedSpeechHandler.getSpeech(speechInput, inputDataOrNul);
        DFResponse dfResponse = new DFResponse(speechResponse);
        return dfResponse;
    }
}
