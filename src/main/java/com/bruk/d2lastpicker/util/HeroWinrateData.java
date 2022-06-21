package com.bruk.d2lastpicker.util;

public class HeroWinrateData {

    private double winrate;
    private int heroId;
    private String heroName;

    public HeroWinrateData() {
    }

    public HeroWinrateData(double winrate, int heroId, String heroName) {
        this.winrate = winrate;
        this.heroId = heroId;
        this.heroName = heroName;
    }


    public double getWinrate() {
        return winrate;
    }

    public void setWinrate(double winrate) {
        this.winrate = winrate;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
