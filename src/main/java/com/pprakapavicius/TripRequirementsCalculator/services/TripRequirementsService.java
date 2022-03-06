package com.pprakapavicius.TripRequirementsCalculator.services;

import com.pprakapavicius.TripRequirementsCalculator.models.ConditioningLevel;
import com.pprakapavicius.TripRequirementsCalculator.models.TripRequirementsResponse;
import com.pprakapavicius.TripRequirementsCalculator.services.resolvers.FoodRequirementsResolver;
import com.pprakapavicius.TripRequirementsCalculator.services.resolvers.ItemRequirementsResolver;
import com.pprakapavicius.TripRequirementsCalculator.services.resolvers.ShelterRequirementsResolver;
import com.pprakapavicius.TripRequirementsCalculator.utils.TripUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TripRequirementsService {
    @Autowired
    private TripUtils tripUtils;
    @Autowired
    private FoodRequirementsResolver foodRequirementsResolver;
    @Autowired
    private ShelterRequirementsResolver shelterRequirementsResolver;
    @Autowired
    private ItemRequirementsResolver itemRequirementsResolver;

    public TripRequirementsResponse getTripRequirements(int range, ConditioningLevel conditioning, Date startDate) {
        int durationInDays = tripUtils.calculateDurationInDays(range, conditioning);
        Date finishDate = calculateFinishDate(durationInDays, startDate);

        var builder = new TripRequirementsResponse.Builder(startDate, finishDate)
        .setFoodRequirements(foodRequirementsResolver.resolve(range, durationInDays, conditioning, startDate, finishDate))
        .setShelterRequirements(shelterRequirementsResolver.resolve(range, durationInDays, conditioning, startDate, finishDate))
        .setItemRequirements(itemRequirementsResolver.resolve(range, durationInDays, conditioning, startDate, finishDate));

        return builder.build();
    }

    private Date calculateFinishDate(int durationInDays, Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, durationInDays);

        return calendar.getTime();
    }
}
