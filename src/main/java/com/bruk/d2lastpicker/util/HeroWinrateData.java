package com.bruk.d2lastpicker.util;

import com.bruk.d2lastpicker.dto.HeroData;

public class HeroWinrateData implements Comparable<HeroWinrateData> {

    private double winrate;
    private int heroId;

    public int getEnemyHeroId() {
        return enemyHeroId;
    }

    public void setEnemyHeroId(int enemyHeroId) {
        this.enemyHeroId = enemyHeroId;
    }

    private int enemyHeroId;
    private String heroName;

    public boolean isCarry() {
        return isCarry;
    }

    public void setCarry(boolean carry) {
        isCarry = carry;
    }

    public boolean isMid() {
        return isMid;
    }

    public void setMid(boolean mid) {
        isMid = mid;
    }

    private boolean isCarry;

    private boolean isMid;

    public HeroWinrateData() {
    }

    public HeroWinrateData(double winrate, int heroId, String heroName) {
        this.winrate = winrate;
        this.heroId = heroId;
        this.heroName = heroName;
    }

    public HeroWinrateData(double winrate, int heroId, String heroName, int enemyHeroId)

    {
        this.winrate = winrate;
        this.heroId = heroId;
        this.heroName = heroName;
        this.enemyHeroId = enemyHeroId;
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