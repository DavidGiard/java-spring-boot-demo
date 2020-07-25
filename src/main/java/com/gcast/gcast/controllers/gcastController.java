package com.gcast.gcast.controllers;

import java.util.UUID;

import com.gcast.gcast.models.GreetingInput;
import com.gcast.gcast.models.GreetingOutput;
import com.gcast.gcast.services.MathService;
import com.gcast.gcast.services.MathServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("gcast")
@RestController
public class gcastController {

    private Logger logger = LoggerFactory.getLogger(gcastController.class);


    @Autowired
    @Qualifier("MathServiceImpl")
    private MathService mathService;

    @GetMapping("Greet")
    public ResponseEntity<String> SayHello() {
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }

    @GetMapping("Greet/{personName}")
    public ResponseEntity<String> SayHelloPersonal(@PathVariable("personName") String personName) {
        String greeting = "Hello " + personName;

        logger.trace(personName + " is Tracy's Mom");
        logger.debug("Don't bug " + personName);
        logger.info("Information please");
        logger.warn("Warning! Warning! Warning! Dr Smith Call " + personName);
        logger.error(personName + " is exceptional!");

        return new ResponseEntity<String>(greeting, HttpStatus.OK);
    }

    @PostMapping(path = "Hello", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GreetingOutput> SayHelloPost(@RequestBody GreetingInput greetingInput) {
        String personName = greetingInput.getPersonName();
        String greeting = "Hello " + personName;
        GreetingOutput greetingOutput = new GreetingOutput(greeting);

        return new ResponseEntity<GreetingOutput>(greetingOutput, HttpStatus.OK);
    }

    @GetMapping("AddNumbers/{firstNumber}/{secondNumber}")
    public ResponseEntity<Integer> AddNumbers(
            @PathVariable("firstNumber") Integer firstNumber,
            @PathVariable("secondNumber") Integer secondNumber
    ) {

        String requestKey = UUID.randomUUID().toString();
        MDC.put("RequestKey", requestKey);

        logger.info("Controller started. AddNumbers");
        logger.info("First: " + firstNumber);
        logger.info("Second: " + secondNumber);
        Integer sum = mathService.AddNumbers(firstNumber, secondNumber);
        return new ResponseEntity<Integer>(sum, HttpStatus.OK);
    }
}
