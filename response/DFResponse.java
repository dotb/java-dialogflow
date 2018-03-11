package com.squarepolka.com.dialogflow.response;

public class DFResponse {
    public String speech;
    public String displayText;

    public DFResponse(String message) {
        this.speech = message;
        this.displayText = message;
    }
}
