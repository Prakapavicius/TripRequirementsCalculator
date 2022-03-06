package com.pprakapavicius.TripRequirementsCalculator.services.resolvers;

import com.pprakapavicius.TripRequirementsCalculator.models.ConditioningLevel;
import com.pprakapavicius.TripRequirementsCalculator.models.Requirement;

import java.util.Date;
import java.util.List;

public interface RequirementsResolver {
    List<Requirement> resolve(int range, int durationInDays, ConditioningLevel conditioning, Date startDate, Date finishDate);
}
