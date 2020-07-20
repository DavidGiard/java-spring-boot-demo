package com.gcast.gcast.models;

public class GreetingInput {
    private String personName;

    public String getPersonName() {return personName;}
    public void setPersonName(String personName) {this.personName = personName;}

    public GreetingInput() {
    }

    public GreetingInput(String personName) {
        this.personName = personName;
    }

}
