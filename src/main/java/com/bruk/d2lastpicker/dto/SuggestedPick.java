package com.bruk.d2lastpicker.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class SuggestedPick {
    private List<HeroData> suggestedPicks;
    public SuggestedPick() {
    }

    @JsonGetter
    public List<HeroData> getSuggestedPicks() {
        return suggestedPicks;
    }

    @JsonGetter
    public void setSuggestedPicks(List<HeroData> suggestedPicks) {
        this.suggestedPicks = suggestedPicks;
    }
}
