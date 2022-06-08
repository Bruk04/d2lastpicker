package com.bruk.d2lastpicker.service;

import com.bruk.d2lastpicker.dto.HeroData;
import com.bruk.d2lastpicker.dto.HeroMatchupData;
import com.bruk.d2lastpicker.util.D2LastPickerValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class Dota2MatchupServiceImpl implements Dota2MatchupService {

    private static final int STEAM_32_ID = 8;
    private static final int MAXIMUM_HERO_ID = 137;
    private static final int EXPECTED_US_LIST_LENGTH = 4;
    private static final int EXPECTED_THEM_LIST_LENGTH = 5;


    private Dota2APIService APIService;


    @Autowired
    public Dota2MatchupServiceImpl(Dota2APIService APIService) {
        this.APIService = APIService;
    }
    public Dota2MatchupServiceImpl() {}

    public List<HeroData> calculateMatchup(long playerID, List<Integer> us, List<Integer> them)
    {
        validatePlayerID(playerID);
        validateHeroIDs(us, EXPECTED_US_LIST_LENGTH);
        validateHeroIDs(them, EXPECTED_THEM_LIST_LENGTH);
        return new ArrayList<HeroData>();
    }


    private void validatePlayerID(long playerID)
    {
        if(playerID < 0)
        {
            String s = "Player ID was negative";
            throw new D2LastPickerValidationException(s);
        }
    }

    private void validateHeroIDs(List<Integer> heroList, int expectedLength) {

        if(heroList.size() != expectedLength)
        {
            String s = String.format("The expected number of heroes was %d but you supplied %d", expectedLength, heroList.size());
            throw new D2LastPickerValidationException(s);
        }

        for(int i = 0; i < heroList.size(); i++)
        {
            int currentEntry = heroList.get(i);
            if(currentEntry > MAXIMUM_HERO_ID || currentEntry < 0)
            {
                String s = String.format("Hero ID %d is out of bounds in the hero list", i);
                throw new D2LastPickerValidationException(s);
            }
        }

        // test for duplicate
        Set<Integer> hset = new HashSet<>();
        for(Integer i : heroList)
        {
            if(hset.add(i) == false)
            {
                String s = String.format("Hero ID %d is a duplicate in the hero list", i);
                throw new D2LastPickerValidationException(s);
            }
            else {
                hset.add(i);

            }
        }
        }
    }
