package com.pprakapavicius.TripRequirementsCalculator.services.resolvers;

import com.pprakapavicius.TripRequirementsCalculator.models.ConditioningLevel;
import com.pprakapavicius.TripRequirementsCalculator.models.Requirement;
import com.pprakapavicius.TripRequirementsCalculator.utils.TripUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FoodRequirementsResolver implements RequirementsResolver {
    @Value("${average.daily.snacks}")
    private int SNACKS_PER_DAY;
    @Value("${average.daily.meals}")
    private int MEALS_PER_DAY;
    @Value("${average.daily.range}")
    private int RANGE_PER_DAY;

    @Autowired
    private TripUtils tripUtils;

    @Override
    public List<Requirement> resolve(int range, int durationInDays, ConditioningLevel conditioning, Date startDate, Date finishDate) {
        List<Requirement> requirements = new ArrayList<>();

        int totalMeals = tripUtils.calculateTotalAmount(MEALS_PER_DAY, durationInDays, conditioning);
        requirements.add(new Requirement("Meals", totalMeals));

        if (ConditioningLevel.ADVANCED != conditioning) {
            int totalSnacks = tripUtils.calculateTotalAmount(SNACKS_PER_DAY, durationInDays, conditioning);
            requirements.add(new Requirement("Snacks", totalSnacks));
        }

        return requirements;
    }
}
