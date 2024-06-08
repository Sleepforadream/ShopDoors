package com.shopdoors.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 12, nullable = false, unique = true)
    private String phone;

    @Column(length = 1000, nullable = false, unique = true)
    private String address;

    @Column(length = 100)
    private City city;

    @Column(length = 100)
    private Fabric fabric;

    @Column(length = 100)
    private int roomDoorsCount;

    @Column(length = 100)
    private int enterDoorsCount;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate measurementDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "hh-mm")
    private LocalTime measurementTime;

    @Column(length = 1000)
    private String info;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return roomDoorsCount == that.roomDoorsCount
                && enterDoorsCount == that.enterDoorsCount
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(phone, that.phone)
                && Objects.equals(address, that.address)
                && Objects.equals(city, that.city)
                && Objects.equals(fabric, that.fabric)
                && Objects.equals(measurementDate, that.measurementDate)
                && Objects.equals(measurementTime, that.measurementTime)
                && Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                phone,
                address,
                city,
                fabric,
                roomDoorsCount,
                enterDoorsCount,
                measurementDate,
                measurementTime,
                info
        );
    }
}