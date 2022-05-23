package com.bruk.d2lastpicker.controller;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bruk.d2lastpicker.util.ControllerError;
import com.bruk.d2lastpicker.util.JSONTest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/picker")
public class LastPickerController {

    private static final String TEST_URL = "/test";
    private static final String TEST2_URL = "/test2";
    private static final Logger LOG = LoggerFactory.getLogger(LastPickerController.class);

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

}
