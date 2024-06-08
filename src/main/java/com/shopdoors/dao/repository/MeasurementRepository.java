package com.shopdoors.dao.repository;

import com.shopdoors.dao.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

}