package com.bruk.d2lastpicker.controller;

import com.bruk.d2lastpicker.dto.PlayerHeroData;
import com.bruk.d2lastpicker.dto.HeroData;
import com.bruk.d2lastpicker.dto.PublicMatchData;
import com.bruk.d2lastpicker.service.Dota2APIImpl;
import com.bruk.d2lastpicker.service.Dota2APIService;
import com.bruk.d2lastpicker.service.TestService;
import com.bruk.d2lastpicker.service.TestServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bruk.d2lastpicker.util.ControllerError;
import com.bruk.d2lastpicker.util.JSONTest;

import java.io.PrintStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/picker")
public class LastPickerController {

    private static final String TEST_URL = "/test";
    private static final String TEST2_URL = "/test2";

    private static final String TEST3_URL = "/test3";

    private static final String TEST4_URL = "/pos12/{id}";

    private static final String ALL_HEROES = "/heroes";

    private static final String PLAYER_HEROES = "/{id}/heroes";

    private static final Logger LOG = LoggerFactory.getLogger(LastPickerController.class);

    @Autowired
    private TestService testService;
    @Autowired
    private Dota2APIService dota2APIService;


    @GetMapping(TEST_URL)
    public ResponseEntity<?> TestMethod() {
        System.out.printf("Is in debug mode: %b\n",(LOG.isDebugEnabled()));


        try {
            LOG.debug("Calling TestMethod()");
            String foo = "Hello World";
            return ResponseEntity.ok(foo);

        }  catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ControllerError>(new ControllerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(TEST2_URL)
    public ResponseEntity<?> TestTwo() {
        System.out.printf("Is in debug mode: %b\n",(LOG.isDebugEnabled()));

        List<JSONTest> myData = new ArrayList<>();
        myData.add(new JSONTest("Testing String", -2, false));
        myData.add(new JSONTest("I am the best", 6, true));
        myData.add(new JSONTest("I am the worst", 50, false));

        try {
            LOG.debug("Attempting to switch to JSON");
            return ResponseEntity.ok(myData);

        }  catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ControllerError>(new ControllerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(TEST3_URL)
    public ResponseEntity<?> TestThree() {

        try{
            List<PublicMatchData> matchesList = testService.getPublicMatches();
            return ResponseEntity.ok(matchesList);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ControllerError>(new ControllerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(TEST4_URL)
    @ResponseBody
    public ResponseEntity<?> TestFour(@PathVariable long id, @RequestParam List<Integer> us, @RequestParam List<Integer> them) {

        try{
            LOG.debug("Attempting to create String");
            String printedString = String.format("Player ID: %d Our Team: %s Their Team: %s", id, us, them );
            LOG.debug("Player ID {}: ", id);
            LOG.debug("Our Team: {}", us);
            LOG.debug("Their Team: {}", them);
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

    @GetMapping(PLAYER_HEROES)
    public ResponseEntity<?> getPlayerHeroes(@PathVariable long id) {

        try{
            List<PlayerHeroData> matchesList = dota2APIService.getPlayerHeroData(id);
            return ResponseEntity.ok(matchesList);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ControllerError>(new ControllerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
