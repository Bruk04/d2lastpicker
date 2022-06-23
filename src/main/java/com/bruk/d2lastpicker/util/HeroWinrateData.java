package com.bruk.d2lastpicker.util;

import com.bruk.d2lastpicker.dto.HeroData;

public class HeroWinrateData implements Comparable<HeroWinrateData> {

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

    @Override
    public int compareTo(HeroWinrateData o) {
        double otherWinrate = o.winrate;

        if (winrate == otherWinrate) {
            return 0;
        }
        if (winrate > otherWinrate) {
            return 1;
        } else {
            return -1;
        }

    }
}