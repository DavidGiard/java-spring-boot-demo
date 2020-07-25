package com.gcast.gcast.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Qualifier("MathServiceImpl")
@Service
public class MathServiceImpl implements MathService {

    private Logger logger = LoggerFactory.getLogger(MathServiceImpl.class);

    @Override
    public Integer AddNumbers(Integer firstNumber, Integer secondNumber) {
        logger.info("MathService called. AddNumbers");
        logger.info("First: " + firstNumber);
        logger.info("Second: " + secondNumber);
        Integer sum = firstNumber + secondNumber;
        return sum;
    }
}
