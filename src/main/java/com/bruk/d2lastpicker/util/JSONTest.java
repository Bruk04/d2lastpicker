package com.bruk.d2lastpicker.util;

import com.fasterxml.jackson.annotation.JsonGetter;

public class JSONTest {

    private String testString = "This is a test string";
    private int testInt = 5;
    private boolean testBool = true;

    public JSONTest(String testString, int testInt, boolean testBool) {
        this.testString = testString;
        this.testInt = testInt;
        this.testBool = testBool;
    }

    public JSONTest() {
    }

    @JsonGetter
    public String getTestString() {
        return testString;
    }

    @JsonGetter
    public int getTestInt() {
        return testInt;
    }

    @JsonGetter
    public boolean isTestBool() {
        return testBool;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public void setTestInt(int testInt) {
        this.testInt = testInt;
    }

    public void setTestBool(boolean testBool) {
        this.testBool = testBool;
    }

}

