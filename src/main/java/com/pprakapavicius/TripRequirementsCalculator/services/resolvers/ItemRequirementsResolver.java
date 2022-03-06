package com.pprakapavicius.TripRequirementsCalculator.services.resolvers;

import com.pprakapavicius.TripRequirementsCalculator.models.ConditioningLevel;
import com.pprakapavicius.TripRequirementsCalculator.models.Requirement;
import com.pprakapavicius.TripRequirementsCalculator.models.Season;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ItemRequirementsResolver implements RequirementsResolver {

    @Override
    public List<Requirement> resolve(int range, int durationInDays, ConditioningLevel conditioning, Date startDate, Date finishDate) {
        List<Requirement> requirements = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(startDate);
        int startMonth = calendar.get(Calendar.MONTH);

        calendar.setTime(finishDate);
        int endMonth = calendar.get(Calendar.MONTH);

        getSeasons(List.of(startMonth, endMonth)).forEach(season -> {
            requirements.add(new Requirement(season.getItem(), 1));
        });

        return requirements;
    }

    private Set<Season> getSeasons(List<Integer> monthIndexes) {
        return monthIndexes.stream()
                .map(Season::fromMonthIndex)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
