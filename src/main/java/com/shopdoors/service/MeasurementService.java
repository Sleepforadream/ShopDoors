package com.shopdoors.service;

import com.shopdoors.dao.entity.City;
import com.shopdoors.dao.entity.Client;
import com.shopdoors.dao.entity.Employee;
import com.shopdoors.dao.entity.Fabric;
import com.shopdoors.dao.entity.Measurement;
import com.shopdoors.dao.entity.Position;
import com.shopdoors.dao.repository.EmployeeRepository;
import com.shopdoors.dao.repository.MeasurementRepository;
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
    private final EmailService emailService;

    public void createEventMeasurement(
            String email,
            String phoneNumber,
            String name,
            String secondName,
            String thirdName,
            String address,
            String city,
            String fabric,
            int roomDoorsCount,
            int enterDoorsCount,
            String measurementDate,
            String measurementTime,
            String info
    ) {
        log.info("Creating event measurement for email: {}", email);
        transactionRunner.doInTransaction(
                () -> {
                    Client client = clientService.save(email, phoneNumber, name, secondName, thirdName);

                    List<Employee> availableMeasures = getAvailableMeasures(
                            LocalDate.parse(measurementDate),
                            LocalTime.parse(measurementTime)
                    );

                    Employee appointedMeasurer = availableMeasures.stream().findAny().orElseThrow();

                    Measurement measurement = save(
                            address,
                            city,
                            fabric,
                            roomDoorsCount,
                            enterDoorsCount,
                            measurementDate,
                            measurementTime,
                            info,
                            client,
                            appointedMeasurer
                    );

                    sendNotificationOnNewMeasurement(appointedMeasurer, measurement, client);
                    log.info("Successfully created measurement for email: {}", email);
                    return measurement;
                }
        );
    }

    public Measurement save(
            String address,
            String city,
            String fabric,
            int roomDoorsCount,
            int enterDoorsCount,
            String measurementDate,
            String measurementTime,
            String info,
            Client client,
            Employee appointedMeasurer
    ) {
        log.info("Saving measurement for address: {}", address);
        Measurement measurement = measurementRepository
                .findByMeasurementDateAndMeasurementTimeAndAddress(
                        LocalDate.parse(measurementDate), LocalTime.parse(measurementTime), address)
                .orElse(new Measurement());

        if (measurement.getMeasurementDate() == null) {
            measurement = measurementRepository.save(
                    Measurement.builder()
                            .registerDate(LocalDate.now())
                            .address(address)
                            .city(City.valueOf(city))
                            .fabric(Fabric.valueOf(fabric))
                            .roomDoorsCount(roomDoorsCount)
                            .enterDoorsCount(enterDoorsCount)
                            .measurementDate(LocalDate.parse(measurementDate))
                            .measurementTime(LocalTime.parse(measurementTime))
                            .info(info)
                            .client(client)
                            .employee(appointedMeasurer)
                            .build()
            );
        }
        log.info("Successfully saved measurement for address: {}", address);
        return measurement;
    }

    private void sendNotificationOnNewMeasurement(Employee appointedMeasurer, Measurement measurement, Client client) {
        String message = "Поступила запись на новый замер. " +
                "Дата: " + measurement.getMeasurementDate() + ". " +
                "Время: " + measurement.getMeasurementTime() + ". " +
                "Адрес замера: " + measurement.getAddress() + ". " +
                "Телефон клиента: " + client.getPhoneNumber() + ". " +
                "Имя клиента: " + client.getFirstName() + ". " +
                "Фамилия клиента: " + client.getSecondName() + ". " +
                "Отчество клиента: " + client.getThirdName() + ". ";
        emailService.sendSEmail(appointedMeasurer.getEmail(), "Новая запись на замер", message);
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