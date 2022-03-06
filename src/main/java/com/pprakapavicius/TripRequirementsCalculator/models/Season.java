package com.pprakapavicius.TripRequirementsCalculator.models;

import java.util.List;

public enum Season {
    SPRING("Sweater"),
    SUMMER("Cap"),
    AUTUMN("Umbrella"),
    WINTER("Coat");

    private final String item;

    Season(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    private static final List<Season> MONTHLY_SEASON_LIST = List.of(
            WINTER, WINTER, SPRING, SPRING, SPRING, SUMMER, SUMMER, SUMMER, AUTUMN, AUTUMN, AUTUMN, WINTER);

    public static Season fromMonthIndex(int index) {
        if (index < 0 || index >= MONTHLY_SEASON_LIST.size()) {
            return null;
        }

        return MONTHLY_SEASON_LIST.get(index);
    }
}
