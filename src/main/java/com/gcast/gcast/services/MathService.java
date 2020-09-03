package com.gcast.gcast.services;

import com.gcast.gcast.exceptions.MissingArgumentsException;

public interface MathService {
    Integer AddNumbers (Integer firstNumber, Integer secondNumber) throws MissingArgumentsException;
    Integer DivideNumbers (Integer firstNumber, Integer secondNumber);
}
