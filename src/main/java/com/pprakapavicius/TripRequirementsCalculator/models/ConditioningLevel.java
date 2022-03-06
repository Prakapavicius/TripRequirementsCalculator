package com.pprakapavicius.TripRequirementsCalculator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ConditioningLevel {
    @JsonProperty("Couch Potato")
    COUCH_POTATO(0.5),
    @JsonProperty("Regular")
    REGULAR(1),
    @JsonProperty("Advanced")
    ADVANCED(1.5);

    private final double ratio;

    ConditioningLevel(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
}
