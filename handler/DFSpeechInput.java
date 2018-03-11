package com.squarepolka.com.dialogflow.handler;


public class DFSpeechInput {
    public String userIdOrNull;
    public String intentNameOrNull;

    public DFSpeechInput() {
        this.userIdOrNull = null;
        this.intentNameOrNull = null;
    }

    public DFSpeechInput(String intentName, String userId) {
        this.intentNameOrNull = intentName;
        this.userIdOrNull = userId;
    }
}
