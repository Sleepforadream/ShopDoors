package com.shopdoors.service;

import com.shopdoors.dao.enums.user.City;
import com.shopdoors.dao.entity.user.Client;
import com.shopdoors.dao.entity.user.Employee;
import com.shopdoors.dao.enums.user.Fabric;
import com.shopdoors.dao.entity.user.Measurement;
import com.shopdoors.dao.enums.user.Position;
import com.shopdoors.dao.repository.EmployeeRepository;
import com.shopdoors.dao.repository.MeasurementRepository;
import com.shopdoors.dto.MeasurementDto;
import com.shopdoors.util.TransactionRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final EmployeeRepository employeeRepository;
    private final TransactionRunner transactionRunner;
    private final ClientService clientService;
    private final NotificationService notificationService;

    public void createEventMeasurement(MeasurementDto measurementDto) {
        log.info("Creating event measurement for email: {}", measurementDto.getEmail());
        transactionRunner.doInTransaction(
                () -> {
                    Client client = clientService.save(
                            measurementDto.getEmail(),
                            measurementDto.getPhoneNumber(),
                            measurementDto.getName(),
                            measurementDto.getSecondName(),
                            measurementDto.getThirdName()
                    );

                    List<Employee> availableMeasures = getAvailableMeasures(
                            LocalDate.parse(measurementDto.getMeasurementDate()),
                            LocalTime.parse(measurementDto.getMeasurementTime())
                    );

                    Employee appointedMeasurer = availableMeasures.stream().findAny().orElseThrow();

                    Measurement measurement = save(measurementDto, client, appointedMeasurer);

                    notificationService.notifyOnNewMeasurementToEmployee(appointedMeasurer, measurement, client);
                    notificationService.notifyOnNewMeasurementToClient(appointedMeasurer, measurement, client);
                    log.info("Successfully created measurement for email: {}", measurementDto.getEmail());
                    return measurement;
                }
        );
    }

    public Measurement save(MeasurementDto measurementDto, Client client, Employee appointedMeasurer) {
        log.info("Saving measurement for address: {}", measurementDto.getAddress());
        Measurement measurement = measurementRepository
                .findByMeasurementDateAndMeasurementTimeAndAddress(
                        LocalDate.parse(measurementDto.getMeasurementDate()),
                        LocalTime.parse(measurementDto.getMeasurementTime()),
                        measurementDto.getAddress())
                .orElse(new Measurement());

        if (measurement.getMeasurementDate() == null) {
            measurement = measurementRepository.save(
                    Measurement.builder()
                            .registerDate(LocalDate.now())
                            .address(measurementDto.getAddress())
                            .city(City.valueOf(measurementDto.getCity()))
                            .fabric(Fabric.valueOf(measurementDto.getFabric()))
                            .roomDoorsCount(measurementDto.getRoomDoorsCount())
                            .enterDoorsCount(measurementDto.getEnterDoorsCount())
                            .measurementDate(LocalDate.parse(measurementDto.getMeasurementDate()))
                            .measurementTime(LocalTime.parse(measurementDto.getMeasurementTime()))
                            .info(measurementDto.getInfo())
                            .client(client)
                            .employee(appointedMeasurer)
                            .build()
            );
        }
        log.info("Successfully saved measurement for address: {}", measurementDto.getAddress());
        return measurement;
    }

    public List<Employee> getAvailableMeasures(LocalDate date, LocalTime time) {
        log.info("Getting available measures for date: {} time {}", date, time);
        List<Employee> allMounters = employeeRepository
                .findEmployeesByPosition(Position.MOUNTER)
                .orElseThrow();
        List<Measurement> measurementsAtDateTime = measurementRepository
                .findByMeasurementDateAndMeasurementTime(date, time)
                .orElseThrow();
        List<Employee> busyEmployees = new ArrayList<>();

        for (Measurement measurement : measurementsAtDateTime) {
            busyEmployees.add(measurement.getEmployee());
        }

        allMounters.removeAll(busyEmployees);
        log.info("Successfully getting available measures for date: {} time: {}", date, time);
        return allMounters;
    }

    public Map<LocalTime, List<Employee>> getAvailableTimesPerMeasures(LocalDate date) {
        log.info("Getting available times per measures for date: {}", date);
        Map<LocalTime, List<Employee>> availableTimes = new HashMap<>();
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(18, 0);

        while (!startTime.isAfter(endTime)) {
            List<Employee> employees = getAvailableMeasures(date, startTime);
            if (!employees.isEmpty()) {
                availableTimes.put(startTime, employees);
            }
            startTime = startTime.plusMinutes(60);
        }

        log.info("Successfully getting available times per measures for date: {}", date);
        return availableTimes;
    }
}