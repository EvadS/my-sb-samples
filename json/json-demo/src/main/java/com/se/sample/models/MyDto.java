package com.se.sample.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MyDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stringValue;

    private int intValue;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }
}
