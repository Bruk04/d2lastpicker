package com.bruk.d2lastpicker.util;

public class ControllerError {

    private String errorMessage;

    public ControllerError(Exception e){
        errorMessage = e.toString();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

