package com.shopdoors.controller;

import com.shopdoors.dao.entity.AuthorizeUser;
import com.shopdoors.dao.entity.City;
import com.shopdoors.dao.entity.Fabric;
import com.shopdoors.dao.entity.Measurement;
import com.shopdoors.dao.repository.AuthorizeUserRepository;
import com.shopdoors.dao.repository.MeasurementRepository;
import com.shopdoors.security.dto.AuthorizeUserDetails;
import com.shopdoors.service.ValidateService;
import com.shopdoors.util.TransactionRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class MeasurementController {

    private final ValidateService validateService;
    private final TransactionRunner transactionRunner;
    private final MeasurementRepository measurementRepository;
//    private final AuthorizeUserRepository authorizeUserRepository;

    @GetMapping(value = {"/measurements"})
    public String measurementsPage() {
        return "measurements";
    }

    @PostMapping("/measurements")
    public String profileChange(
            @RequestParam("name") String name,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("fabric") String fabric,
            @RequestParam("roomDoorsCount") int roomDoorsCount,
            @RequestParam("enterDoorsCount") int enterDoorsCount,
            @RequestParam("measurementDate") String measurementDate,
            @RequestParam("measurementTime") String measurementTime,
            @RequestParam("info") String info,
            Model model) {

        var errors = validateService.validateMeasurements(
                name, phoneNumber, address, roomDoorsCount, enterDoorsCount, measurementDate, info
        );

//        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
//        String currentEmail = currentAuth.getName();

        if (!errors.values().stream().findFirst().orElse(true)) {
            model.addAttribute("error", errors.keySet()
                    .stream()
                    .findFirst()
                    .orElse("Неизвестная ошибка"));

            return "measurements";
        }

        transactionRunner.doInTransaction(
                () -> measurementRepository.save(
                        Measurement.builder()
                                .name(name)
                                .phone(phoneNumber)
                                .address(address)
                                .city(City.valueOf(city))
                                .fabric(Fabric.valueOf(fabric))
                                .roomDoorsCount(roomDoorsCount)
                                .enterDoorsCount(enterDoorsCount)
                                .measurementDate(LocalDate.parse(measurementDate))
                                .measurementTime(LocalTime.parse(measurementTime))
                                .build()
                )
        );
        model.addAttribute("success", true);
        return "redirect:/measurements";
    }
}