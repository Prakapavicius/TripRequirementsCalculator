package com.pprakapavicius.TripRequirementsCalculator.models;

import java.util.Date;
import java.util.List;

public class TripRequirementsResponse {
    private final Date startDate;
    private final Date finishDate;
    private final List<Requirement> foodRequirements;
    private final List<Requirement> shelterRequirements;
    private final List<Requirement> itemRequirements;

    public TripRequirementsResponse(Builder builder) {
        this.startDate = builder.startDate;
        this.finishDate = builder.finishDate;
        this.foodRequirements = builder.foodRequirements;
        this.shelterRequirements = builder.shelterRequirements;
        this.itemRequirements = builder.itemRequirements;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public List<Requirement> getFoodRequirements() {
        return foodRequirements;
    }

    public List<Requirement> getShelterRequirements() {
        return shelterRequirements;
    }

    public List<Requirement> getItemRequirements() {
        return itemRequirements;
    }

    public static class Builder {
        private final Date startDate;
        private final Date finishDate;
        private List<Requirement> foodRequirements;
        private List<Requirement> shelterRequirements;
        private List<Requirement> itemRequirements;

        public Builder(Date startDate, Date finishDate) {
            this.startDate = startDate;
            this.finishDate = finishDate;
        }

        public Builder setFoodRequirements(List<Requirement> foodRequirements) {
            this.foodRequirements = foodRequirements;
            return this;
        }

        public Builder setShelterRequirements(List<Requirement> shelterRequirements) {
            this.shelterRequirements = shelterRequirements;
            return this;
        }

        public Builder setItemRequirements(List<Requirement> itemRequirements) {
            this.itemRequirements = itemRequirements;
            return this;
        }

        public TripRequirementsResponse build() {
            return new TripRequirementsResponse(this);
        }
    }
}
