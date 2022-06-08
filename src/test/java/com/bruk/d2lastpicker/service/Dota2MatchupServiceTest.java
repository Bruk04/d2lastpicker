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
            fail("PlayerID is negative, when it should be positive");
        } catch(Exception e) {
        }
    }

    @Test
    public void should_throw_on_large_us_list()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(5);
        List<Integer> them = listMaker(5);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("The us team is too large, should be 4 heroes");
        } catch(Exception e) {
        }
    }

    @Test
    public void should_throw_on_small_us_list()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(3);
        List<Integer> them = listMaker(5);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("The us team is too small, should be 4 heroes");
        } catch(Exception e) {
        }
    }

    @Test
    public void should_throw_on_large_them_list()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(6);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("The them team is too large, should be 5 heroes");
        } catch(Exception e) {
        }
    }

    @Test
    public void should_throw_on_small_them_list()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(4);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("The them team is too small, should be 5 heroes");
        } catch(Exception e) {
        }
    }

    @Test
    public void should_throw_on_bad_hero_id_us()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);
        us.remove(4);
        us.add(-32);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("The us team has a bad hero ID");
        } catch(Exception e) {
        }
    }

    @Test
    public void should_throw_on_bad_hero_id_them()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);
        them.remove(5);
        them.add(180);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("The them team has a bad hero ID");
        } catch(Exception e) {
        }
    }

    @Test
    public void should_throw_on_duplicate_list()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);
        us.remove(4);
        them.remove(5);
        us.add(4, 32);
        them.add(4, 32);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("There exists a duplicate hero ID between the teams");
        } catch(Exception e) {
        }
    }



}
