package com.gcast.gcast.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("MathServiceMock")
@Service
public class MathServiceMock implements MathService {
    @Override
    public Integer AddNumbers(Integer firstNumber, Integer secondNumber) {
        return 10;
    }
}
