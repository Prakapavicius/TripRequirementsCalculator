package com.pprakapavicius.TripRequirementsCalculator.utils;

import com.pprakapavicius.TripRequirementsCalculator.models.ConditioningLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TripUtils {
    @Value("${average.daily.range}")
    private int RANGE_PER_DAY;

    public int calculateDurationInDays(int range, @NonNull ConditioningLevel conditioning) {
        return (int) (range / (RANGE_PER_DAY * conditioning.getRatio()));
    }

    public int calculateTotalAmount(int amountPerDay, int days, @NonNull ConditioningLevel conditioning) {
        if (days < 1) {
            return 1;
        }

        return (int) Math.ceil((amountPerDay / conditioning.getRatio()) * days);
    }
}
