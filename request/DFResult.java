package com.squarepolka.com.dialogflow.request;

import java.util.List;
import java.util.Map;

public class DFResult {
    public String source;
    public String resolvedQuery;
    public String speech;
    public String action;
    public Number score;
    public Boolean actionIncomplete;
    public Map parameters;
    public Map fulfillment;
    public List contexts;
    public DFResultMetadata metadata;
}