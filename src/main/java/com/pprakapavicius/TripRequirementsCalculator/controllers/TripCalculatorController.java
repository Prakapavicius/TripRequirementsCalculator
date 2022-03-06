package com.pprakapavicius.TripRequirementsCalculator.controllers;

import com.pprakapavicius.TripRequirementsCalculator.models.ConditioningLevel;
import com.pprakapavicius.TripRequirementsCalculator.models.TripRequirementsResponse;
import com.pprakapavicius.TripRequirementsCalculator.services.TripRequirementsService;
import com.pprakapavicius.TripRequirementsCalculator.utils.JsonPropertyEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/trip")
public class TripCalculatorController {

    @Autowired
    private TripRequirementsService tripRequirementsService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ConditioningLevel.class, new JsonPropertyEditor<>(ConditioningLevel.class));
    }

    @GetMapping("/requirements")
    public TripRequirementsResponse getTripRequirements(@RequestParam @NotNull @Min(1) @Max(1000) Integer range,
                                                        @RequestParam @NotNull ConditioningLevel conditioning,
                                                        @RequestParam(required = false) @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate) {
        return tripRequirementsService.getTripRequirements(range, conditioning, startDate);
    }
}