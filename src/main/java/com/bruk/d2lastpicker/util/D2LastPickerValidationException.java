package com.bruk.d2lastpicker.util;

public class D2LastPickerValidationException extends RuntimeException {

    public D2LastPickerValidationException() {

    }

    public D2LastPickerValidationException(Exception e)
    {
        super(e);
    }

    public D2LastPickerValidationException(String s)
    {
        super(s);
    }


}
