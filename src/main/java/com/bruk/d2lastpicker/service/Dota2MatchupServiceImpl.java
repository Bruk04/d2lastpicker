package com.bruk.d2lastpicker.service;

import com.bruk.d2lastpicker.dto.HeroData;
import com.bruk.d2lastpicker.dto.HeroMatchupData;
import com.bruk.d2lastpicker.dto.PlayerHeroData;
import com.bruk.d2lastpicker.util.CarryHeroes;
import com.bruk.d2lastpicker.util.D2LastPickerValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final double AVERAGE_WINRATE = 50.00;

    private static final Logger LOG = LoggerFactory.getLogger(Dota2MatchupServiceImpl.class);


    private Dota2APIService APIService;
    private CarryHeroes carryHeroes;


    @Autowired
    public Dota2MatchupServiceImpl(Dota2APIService APIService) {
        this.APIService = APIService;
    }

    public Dota2MatchupServiceImpl() {
    }

    public List<HeroData> calculateMatchup(long playerID, List<Integer> us, List<Integer> them) {
        validatePlayerID(playerID);
        validateHeroIDs(us, EXPECTED_US_LIST_LENGTH);
        validateHeroIDs(them, EXPECTED_THEM_LIST_LENGTH);
        validateBothLists(us, them);
        getHeroMatchups(us, them);
        getPlayerHeroes(playerID);


        return new ArrayList<HeroData>();
    }

    private List<String> playerDataParser(long playerID)
    {
        List<PlayerHeroData> playerHeroData = APIService.getPlayerHeroData(playerID);
        List<String> allHeroIds = new ArrayList<>();
        for(PlayerHeroData playerData : playerHeroData)
        {
            String heroId = playerData.getHero_id();
            allHeroIds.add(heroId);
        }
        return allHeroIds;
    }


    private List<Double> getHeroMatchups(List<Integer> us, List<Integer> them)
    {
        long heroID = us.get(0);
        List<HeroMatchupData> heroMatchupData = APIService.getHeroMatchupData(heroID);
        List<Double> heroWinrates = new ArrayList<>();
        for(HeroMatchupData matchupData : heroMatchupData) {
            long hero = matchupData.getHero_id();
            Integer heroInt = (int)hero;
            if (us.contains(heroInt) || them.contains(heroInt)) {
                double wins = matchupData.getWins();
                double games = matchupData.getGames_played();
                double winrate = wins/games;
                LOG.debug(String.valueOf(winrate));
                heroWinrates.add(winrate);
            }
        }
        return heroWinrates;
    }

    private void getPlayerHeroes(long playerId)
    {
        List<PlayerHeroData> playerHeroData = APIService.getPlayerHeroData(playerId);
        List<HeroData> allHeroData = APIService.getHeroData();
        List<String> possiblePosOneHeroes = new ArrayList<>();
        List<String> possiblePosTwoHeroes = new ArrayList<>();
        List<Integer> posOne = new ArrayList<>();
        List<Integer> posTwo = new ArrayList<>();
        posOne.addAll(carryHeroes.getPositionOneHeroes());
        posTwo.addAll(carryHeroes.getPositionTwoHeroesHeroes());

        for(PlayerHeroData playerData : playerHeroData)
        {
            LOG.debug("inside the for loop");
            if(posOne.contains(playerData.getHero_id()))
            {
                LOG.debug("Inside the if");
               possiblePosOneHeroes.add(playerData.getHero_id());
               LOG.debug(playerData.getHero_id());
            }
            if(posTwo.contains(playerData.getHero_id()))
            {
                possiblePosTwoHeroes.add(playerData.getHero_id());
                LOG.debug(playerData.getHero_id());

            }
        }



    }



    public void validatePlayerID(long playerID) {
        if (playerID < 0) {
            String s = "Player ID was negative";
            String error = String.format("player ID %d was negative or invalid", playerID);
            LOG.error(error);
            throw new D2LastPickerValidationException(s);
        }
    }

    public void validateHeroIDs(List<Integer> heroList, int expectedLength) {

        if (heroList.size() != expectedLength) {
            String s = String.format("the expected number of heroes was %d but you supplied %d", expectedLength, heroList.size());
            LOG.error(s);
            throw new D2LastPickerValidationException(s);
        }

        LOG.debug("The hero list was of the expected size");

        for (int i = 0; i < heroList.size(); i++) {
            int currentEntry = heroList.get(i);
            if (currentEntry > MAXIMUM_HERO_ID || currentEntry < 0) {
                String s = String.format("hero ID %d is out of bounds in the hero list", i);
                LOG.error(s);
                throw new D2LastPickerValidationException(s);

            }
        }
        LOG.debug("The no heroes inside the hero list was out bounds");
        validateHeroList(heroList);
        LOG.debug("No issues found with this list");
    }

    public void validateBothLists(List<Integer> us, List<Integer>them) {
        // test for duplicate between both lists
        List<Integer> tempList = new ArrayList<>();
        tempList.addAll(us);
        tempList.addAll(them);
        LOG.debug("Attempting to make new hashset call to validateBothLists successful");
        validateHeroList(tempList);
    }

    public void validateHeroList(List<Integer> heroList)
        {
            // test for duplicate inside the same list
            LOG.debug("Attempting to make new hashset call to validateHeroList successful");
            Set<Integer> hset = new HashSet<>();
            for (Integer i : heroList) {
                if (hset.add(i) == false) {
                    String s = String.format("hero ID %d is a duplicate in the hero list", i);
                    LOG.error(s);
                    throw new D2LastPickerValidationException(s);
                } else {
                    hset.add(i);
                }
            }
        }
    }
