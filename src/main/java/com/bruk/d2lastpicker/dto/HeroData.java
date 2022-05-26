package com.bruk.d2lastpicker.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class HeroData {

    private int id = 0;

    private String localized_name = "";

    public HeroData() {
    }

    @JsonGetter
    public int getId() {
        return id;
    }

    @JsonSetter
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter
    public String getLocalized_name() {
        return localized_name;
    }

    @JsonSetter
    public void setLocalized_name(String localized_name) {
        this.localized_name = localized_name;
    }

}
