package com.gcast.gcast.models;

public class GreetingOutput {
    private String greeting;

    public String getGreeting() {return greeting;}
    public void setGreeting(String greeting) {this.greeting = greeting;}

    public GreetingOutput() {
    }
    public GreetingOutput(String greeting) {
        this.greeting = greeting;
    }

}
