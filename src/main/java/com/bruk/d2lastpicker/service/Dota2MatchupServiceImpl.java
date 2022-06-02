package com.bruk.d2lastpicker.service;

import com.bruk.d2lastpicker.dto.HeroData;
import com.bruk.d2lastpicker.dto.HeroMatchupData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Dota2MatchupServiceImpl implements Dota2MatchupService {

    private Dota2APIService APIService;

    @Autowired
    public Dota2MatchupServiceImpl(Dota2APIService APIService) {
        this.APIService = APIService;
    }

    public List<HeroData> calculateMatchup(long playerId, List<Integer> us, List<Integer> them)
    {
        try
        {
            return new ArrayList<HeroData>();
        } catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
