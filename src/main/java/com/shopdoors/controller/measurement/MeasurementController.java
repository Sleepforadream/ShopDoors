package com.shopdoors.controller.measurement;

import com.shopdoors.dto.MeasurementDto;
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
import java.util.Map;

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
        MeasurementDto measurementDto = MeasurementDto.builder()
                .name(name)
                .secondName(secondName)
                .thirdName(thirdName)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(address)
                .city(city)
                .fabric(fabric)
                .roomDoorsCount(roomDoorsCount)
                .enterDoorsCount(enterDoorsCount)
                .measurementDate(measurementDate)
                .measurementTime(measurementTime)
                .info(info)
                .build();

        var errors = validateService.validateMeasurements(measurementDto);

        if (!errors.values().stream().findFirst().orElse(true)) {
            addErrorAttributesForModel(measurementDto, model, errors);
            return "measurements";
        }

        measurementService.createEventMeasurement(measurementDto);

        model.addAttribute("success", true);
        return "redirect:/measurements";
    }

    private static void addErrorAttributesForModel(MeasurementDto measurementDto, Model model, Map<String, Boolean> errors) {
        model.addAttribute("error", errors.keySet()
                .stream()
                .findFirst()
                .orElse("Неизвестная ошибка"));
        if (!measurementDto.getName().isEmpty()) model.addAttribute("name", measurementDto.getName());
        if (!measurementDto.getPhoneNumber().isEmpty()) model.addAttribute("phoneNumber", measurementDto.getPhoneNumber());
        if (!measurementDto.getAddress().isEmpty()) model.addAttribute("address", measurementDto.getAddress());
        if (!measurementDto.getCity().isEmpty()) model.addAttribute("city", measurementDto.getCity());
        if (!measurementDto.getFabric().isEmpty()) model.addAttribute("fabric", measurementDto.getFabric());
        if (measurementDto.getRoomDoorsCount() != 0) model.addAttribute("roomDoorsCount", measurementDto.getRoomDoorsCount());
        if (measurementDto.getEnterDoorsCount() != 0) model.addAttribute("enterDoorsCount", measurementDto.getEnterDoorsCount());
        if (!measurementDto.getMeasurementDate().isEmpty()) model.addAttribute("measurementDate", measurementDto.getMeasurementDate());
        if (!measurementDto.getInfo().isEmpty()) model.addAttribute("info", measurementDto.getInfo());
    }
}