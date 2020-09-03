package com.gcast.gcast.models;

public class DivideNumbersOutput {
    private Integer quotient;
    public Integer getQuotient() {return quotient;}
    public void setQuotient(Integer quotient) {this.quotient = quotient;}

    private String message;
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

    public DivideNumbersOutput(Integer quotient) {
        this.quotient = quotient;
    }

    public DivideNumbersOutput(Integer quotient, String message) {
        this.quotient = quotient;
        this.message = message;
    }
    
}