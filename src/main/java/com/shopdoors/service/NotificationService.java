package com.shopdoors.service;

import com.shopdoors.dao.entity.Client;
import com.shopdoors.dao.entity.Employee;
import com.shopdoors.dao.entity.Measurement;
import com.shopdoors.dao.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final EmailService emailService;
    private final MeasurementRepository measurementRepository;

    public void notifyOnNewMeasurementToEmployee(Employee appointedMeasurer, Measurement measurement, Client client) {
        String message = "Поступила запись на новый замер. " +
                "Дата: " + measurement.getMeasurementDate() + ". " +
                "Время: " + measurement.getMeasurementTime() + ". " +
                "Адрес замера: " + measurement.getAddress() + ". " +
                "Телефон клиента: " + client.getPhoneNumber() + ". " +
                "Имя клиента: " + client.getFirstName() + ". " +
                "Фамилия клиента: " + client.getSecondName() + ". " +
                "Отчество клиента: " + client.getThirdName() + ".";
        emailService.sendEmail(appointedMeasurer.getEmail(), "Новая запись на замер", message);
    }

    public void notifyOnNewMeasurementToClient(Employee appointedMeasurer, Measurement measurement, Client client) {
        String message = "Вы успешно записаны на замер. " +
                "Дата: " + measurement.getMeasurementDate() + ". " +
                "Время: " + measurement.getMeasurementTime() + ". " +
                "Адрес замера: " + measurement.getAddress() + ". " +
                "Телефон замерщика: " + appointedMeasurer.getPhoneNumber() + ". " +
                "Имя замерщика: " + appointedMeasurer.getFirstName() + ".";
        emailService.sendEmail(client.getEmail(), "Запись на замер дверей KD", message);
    }

    public void notifyReminder(Measurement measurement) {
        String message = "Напоминание: Через час у вас запланирован замер. " +
                "Дата: " + measurement.getMeasurementDate() + ". " +
                "Время: " + measurement.getMeasurementTime() + ". " +
                "Адрес: " + measurement.getAddress() + ".";
        emailService.sendEmail(measurement.getEmployee().getEmail(), "Напоминание о замере", message);
        emailService.sendEmail(measurement.getClient().getEmail(), "Напоминание о замере", message);
        log.info("Notification sent to employee: {}", measurement.getEmployee().getEmail());
    }

    public void checkForSentMeasurementReminder() {
        LocalTime timeNow = LocalTime.now();
        LocalTime oneHourLaterTime = timeNow.plusHours(1);
        LocalDate dateNow = LocalDate.now();

        List<Measurement> measurements = measurementRepository
                .findByMeasurementTimeBetweenAndMeasurementDate(timeNow, oneHourLaterTime, dateNow)
                .orElseThrow();

        for (Measurement measurement : measurements) {
            boolean isSent = measurement.isReminderSent();
            boolean isTimeCome = measurement.getMeasurementTime().isBefore(LocalTime.now().plusHours(1));
            boolean isDateCome = measurement.getMeasurementDate().equals(LocalDate.now());

            if (isSent && isTimeCome && isDateCome) {
                notifyReminder(measurement);
                measurement.setReminderSent(true);
                measurementRepository.save(measurement);
            }
        }
    }
}
