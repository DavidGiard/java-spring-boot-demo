package com.gcast.gcast.services;

import com.gcast.gcast.exceptions.MissingArgumentsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("MathServiceImpl")
@Service
public class MathServiceImpl implements MathService {

    private Logger logger = LoggerFactory.getLogger(MathServiceImpl.class);

    @Override
    public Integer AddNumbers(Integer firstNumber, Integer secondNumber) throws MissingArgumentsException {
        logger.info("MathService called. AddNumbers");
        logger.info("First: " + firstNumber);
        logger.info("Second: " + secondNumber);

        if (firstNumber == null || secondNumber == null) {
            String message = "Missing input. firstNumber amd secondNumber are required";
            throw new MissingArgumentsException(message);
        }
        Integer sum = firstNumber + secondNumber;
        return sum;
    }

    @Override
    public Integer DivideNumbers(Integer firstNumber, Integer secondNumber) {
        Integer quotient = 0;
        quotient = firstNumber / secondNumber;
        return quotient;
    }
}
