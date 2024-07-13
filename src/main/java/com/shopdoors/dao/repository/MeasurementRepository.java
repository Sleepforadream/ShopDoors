package com.shopdoors.dao.repository;

import com.shopdoors.dao.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    Optional<List<Measurement>> findByMeasurementDate(LocalDate measurementDate);

    Optional<List<Measurement>> findByMeasurementDateAndMeasurementTime(LocalDate measurementDate, LocalTime measurementTime);

    Optional<Measurement> findByMeasurementDateAndMeasurementTimeAndAddress(
            LocalDate measurementDate, LocalTime measurementTime, String address
    );
}