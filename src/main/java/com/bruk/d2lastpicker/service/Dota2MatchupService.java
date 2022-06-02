package com.bruk.d2lastpicker.service;

import com.bruk.d2lastpicker.dto.HeroData;

import java.util.List;

public interface Dota2MatchupService {

    public List<HeroData> calculateMatchup(long playerId, List<Integer> us, List<Integer> them);

}
