package com.bruk.d2lastpicker.service;

import com.bruk.d2lastpicker.dto.HeroData;
import com.bruk.d2lastpicker.dto.HeroMatchupData;

import java.util.List;

public interface Dota2MatchupService {

    public List<HeroMatchupData> calculateMatchupService(long id);

}
