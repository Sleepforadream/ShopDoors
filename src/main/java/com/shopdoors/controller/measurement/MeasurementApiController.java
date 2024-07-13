package com.shopdoors.controller.measurement;

import com.shopdoors.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MeasurementApiController {

    private final MeasurementService measurementService;

    @GetMapping("/available-times")
    public List<LocalTime> getAvailableTimes(@RequestParam LocalDate date) {
        return measurementService.getAvailableTimesPerMeasures(date)
                .keySet()
                .stream()
                .sorted()
                .toList();
    }
}