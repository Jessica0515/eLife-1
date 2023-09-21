package com.example.elife;

public class WeekDay {
    private String name;
    private int value;

    public WeekDay(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
