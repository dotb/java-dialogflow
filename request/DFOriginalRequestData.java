package com.squarepolka.dialogflow.request;


import java.util.List;
import java.util.Map;

public class DFOriginalRequestData {
    public Boolean isInSandbox;
    public Map surface;
    public Map conversation;
    public List availableSurfaces;
    public List inputs;
    public DFUser user;
}