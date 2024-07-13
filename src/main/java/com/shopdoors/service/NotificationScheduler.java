package com.shopdoors.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationScheduler {

    private final NotificationService notificationService;

    @Scheduled(fixedRate = 60000)
    public void scheduleMeasurementNotifications() {
        notificationService.checkForSentMeasurementReminder();
    }
}