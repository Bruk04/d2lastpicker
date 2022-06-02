package com.bruk.d2lastpicker.service;

import com.bruk.d2lastpicker.dto.HeroData;
import com.bruk.d2lastpicker.dto.HeroMatchupData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Dota2MatchupServiceImpl {

    private Dota2APIService APIService;

    @Autowired
    public Dota2MatchupServiceImpl(Dota2APIService APIService) {
        this.APIService = APIService;
    }

    // function is void for now, more to do with it later.
    public void calculateMatchup(long id, List<HeroData> us, List<HeroData> them)
    {
        try
        {
            APIService.getHeroData();
            APIService.getPlayerHeroData(id);
            APIService.getHeroMatchupData(id);
            return;
        } catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
