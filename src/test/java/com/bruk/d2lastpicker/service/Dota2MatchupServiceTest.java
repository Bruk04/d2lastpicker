package com.bruk.d2lastpicker.service;


import com.bruk.d2lastpicker.service.Dota2MatchupServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;


public class Dota2MatchupServiceTest {

    private static final int VALID_PLAYER_ID = 98359366;

    private List<Integer> listMaker(int listLength) {
        List<Integer> newList = new ArrayList<>();
        if (listLength == 4) {
            newList.add(1);
            newList.add(3);
            newList.add(5);
            newList.add(9);
        } else {
            newList.add(2);
            newList.add(4);
            newList.add(6);
            newList.add(8);
            newList.add(10);
        }
        return newList;
    }

    @Test
    public void should_accept_valid_input()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);

        } catch(Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void should_throw_on_bad_playerid()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);
        try {
            matchupService.calculateMatchup(-31312, us, them);
            fail("Unexpected exception");

        } catch(Exception e) {

        }
    }


}
