package com.gcast.gcast.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("MathServiceImpl")
@Service
public class MathServiceImpl implements MathService {

    @Override
    public Integer AddNumbers(Integer firstNumber, Integer secondNumber) {
        Integer sum = firstNumber + secondNumber;
        return sum;
    }
}
