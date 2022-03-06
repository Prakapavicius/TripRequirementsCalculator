package com.pprakapavicius.TripRequirementsCalculator.services.resolvers;

import com.pprakapavicius.TripRequirementsCalculator.models.ConditioningLevel;
import com.pprakapavicius.TripRequirementsCalculator.models.Requirement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ShelterRequirementsResolver implements RequirementsResolver {

    @Override
    public List<Requirement> resolve(int range, int durationInDays, ConditioningLevel conditioning, Date startDate, Date finishDate) {
        List<Requirement> requirements = new ArrayList<>();
        if (durationInDays < 1) {
            return requirements;
        }

        int stopToRest = (int) Math.ceil(durationInDays / conditioning.getRatio());
        requirements.add(new Requirement("Rest", stopToRest));
        requirements.add(new Requirement("Sleep", durationInDays));

        return requirements;
    }
}
