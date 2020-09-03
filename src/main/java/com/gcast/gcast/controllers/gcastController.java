package com.gcast.gcast.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.gcast.gcast.exceptions.MissingArgumentsException;
import com.gcast.gcast.models.AddNumbersInput;
import com.gcast.gcast.models.AddNumbersOutput;
import com.gcast.gcast.models.DivideNumbersInput;
import com.gcast.gcast.models.DivideNumbersOutput;
import com.gcast.gcast.models.GreetingInput;
import com.gcast.gcast.models.GreetingOutput;
import com.gcast.gcast.services.MathService;
import com.gcast.gcast.services.MathServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
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
            @PathVariable("secondNumber") Integer secondNumber,
            HttpServletRequest request
    ) throws MissingArgumentsException {

        String requestKey = request.getHeader("X-Request-Key");
        if (requestKey == null){
            requestKey = UUID.randomUUID().toString();
        }

        MDC.put("RequestKey", requestKey);

        logger.info("Controller started. AddNumbers");
        logger.info("First: " + firstNumber);
        logger.info("Second: " + secondNumber);
        Integer sum = mathService.AddNumbers(firstNumber, secondNumber);
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Request-Key", requestKey);
        return new ResponseEntity<Integer>(sum, responseHeaders, HttpStatus.OK);
    }

    @PostMapping(path = "AddNumbers", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AddNumbersOutput> AddNumbersPost(@RequestBody AddNumbersInput addNumbersInput) {
        Integer firstNumber = addNumbersInput.getFirstNumber();
        Integer secondNumber = addNumbersInput.getSecondNumber();
        String personName = addNumbersInput.getPersonName();

        if (personName==null){
            String message = "Missing input. personName is required";
            AddNumbersOutput addNumbersOutput = new AddNumbersOutput(null, message);
            return new ResponseEntity<AddNumbersOutput>(addNumbersOutput, HttpStatus.BAD_REQUEST);
            }

        if (personName.compareTo("Bob") ==0){
            String message = "Bob is not welcome here";
            AddNumbersOutput addNumbersOutput = new AddNumbersOutput(null, message);
            return new ResponseEntity<AddNumbersOutput>(addNumbersOutput, HttpStatus.BAD_REQUEST);
        }

        Integer sum = null;
        try{
            sum = mathService.AddNumbers(firstNumber, secondNumber);
        }catch (MissingArgumentsException ex){
            String message = ex.getMessage();
            AddNumbersOutput addNumbersOutput = new AddNumbersOutput(null, message);
            return new ResponseEntity<AddNumbersOutput>(addNumbersOutput, HttpStatus.BAD_REQUEST);
        }

        String message = "The sum is " + sum + ", " + personName;
        AddNumbersOutput addNumbersOutput = new AddNumbersOutput(sum, message);
        return new ResponseEntity<AddNumbersOutput>(addNumbersOutput, HttpStatus.OK);

    }
    
    @PostMapping(path = "DivideNumbers", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DivideNumbersOutput> DivideNumbersPost(@RequestBody DivideNumbersInput divideNumbersInput) {
        Integer firstNumber = divideNumbersInput.getFirstNumber();
        Integer secondNumber = divideNumbersInput.getSecondNumber();
        String personName = divideNumbersInput.getPersonName();

        Integer quotient = 0;
        DivideNumbersOutput divideNumbersOutput;

        try {
            quotient = mathService.DivideNumbers(firstNumber, secondNumber);
            String message = "The quotient is " + quotient + ", " + personName;
            divideNumbersOutput = new DivideNumbersOutput(quotient, message);
            } catch (ArithmeticException e) {
            String message = "An arithmetic error has occured, " + personName + "! : " + e.getMessage();
            divideNumbersOutput = new DivideNumbersOutput(null, message);
        } catch (Exception e) {
            String message = "An error has occured, " + personName + "! : " + e.getMessage();
            divideNumbersOutput = new DivideNumbersOutput(null, message);
        }
        return new ResponseEntity<DivideNumbersOutput>(divideNumbersOutput, HttpStatus.OK);
    }

}
