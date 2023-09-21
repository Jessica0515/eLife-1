package com.example.elife;

public class TimeRange {
    private String range;
    private int value;

    public TimeRange(String range, int value) {
        this.range = range;
        this.value = value;
    }

    public String getRange() {
        return range;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return range ;
    }
}

