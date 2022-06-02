package com.bruk.d2lastpicker.controller;

import com.bruk.d2lastpicker.dto.HeroData;
import com.bruk.d2lastpicker.service.Dota2APIService;
import com.bruk.d2lastpicker.service.Dota2MatchupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bruk.d2lastpicker.util.ControllerError;

import java.util.List;

@RestController
@RequestMapping("/picker")
public class LastPickerController {


    private static final String ALL_HEROES = "/heroes";

    private static final String MATCHUP_TEST_URL = "/pos12/{id}/matchups";

    private static final Logger LOG = LoggerFactory.getLogger(LastPickerController.class);

    @Autowired
    private Dota2APIService dota2APIService;
    private Dota2MatchupService dota2MatchupService;


    @GetMapping(MATCHUP_TEST_URL)
    @ResponseBody
    public ResponseEntity<?> TestFour(@PathVariable long playerId, @RequestParam List<Integer> us, @RequestParam List<Integer> them) {

        try{
            dota2MatchupService.calculateMatchup(playerId, us, them);
            String printedString = String.format("Calling was successful");
            return ResponseEntity.ok(printedString);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ControllerError>(new ControllerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(ALL_HEROES)
    public ResponseEntity<?> getAllHeroes() {

        try{
            LOG.debug("Creating List Of Hero Data");
            List<HeroData> matchesList = dota2APIService.getHeroData();
            LOG.debug("List Creation Successful");
            return ResponseEntity.ok(matchesList);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ControllerError>(new ControllerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
