package com.bruk.d2lastpicker.service;

import com.bruk.d2lastpicker.dto.HeroData;
import com.bruk.d2lastpicker.dto.HeroMatchupData;
import com.bruk.d2lastpicker.dto.PlayerHeroData;
import com.bruk.d2lastpicker.util.CarryHeroes;
import com.bruk.d2lastpicker.util.D2LastPickerValidationException;
import com.bruk.d2lastpicker.util.HeroWinrateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.*;


@Service
public class Dota2MatchupServiceImpl implements Dota2MatchupService {

    private static final int STEAM_32_ID = 8;
    private static final int MAXIMUM_HERO_ID = 137;
    private static final int EXPECTED_US_LIST_LENGTH = 4;
    private static final int EXPECTED_THEM_LIST_LENGTH = 5;
    private static final int MINIMUM_GAMES_REQUIRED = 10;

    private static final int TOP_TEN = 10;

    private static final int TOP_THREE = 3;

    private static final int GROUPINGS = 5;

    private static final double AVERAGE_WINRATE = .50;

    private static final Logger LOG = LoggerFactory.getLogger(Dota2MatchupServiceImpl.class);


    private Dota2APIService APIService;
    private CarryHeroes carryHeroes;


    @Autowired
    public Dota2MatchupServiceImpl(Dota2APIService APIService) {
        this.APIService = APIService;
    }

    public Dota2MatchupServiceImpl() {
    }

    /*
    This class is the main algorithm of the program. Given a player ID, 4 heroes on your team, and 5 heroes on the
    enemy team, it finds the "best" hero to pick based on a given players individual performance on a hero as well as the
    heroes' performance against the heroes on the enemy team.
     */

    public List<HeroWinrateData> calculateMatchup(long playerID, List<Integer> us, List<Integer> them) {
        validatePlayerID(playerID);
        validateHeroIDs(us, EXPECTED_US_LIST_LENGTH);
        validateHeroIDs(them, EXPECTED_THEM_LIST_LENGTH);
        validateBothLists(us, them);
        List<HeroWinrateData> topThreeBothRoles = topThreeHeroes(them, playerID);

        for(HeroWinrateData e : topThreeBothRoles)
        {
            String debug = String.format("Top Hero Name: %s Winrate: %f isCarry: %b", e.getHeroName(), e.getWinrate(), e.isCarry());
            LOG.debug(debug);
        }
        return topThreeBothRoles;
    }

    private List<HeroWinrateData> topThreeHeroes(List<Integer> them, long playerID)
    {
        List<HeroWinrateData> totalHeroWinrates = groupWinrates(them, playerID);
        List<HeroWinrateData> topThreeCarries = new ArrayList<>();
        List<HeroWinrateData> topThreeMids = new ArrayList<>();
        List<HeroWinrateData> topThreeBothRoles = new ArrayList<>();
        int numberOfMids = 0;
        int numberofCarries = 0;
        Collections.sort(totalHeroWinrates, Collections.reverseOrder());
        for(HeroWinrateData e : totalHeroWinrates)
        {
            if(e.isCarry() && numberofCarries < TOP_THREE)
            {
                topThreeCarries.add(e);
                numberofCarries++;
                continue;
            }
            if(!e.isCarry() && numberOfMids < TOP_THREE)

            {
                topThreeMids.add(e);
                numberOfMids++;
            }
        }
        topThreeBothRoles.addAll(topThreeCarries);
        topThreeBothRoles.addAll(topThreeMids);
        return topThreeBothRoles;
    }


