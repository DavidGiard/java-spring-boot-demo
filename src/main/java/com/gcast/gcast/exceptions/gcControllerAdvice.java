package com.gcast.gcast.exceptions;

import com.gcast.gcast.models.DivideNumbersOutput;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class gcControllerAdvice {

    @ResponseBody
    @ExceptionHandler(MissingArgumentsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<DivideNumbersOutput> MissingArgumentsExceptionHandler(MissingArgumentsException ex) {
        String message = "MISSING ARGS ERROR: " + ex.getMessage();
        DivideNumbersOutput divideNumbersOutput = new DivideNumbersOutput(null, message);
        return new ResponseEntity<DivideNumbersOutput>(divideNumbersOutput, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<DivideNumbersOutput> ArithmeticExceptionHandler(ArithmeticException ex) {
        String message = "MATH ERROR: " + ex.getMessage();
        DivideNumbersOutput divideNumbersOutput = new DivideNumbersOutput(null, message);
        return new ResponseEntity<DivideNumbersOutput>(divideNumbersOutput, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<DivideNumbersOutput> ExceptionHandler(Exception ex) {
        String message = "GENERAL ERROR: " + ex.getMessage();
        DivideNumbersOutput divideNumbersOutput = new DivideNumbersOutput(null, message);
        return new ResponseEntity<DivideNumbersOutput>(divideNumbersOutput, HttpStatus.BAD_REQUEST);
    }

}