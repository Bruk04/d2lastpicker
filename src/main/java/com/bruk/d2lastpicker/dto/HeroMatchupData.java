package com.bruk.d2lastpicker.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class HeroMatchupData {

    private long hero_id = 0;
    private long games_played = 0;
    private long wins = 0;

    public HeroMatchupData() {
    }

    @JsonGetter
    public long getHero_id() {
        return hero_id;
    }

    @JsonSetter
    public void setHero_id(long hero_id) {
        this.hero_id = hero_id;
    }

    @JsonGetter
    public long getGames_played() {
        return games_played;
    }

    @JsonSetter

    public void setGames_played(long games_played) {
        this.games_played = games_played;
    }

    @JsonGetter
    public long getWins() {
        return wins;
    }

    @JsonSetter
    public void setWins(long wins) {
        this.wins = wins;
    }
}
