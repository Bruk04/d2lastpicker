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

    private List<PlayerHeroData> playerHeroDataMaker2()
    {
        List<PlayerHeroData> playerHeroDataList = new ArrayList<>();

        //carries
        playerHeroDataList.add(new PlayerHeroData("1", 50, 35)); //.7 // should be picked
        playerHeroDataList.add(new PlayerHeroData("10", 125, 85)); //.68 // should be picked
        playerHeroDataList.add(new PlayerHeroData("46", 30, 20)); //.6667
        playerHeroDataList.add(new PlayerHeroData("102", 50, 33)); // .66
        playerHeroDataList.add(new PlayerHeroData("95", 0, 0)); // 0.0 shouldnt be picked
        playerHeroDataList.add(new PlayerHeroData("109", 5, 4)); // .8 shouldnt be picked
        playerHeroDataList.add(new PlayerHeroData("53", 100, 90)); // .90 // should be picked





        //mids
        playerHeroDataList.add(new PlayerHeroData("19", 192, 116)); //.604
        playerHeroDataList.add(new PlayerHeroData("13", 80, 59)); //.7375
        playerHeroDataList.add(new PlayerHeroData("52", 78, 45 )); //.57
        playerHeroDataList.add(new PlayerHeroData("35", 0, 0)); // 0.0 shouldnt be picked
        playerHeroDataList.add(new PlayerHeroData("23", 90, 65)); // .722
        playerHeroDataList.add(new PlayerHeroData("61", 6, 6)); // 1.0 shouldnt be picked




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
                heroMatchupDataList.add(new HeroMatchupData(2, 80, 45 )); //.0625
                heroMatchupDataList.add(new HeroMatchupData(4, 45, 23)); // .0111
                heroMatchupDataList.add(new HeroMatchupData(6, 30, 18)); // .1
                heroMatchupDataList.add(new HeroMatchupData(8, 90, 55)); // .1111
                heroMatchupDataList.add(new HeroMatchupData(12, 20, 15)); // .25
                //53.46%
            }

            case 10:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 20, 5)); // -.25
                heroMatchupDataList.add(new HeroMatchupData(4, 100, 43)); // -.07
                heroMatchupDataList.add(new HeroMatchupData(6, 180, 82)); // /-.0444
                heroMatchupDataList.add(new HeroMatchupData(8, 60, 20)); // -.1666
                heroMatchupDataList.add(new HeroMatchupData(12, 20, 5)); // -.25
                //-78.1%
            }

            case 46:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 40, 25)); // .125
                heroMatchupDataList.add(new HeroMatchupData(4, 10, 4)); // -.1
                heroMatchupDataList.add(new HeroMatchupData(6, 90, 40)); // -.0555
                heroMatchupDataList.add(new HeroMatchupData(8, 36, 20)); // .05555
                heroMatchupDataList.add(new HeroMatchupData(12, 100, 40)); // -.1
                //-7.495%
            }

            case 102:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 50, 35)); // .2
                heroMatchupDataList.add(new HeroMatchupData(4, 23, 12)); // .0217
                heroMatchupDataList.add(new HeroMatchupData(6, 66, 30)); // -.0454
                heroMatchupDataList.add(new HeroMatchupData(8, 45, 15)); // -.1666
                heroMatchupDataList.add(new HeroMatchupData(12, 100, 55)); // .05
                //5.97%
            }

            case 95:

            {
                heroMatchupDataList.add(new HeroMatchupData(2, 50, 30)); // .1
                heroMatchupDataList.add(new HeroMatchupData(4, 10, 6)); // .1
                heroMatchupDataList.add(new HeroMatchupData(6, 23, 11)); // -.0217
                heroMatchupDataList.add(new HeroMatchupData(8, 50, 23)); // -.04
                heroMatchupDataList.add(new HeroMatchupData(12, 69, 40)); // .0797
                // 21.8%
            }

            case 109:

            {
                heroMatchupDataList.add(new HeroMatchupData(2, 20, 12)); // .1
                heroMatchupDataList.add(new HeroMatchupData(4, 35, 23)); // .157
                heroMatchupDataList.add(new HeroMatchupData(6, 90, 50)); // .0555
                heroMatchupDataList.add(new HeroMatchupData(8, 50, 15)); // -.2
                heroMatchupDataList.add(new HeroMatchupData(12, 20, 14)); // .2
                // 31.25%
            }


            case 53:

            {
                heroMatchupDataList.add(new HeroMatchupData(2, 50, 30)); // .1
                heroMatchupDataList.add(new HeroMatchupData(4, 10, 6)); // .1
                heroMatchupDataList.add(new HeroMatchupData(6, 23, 11)); // -.021
                heroMatchupDataList.add(new HeroMatchupData(8, 50, 23)); // -.04
                heroMatchupDataList.add(new HeroMatchupData(12, 69, 40)); // .0797
                //21.87%
            }


            case 13:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 69, 30)); // -.065
                heroMatchupDataList.add(new HeroMatchupData(4, 40, 23)); // -.04
                heroMatchupDataList.add(new HeroMatchupData(6, 88, 37)); // -.079
                heroMatchupDataList.add(new HeroMatchupData(8, 115 , 73)); // .1347
                heroMatchupDataList.add(new HeroMatchupData(12, 250, 130)); // .02
                // -2.93%
            }

            case 19:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 108, 50)); // -.037
                heroMatchupDataList.add(new HeroMatchupData(4, 103, 60)); // .0825
                heroMatchupDataList.add(new HeroMatchupData(6, 55, 23)); // -.0818
                heroMatchupDataList.add(new HeroMatchupData(8, 182, 99)); // .0439
                heroMatchupDataList.add(new HeroMatchupData(12, 123, 66)); // .036
                //4.36%
            }

            case 52:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 40, 15)); // -.125
                heroMatchupDataList.add(new HeroMatchupData(4, 116, 70)); // .103
                heroMatchupDataList.add(new HeroMatchupData(6, 100, 56)); // .06
                heroMatchupDataList.add(new HeroMatchupData(8, 32, 15)); // -.031
                heroMatchupDataList.add(new HeroMatchupData(12, 70, 32)); // -.0428
                //-3.58%
            }

            case 35:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 23, 10)); // -.065
                heroMatchupDataList.add(new HeroMatchupData(4, 60, 40)); // .166
                heroMatchupDataList.add(new HeroMatchupData(6, 55, 23)); // -.0818
                heroMatchupDataList.add(new HeroMatchupData(8, 109, 66)); // .105
                heroMatchupDataList.add(new HeroMatchupData(12, 10, 6)); // .1
                //22.42%
            }

            case 23:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 90, 50)); // .055
                heroMatchupDataList.add(new HeroMatchupData(4, 20, 11)); // .05
                heroMatchupDataList.add(new HeroMatchupData(6, 35, 23)); // .157
                heroMatchupDataList.add(new HeroMatchupData(8, 230, 120)); // .0217
                heroMatchupDataList.add(new HeroMatchupData(12, 500, 290)); // .08
                //36.37%
            }

            case 61:
            {
                heroMatchupDataList.add(new HeroMatchupData(2, 50, 29)); // .08
                heroMatchupDataList.add(new HeroMatchupData(4, 88, 47)); // .034
                heroMatchupDataList.add(new HeroMatchupData(6, 70, 30)); // -.071
                heroMatchupDataList.add(new HeroMatchupData(8, 19, 9)); // -.026
                heroMatchupDataList.add(new HeroMatchupData(12, 15, 8)); // .033
                //5%
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
    public void calculateMatchup_works_as_expected2() {
        Dota2APIService mockDota2APIService = Mockito.mock(Dota2APIService.class);
        Dota2MatchupServiceImpl matchupService = new Dota2MatchupServiceImpl(mockDota2APIService);
        List<Integer> us = listMaker(4);
        List<Integer> them = listMaker(5);

        // prep mock
        List<PlayerHeroData> playerHeroDataList = playerHeroDataMaker2();
        Mockito.when(mockDota2APIService.getPlayerHeroData(VALID_PLAYER_ID)).thenReturn(playerHeroDataList);
        Mockito.when(mockDota2APIService.getHeroMatchupData(1)).thenReturn(heroWinrateDataMaker(1));
        Mockito.when(mockDota2APIService.getHeroMatchupData(10)).thenReturn(heroWinrateDataMaker(10));
        Mockito.when(mockDota2APIService.getHeroMatchupData(46)).thenReturn(heroWinrateDataMaker(46));
        Mockito.when(mockDota2APIService.getHeroMatchupData(102)).thenReturn(heroWinrateDataMaker(102));
        Mockito.when(mockDota2APIService.getHeroMatchupData(95)).thenReturn(heroWinrateDataMaker(95));
        Mockito.when(mockDota2APIService.getHeroMatchupData(109)).thenReturn(heroWinrateDataMaker(109));
        Mockito.when(mockDota2APIService.getHeroMatchupData(53)).thenReturn(heroWinrateDataMaker(53));


        Mockito.when(mockDota2APIService.getHeroMatchupData(13)).thenReturn(heroWinrateDataMaker(13));
        Mockito.when(mockDota2APIService.getHeroMatchupData(19)).thenReturn(heroWinrateDataMaker(19));
        Mockito.when(mockDota2APIService.getHeroMatchupData(52)).thenReturn(heroWinrateDataMaker(52));
        Mockito.when(mockDota2APIService.getHeroMatchupData(35)).thenReturn(heroWinrateDataMaker(35));
        Mockito.when(mockDota2APIService.getHeroMatchupData(23)).thenReturn(heroWinrateDataMaker(23));
        Mockito.when(mockDota2APIService.getHeroMatchupData(61)).thenReturn(heroWinrateDataMaker(61));

        // test the method
        try {
            List<HeroWinrateData> data = matchupService.calculateMatchup(VALID_PLAYER_ID, us, them);
            assert data.size() == 6;
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
