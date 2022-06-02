package com.bruk.d2lastpicker.dto;

import java.util.List;

public class SuggestedPick {

    // hero IDs for the front end to work with and know
    private long hero_id = 0;

    private long suggestedHeroID = 0;

    // since I will have calculated the matchups for both teams in the MatchupService
    // I can just return a single list that houses both the matchups for our team and the other team
    private List<HeroMatchupData> bothTeams;

    // the front end needs to have all the hero IDs and their names to display to the user
    private HeroData allHeroData;



}
