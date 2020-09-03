package com.gcast.gcast.services;

import com.gcast.gcast.exceptions.MissingArgumentsException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("MathServiceMock")
@Service
public class MathServiceMock implements MathService {
    @Override
    public Integer AddNumbers(Integer firstNumber, Integer secondNumber) throws MissingArgumentsException{
        return 10;
    }

    @Override
    public Integer DivideNumbers (Integer firstNumber, Integer secondNumber){
        return 10;
    }
}
