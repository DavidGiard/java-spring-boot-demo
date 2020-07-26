package com.gcast.gcast.models;

public class AddNumbersOutput {
    private Integer sum;
    public Integer getSum() {return sum;}
    public void setSum(Integer sum) {this.sum = sum;}

    private String message;
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

    public AddNumbersOutput(Integer sum) {
        this.sum = sum;
    }

    public AddNumbersOutput(Integer sum, String message) {
        this.sum = sum;
        this.message = message;
    }
}