    //
    private List<HeroWinrateData> groupWinrates(List<Integer> them, long playerID)
    {
        double adjustedPickRate;
        String heroName = "";
        int heroID = 0;
        double totalAdjustedPickRate = 0;
        int i = 1;
        boolean isCarry = false;
        List<HeroWinrateData> adjustedPickRateList = new ArrayList<>();
        List<HeroWinrateData> heroMatchupList = getHeroMatchups(them, playerID);
        for(HeroWinrateData e : heroMatchupList)
        {
            isCarry = e.isCarry();
            if(i == GROUPINGS)
            {
                HeroWinrateData adjustedHeroPickRate = new HeroWinrateData(totalAdjustedPickRate, heroID, heroName, isCarry);
                adjustedPickRateList.add(adjustedHeroPickRate);
                i = 1;
                adjustedPickRate = 0;
                totalAdjustedPickRate = 0;
                continue;
            }
            heroName = e.getHeroName();
            heroID = e.getHeroId();
            adjustedPickRate = e.getWinrate() - AVERAGE_WINRATE;
            totalAdjustedPickRate += adjustedPickRate;
            i++;
        }
        // pickup/add the last hero that needs to still be added to the list
        HeroWinrateData adjustedHeroPickRate = new HeroWinrateData(totalAdjustedPickRate, heroID, heroName, isCarry);
        adjustedPickRateList.add(adjustedHeroPickRate);
        return adjustedPickRateList;
    }

    // Calls getTopTenHeroes(), for each of the players best heroes (both pos 1 and 2) finds their associated winrate
    // by calling the matchupData API to know that heroes winrate against the heroes that are shown on the enemy team.
    private List<HeroWinrateData> getHeroMatchups(List<Integer> them, long playerId) {
        double winrate;
        List<HeroWinrateData> topTenBothRoles = getTopTenHeroes(playerId);
        List<HeroWinrateData> heroWinrateData = new ArrayList<>();
        String name = "";
        int ID = 0;
        int enemyHeroID = 0;
        int i = 0;

      for (HeroWinrateData e : topTenBothRoles) {
          name = e.getHeroName();
          ID = e.getHeroId();
              List<HeroMatchupData> heroMatchupData = APIService.getHeroMatchupData(topTenBothRoles.get(i).getHeroId());
              i++;
              for (HeroMatchupData matchupData : heroMatchupData) {
                  long heroID = matchupData.getHero_id();
                  Integer heroInt = (int) heroID;
                  if (them.contains(heroInt)) {
                      enemyHeroID = heroInt;
                      if (matchupData.getGames_played() == 0) {
                          winrate = 0;
                      }
                      if (matchupData.getWins() == 0) {
                          winrate = 0;
                      }
                      double wins = matchupData.getWins();
                      double games = matchupData.getGames_played();
                      winrate = wins / games;
                      HeroWinrateData winrateData = new HeroWinrateData(winrate, ID, name, e.isCarry());
                      heroWinrateData.add(winrateData);
              }
          }
      }
        return heroWinrateData;
    }


    // Calls the getPlayerHeroData API service to find basic stastics about all the heroes a certain player plays
    // then finds only the heroes we care about (pos 1 and 2) and adds them to a list storing all "carry" hero IDs
    // that the specific player plays/has played.
    private List<PlayerHeroData> getPlayerHeroes(long playerId)
    {
        LOG.debug("Calling getPlayerHeroes");
        List<PlayerHeroData> playerHeroData = APIService.getPlayerHeroData(playerId);
        List<Integer> possiblePosOneHeroes = new ArrayList<>();
        List<Integer> possiblePosTwoHeroes = new ArrayList<>();
        List<Integer> allPossibleHeroes = new ArrayList<>();
        List<PlayerHeroData> cutDownList = new ArrayList<>();
        List<Integer> posOne = new ArrayList<>();
        List<Integer> posTwo = new ArrayList<>();
        CarryHeroes carryHeroes = new CarryHeroes();
        posOne.addAll(carryHeroes.getPositionOneHeroes());
        posTwo.addAll(carryHeroes.getPositionTwoHeroesHeroes());

        for(PlayerHeroData playerData : playerHeroData)
        {
            if(posOne.contains(playerData.getHeroValue()))
            {
               possiblePosOneHeroes.add(playerData.getHeroValue());
               LOG.debug(playerData.getHero_id());
            }
            if(posTwo.contains(playerData.getHeroValue()))
            {
                possiblePosTwoHeroes.add(playerData.getHeroValue());
                LOG.debug(playerData.getHero_id());

            }
        }
        allPossibleHeroes.addAll(possiblePosOneHeroes);
        allPossibleHeroes.addAll(possiblePosTwoHeroes);
        for(PlayerHeroData playerData : playerHeroData)
        {
            if(allPossibleHeroes.contains(playerData.getHeroValue()))
            {
                cutDownList.add(playerData);
            }
        }
        String debug = String.format("The function getPlayerHeroes returned successfully", cutDownList);
        LOG.debug(debug);
        return cutDownList;
    }

