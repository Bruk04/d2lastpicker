package com.bruk.d2lastpicker.service;

import com.bruk.d2lastpicker.util.HeroWinrateData;

import java.util.List;

public interface Dota2MatchupService {

    public List<HeroWinrateData> calculateMatchup(long playerId, List<Integer> us, List<Integer> them);

}
