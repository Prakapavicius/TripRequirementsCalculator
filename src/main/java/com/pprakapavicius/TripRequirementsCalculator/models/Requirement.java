package com.pprakapavicius.TripRequirementsCalculator.models;

public class Requirement {
    private final String name;
    private final int count;

    public Requirement(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