    // finds a players (given a playerID) best 10 position one heroes and best 10 position 2 heroes
    // returns a list of HeroWinrateData that contains a hero name, hero ID, and the players winrate on that hero
    private List<HeroWinrateData> getTopTenHeroes(long playerId) {
        double winrate = 0;
        List<PlayerHeroData> playerHeroes = getPlayerHeroes(playerId);
        List<HeroWinrateData> topTenBothRoles = new ArrayList<>();
        List<HeroWinrateData> heroWinrateData = new ArrayList<>();
        CarryHeroes carryHeroes = new CarryHeroes();
        Map<Integer, String> positionOneHeroNames = carryHeroes.getPositionOneHeroNames();
        Map<Integer, String> positionTwoHeroNames = carryHeroes.getPositionTwoHeroNames();

        // for all the position one heroes in the game
        for (Map.Entry<Integer, String> e : positionOneHeroNames.entrySet()) {
            // for all the heroes the player has played
            for (PlayerHeroData playerHero : playerHeroes) {

                // if the player has played the position one hero
                if (playerHero.getHeroValue() == (e.getKey())) {
                    int wins = playerHero.getWin();
                    int totalGames = playerHero.getGames();

                    // player hasn't played enough games on the hero, skip
                    if(totalGames < MINIMUM_GAMES_REQUIRED || Double.isNaN(winrate) )
                    {
                        continue;
                    }

                    if (wins == 0 || totalGames == 0) {
                        winrate = 0;
                    }
                    else {
                        winrate = (double) wins / totalGames;
                    }
                    Integer heroID = e.getKey();
                    String heroName = e.getValue();

                    HeroWinrateData heroData = new HeroWinrateData(winrate, heroID, heroName, true);
                    LOG.debug(heroData.getHeroName());
                    heroWinrateData.add(heroData);
                }
            }
        }

        Collections.sort(heroWinrateData, Collections.reverseOrder());
        int maxHeroCount = TOP_TEN;
        if(heroWinrateData.size() < TOP_TEN)
        {
            maxHeroCount = heroWinrateData.size();
        }
        for (int i = 0; i < maxHeroCount; i++) {
            HeroWinrateData heroData = new HeroWinrateData(heroWinrateData.get(i).getWinrate(),
                    heroWinrateData.get(i).getHeroId(), heroWinrateData.get(i).getHeroName(), true);
            topTenBothRoles.add(heroData);
        }

        // clearing the List<HeroWinrateData> to allow for position two heroes to be looked over
        heroWinrateData.clear();

        for (Map.Entry<Integer, String> e : positionTwoHeroNames.entrySet()) {
            for (PlayerHeroData playerHero : playerHeroes) {
                if (playerHero.getHeroValue() == (e.getKey())) {

                    int wins = playerHero.getWin();
                    int totalGames = playerHero.getGames();
                    if (wins == 0) {
                        winrate = 0;
                    }
                    if (totalGames == 0) {
                        winrate = 0;
                    } else {
                        winrate = (double) wins / totalGames;
                    }
                    if (Double.isNaN(winrate)) {
                        continue;
                    }
                    Integer heroID = e.getKey();
                    String heroName = e.getValue();
                    HeroWinrateData heroData = new HeroWinrateData(winrate, heroID, heroName, false);
                    heroWinrateData.add(heroData);
                }
            }
        }

        Collections.sort(heroWinrateData, Collections.reverseOrder());
        maxHeroCount = TOP_TEN;
        if(heroWinrateData.size() < TOP_TEN)
        {
            maxHeroCount = heroWinrateData.size();
        }
        for (int i = 0; i < maxHeroCount; i++) {
            HeroWinrateData heroData = new HeroWinrateData(heroWinrateData.get(i).getWinrate(),
                    heroWinrateData.get(i).getHeroId(), heroWinrateData.get(i).getHeroName(), false);
            topTenBothRoles.add(heroData);
        }
        return topTenBothRoles;
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
