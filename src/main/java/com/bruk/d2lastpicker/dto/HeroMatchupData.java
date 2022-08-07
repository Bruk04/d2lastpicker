package com.bruk.d2lastpicker.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class HeroMatchupData {

    private long hero_id = 0;
    private double games_played = 0;
    private double wins = 0;

    public HeroMatchupData() {
    }

    public HeroMatchupData(long hero_id, double games_played, double wins) {
        this.hero_id = hero_id;
        this.games_played = games_played;
        this.wins = wins;
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
    public double getGames_played() {
        return games_played;
    }

    @JsonSetter

    public void setGames_played(double games_played) {
        this.games_played = games_played;
    }

    @JsonGetter
    public double getWins() {
        return wins;
    }

    @JsonSetter
    public void setWins(double wins) {
        this.wins = wins;
    }
}
