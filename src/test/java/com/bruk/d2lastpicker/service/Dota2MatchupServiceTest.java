package com.bruk.d2lastpicker.service;


import com.bruk.d2lastpicker.dto.HeroMatchupData;
import com.bruk.d2lastpicker.dto.PlayerHeroData;
import com.bruk.d2lastpicker.service.Dota2MatchupServiceImpl;
import com.bruk.d2lastpicker.util.HeroWinrateData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;


@RunWith(MockitoJUnitRunner.class)
public class Dota2MatchupServiceTest {

    private static final int VALID_PLAYER_ID = 98359366;

    private List<Integer> listMaker(int listLength) {
        List<Integer> newList = new ArrayList<>();
        if (listLength == 4) {
            newList.add(30);
            newList.add(3);
            newList.add(5);
            newList.add(9);
        } else {
            newList.add(2);
            newList.add(4);
            newList.add(6);
            newList.add(8);
            newList.add(12);
        }
        return newList;
    }

    private List<PlayerHeroData> playerHeroDataMaker()
    {
        List<PlayerHeroData> playerHeroDataList = new ArrayList<>();

        //carries
        playerHeroDataList.add(new PlayerHeroData("1", 50, 35)); //.7
        playerHeroDataList.add(new PlayerHeroData("10", 125, 85)); //.68
        playerHeroDataList.add(new PlayerHeroData("46", 30, 20)); //.6667

        //mids
        playerHeroDataList.add(new PlayerHeroData("19", 192, 116)); //.604
        playerHeroDataList.add(new PlayerHeroData("13", 80, 59)); //.7375
        playerHeroDataList.add(new PlayerHeroData("52", 78, 45 )); //.57

        return playerHeroDataList;
    }

    private List<HeroMatchupData> heroWinrateDataMaker(long heroid)
    {
        List<HeroMatchupData> heroMatchupDataList = new ArrayList<>();
        int id = (int)heroid;
        switch(id)
        {
            case 1:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 80, 45 ));
                heroMatchupDataList.add(new HeroMatchupData(4, 45, 23));
                heroMatchupDataList.add(new HeroMatchupData(6, 30, 18));
                heroMatchupDataList.add(new HeroMatchupData(8, 90, 55));
                heroMatchupDataList.add(new HeroMatchupData(12, 20, 15));
            }

            case 10:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 20, 5));
                heroMatchupDataList.add(new HeroMatchupData(4, 100, 43));
                heroMatchupDataList.add(new HeroMatchupData(6, 180, 82));
                heroMatchupDataList.add(new HeroMatchupData(8, 60, 20));
                heroMatchupDataList.add(new HeroMatchupData(12, 20, 5));
            }

            case 46:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 40, 25));
                heroMatchupDataList.add(new HeroMatchupData(4, 10, 4));
                heroMatchupDataList.add(new HeroMatchupData(6, 90, 40));
                heroMatchupDataList.add(new HeroMatchupData(8, 36, 20));
                heroMatchupDataList.add(new HeroMatchupData(12, 100, 40));
            }

            case 13:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 69, 30));
                heroMatchupDataList.add(new HeroMatchupData(4, 40, 23));
                heroMatchupDataList.add(new HeroMatchupData(6, 88, 37));
                heroMatchupDataList.add(new HeroMatchupData(8, 115 , 73));
                heroMatchupDataList.add(new HeroMatchupData(12, 250, 130));
            }

            case 19:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 108, 50));
                heroMatchupDataList.add(new HeroMatchupData(4, 103, 60));
                heroMatchupDataList.add(new HeroMatchupData(6, 55, 23));
                heroMatchupDataList.add(new HeroMatchupData(8, 182, 99));
                heroMatchupDataList.add(new HeroMatchupData(12, 123, 66));
            }

            case 52:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 40, 15));
                heroMatchupDataList.add(new HeroMatchupData(4, 116, 70));
                heroMatchupDataList.add(new HeroMatchupData(6, 100, 56));
                heroMatchupDataList.add(new HeroMatchupData(8, 32, 15));
                heroMatchupDataList.add(new HeroMatchupData(12, 70, 32));
            }
        }


        return heroMatchupDataList;
    }


    @Test
    public void calculateMatchup_works_as_expected_1()
    {
        Dota2APIService mockDota2APIService = Mockito.mock(Dota2APIService.class);
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl(mockDota2APIService);
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);

        // prep mock
        List<PlayerHeroData> playerHeroDataList = playerHeroDataMaker();
        Mockito.when(mockDota2APIService.getPlayerHeroData(VALID_PLAYER_ID)).thenReturn(playerHeroDataList);
        Mockito.when(mockDota2APIService.getHeroMatchupData(1)).thenReturn(heroWinrateDataMaker(1));
        Mockito.when(mockDota2APIService.getHeroMatchupData(10)).thenReturn(heroWinrateDataMaker(10));
        Mockito.when(mockDota2APIService.getHeroMatchupData(46)).thenReturn(heroWinrateDataMaker(46));

        Mockito.when(mockDota2APIService.getHeroMatchupData(13)).thenReturn(heroWinrateDataMaker(13));
        Mockito.when(mockDota2APIService.getHeroMatchupData(19)).thenReturn(heroWinrateDataMaker(19));
        Mockito.when(mockDota2APIService.getHeroMatchupData(52)).thenReturn(heroWinrateDataMaker(52));

        // test the method
        try {
            List<HeroWinrateData> data = matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            assert data.size() == 6;
            assert data.get(0).getWinrate() > data.get(1).getWinrate();
            assert data.get(0).getWinrate() > data.get(2).getWinrate();
            assert data.get(3).getWinrate() > data.get(4).getWinrate();
            assert data.get(3).getWinrate() > data.get(5).getWinrate();

        }
        catch(Exception e)
        {
            fail("Unexpected Exception");
        }
    }

    @Test
    public void calculateMatchup_should_throw_on_bad_playerid()
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
    public void calculateMatchup_should_throw_on_large_us_list()
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
    public void calculateMatchup_should_throw_on_small_us_list()
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
    public void calculateMatchup_should_throw_on_large_them_list()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);
        them.add(23);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("The them team is too large, should be 5 heroes");
        } catch(Exception e) {
        }
    }

    @Test
    public void calculateMatchup_should_throw_on_small_them_list()
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
    public void calculateMatchup_should_throw_on_bad_hero_id_us()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);
        us.remove(3);
        us.add(-32);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("The us team has a bad hero ID");
        } catch(Exception e) {
        }
    }

    @Test
    public void calculateMatchup_should_throw_on_bad_hero_id_them()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);
        them.remove(4);
        them.add(180);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("The them team has a bad hero ID");
        } catch(Exception e) {
        }
    }

    @Test
    public void calculateMatchup_should_throw_on_duplicate_list()
    {
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl();
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);
        us.remove(3);
        us.add(3);
        try {
            matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            fail("There exists a duplicate hero ID between the teams");
        } catch(Exception e) {
        }
    }



}
