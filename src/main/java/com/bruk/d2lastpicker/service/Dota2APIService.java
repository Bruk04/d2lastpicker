package com.bruk.d2lastpicker.service;

import com.bruk.d2lastpicker.dto.HeroData;
import com.bruk.d2lastpicker.dto.PlayerHeroData;

import java.util.List;

public interface Dota2APIService {
    public List<PlayerHeroData> getPlayerHeroData(long id);
    public List<HeroData> getHeroData();
}