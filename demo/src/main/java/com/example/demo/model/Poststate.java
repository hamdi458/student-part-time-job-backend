package com.example.demo.model;

public enum Poststate {
    FREE("FREE"),
    OCCUPIED("OCCUPIED");







    private String state;

    Poststate(String state) {
        this.state = state;
    }
    public String state() {
        return state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
