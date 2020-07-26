package com.gcast.gcast.exceptions;

public class MissingArgumentsException extends Exception {

    private static final long serialVersionUID = 1L;

    public MissingArgumentsException(String errorMessage){
        super(errorMessage);
    }

    public MissingArgumentsException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
}
