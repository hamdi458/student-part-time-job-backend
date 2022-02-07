package com.example.demo.model;

public enum Reacttype {

    INTERESTED("INTERESTED"),

    ACCEPTED("ACCEPTED"),

    REFUSED("REFUSED");
    private String state;

    Reacttype(String type) {
        this.state = type;
    }
    public String state() {
        return state;
    }

    public String getState() {
        return state;
    }

    public void setState(String type) {
        this.state = type;
    }
}
