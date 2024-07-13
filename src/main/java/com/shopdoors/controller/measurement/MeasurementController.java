package com.shopdoors.controller.measurement;

import com.shopdoors.service.MeasurementService;
import com.shopdoors.service.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MeasurementController {

    private final ValidateService validateService;
    private final MeasurementService measurementService;

    @GetMapping(value = {"/measurements"})
    public String measurementsPage(
            @RequestParam(required = false) LocalDate date,
            Model model
    ) {
        if (date == null) {
            date = LocalDate.now().plusDays(1);
        }
        List<LocalTime> availableTimes = measurementService.getAvailableTimesPerMeasures(date)
                .keySet()
                .stream()
                .sorted()
                .toList();
        model.addAttribute("availableTimes", availableTimes);
        model.addAttribute("selectedDate", date);
        return "measurements";
    }

    @PostMapping("/measurements")
    public String createMeasurementEvent(
            @RequestParam("name") String name,
            @RequestParam("secondName") String secondName,
            @RequestParam("thirdName") String thirdName,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("fabric") String fabric,
            @RequestParam("roomDoorsCount") int roomDoorsCount,
            @RequestParam("enterDoorsCount") int enterDoorsCount,
            @RequestParam("measurementDate") String measurementDate,
            @RequestParam("measurementTime") String measurementTime,
            @RequestParam("info") String info,
            Model model
    ) {

        var errors = validateService.validateMeasurements(
                name,
                secondName,
                thirdName,
                email,
                phoneNumber,
                address,
                roomDoorsCount,
                enterDoorsCount,
                measurementDate,
                measurementTime,
                info
        );

        if (!errors.values().stream().findFirst().orElse(true)) {
            model.addAttribute("error", errors.keySet()
                    .stream()
                    .findFirst()
                    .orElse("Неизвестная ошибка"));
            if (!name.isEmpty()) model.addAttribute("name", name);
            if (!phoneNumber.isEmpty()) model.addAttribute("phoneNumber", phoneNumber);
            if (!address.isEmpty()) model.addAttribute("address", address);
            if (!city.isEmpty()) model.addAttribute("city", city);
            if (!fabric.isEmpty()) model.addAttribute("fabric", fabric);
            if (roomDoorsCount != 0) model.addAttribute("roomDoorsCount", roomDoorsCount);
            if (enterDoorsCount != 0) model.addAttribute("enterDoorsCount", enterDoorsCount);
            if (!measurementDate.isEmpty()) model.addAttribute("measurementDate", measurementDate);
            if (!info.isEmpty()) model.addAttribute("info", info);
            return "measurements";
        }

        measurementService.createEventMeasurement(
                email,
                phoneNumber,
                name,
                secondName,
                thirdName,
                address,
                city,
                fabric,
                roomDoorsCount,
                enterDoorsCount,
                measurementDate,
                measurementTime,
                info
        );

        model.addAttribute("success", true);
        return "redirect:/measurements";
    }
}