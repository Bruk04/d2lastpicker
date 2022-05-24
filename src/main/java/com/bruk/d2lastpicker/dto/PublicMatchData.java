package com.bruk.d2lastpicker.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;


public class PublicMatchData {

    private long match_id = 0;
    private long match_seq_num = 0;
    private boolean radiant_win = true;
    private long start_time = 0;
    private long duration = 0;



    private long avg_mmr = 0;
    String radiant_team = "Radiant";
    String dire_team = "Dire";


    public PublicMatchData() {

    }

    @JsonGetter
    public long getAvg_mmr() {
        return avg_mmr;
    }

    @JsonSetter
    public void setAvg_mmr(long avg_mmr) {
        this.avg_mmr = avg_mmr;
    }

    @JsonGetter
    public long getMatch_id() {
        return match_id;
    }

    @JsonSetter
    public void setMatch_id(long match_id) {
        this.match_id = match_id;
    }

    @JsonGetter
    public long getMatch_seq_num() {
        return match_seq_num;
    }

    @JsonSetter
    public void setMatch_seq_num(long match_seq_num) {
        this.match_seq_num = match_seq_num;
    }

    @JsonGetter
    public boolean isRadiant_win() {
        return radiant_win;
    }

    @JsonSetter
    public void setRadiant_win(boolean radiant_win) {
        this.radiant_win = radiant_win;
    }

    @JsonGetter
    public long getStart_time() {
        return start_time;
    }

    @JsonSetter
    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    @JsonGetter
    public long getDuration() {
        return duration;
    }

    @JsonSetter
    public void setDuration(long duration) {
        this.duration = duration;
    }

    @JsonGetter
    public String getRadiant_team() {
        return radiant_team;
    }

    @JsonSetter
    public void setRadiant_team(String radiant_team) {
        this.radiant_team = radiant_team;
    }

    @JsonGetter
    public String getDire_team() {
        return dire_team;
    }

    @JsonSetter
    public void setDire_team(String dire_team) {
        this.dire_team = dire_team;
    }

    @Override
    public String toString() {
        return "CallTest{" +
                "match_id=" + match_id +
                ", match_seq_num=" + match_seq_num +
                ", radiant_win=" + radiant_win +
                ", start_time=" + start_time +
                ", duration=" + duration +
                ", avg_mmr=" + avg_mmr +
                ", radiant_team='" + radiant_team + '\'' +
                ", dire_team='" + dire_team + '\'' +
                '}';
    }
}
