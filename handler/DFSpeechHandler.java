package com.squarepolka.com.dialogflow.handler;

public interface DFSpeechHandler<T> {

    public abstract String getIntentName();
    public abstract String getSpeech(DFSpeechInput speechInput, T inputData);

}
