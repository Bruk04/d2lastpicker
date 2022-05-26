package com.bruk.d2lastpicker.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class PlayerHeroData {

    private String hero_id = "string";
    private int games = 0;
    private int win = 0;

    public PlayerHeroData() {

    }

    @JsonGetter
    public String getHero_id() {
        return hero_id;
    }

    @JsonSetter
    public void setHero_id(String hero_id) {
        this.hero_id = hero_id;
    }

    @JsonGetter
    public int getGames() {
        return games;
    }

    @JsonSetter
    public void setGames(int games) {
        this.games = games;
    }

    @JsonGetter
    public int getWin() {
        return win;
    }

    @JsonSetter
    public void setWin(int win) {
        this.win = win;
    }
}
