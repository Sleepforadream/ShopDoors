package com.shopdoors.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "measurement", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"measurement_date", "measurement_time", "address"})
})
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;

    @Column(length = 1000, nullable = false)
    private String address;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 100, nullable = false)
    private City city;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 100, nullable = false)
    private Fabric fabric;

    @Column(length = 100, nullable = false)
    private int roomDoorsCount;

    @Column(length = 100, nullable = false)
    private int enterDoorsCount;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate measurementDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "hh-mm")
    private LocalTime measurementTime;

    @Column(length = 1000)
    private String info;

    @Column
    private boolean reminderSent;

    @ManyToOne(targetEntity = Client.class, fetch = FetchType.LAZY)
    private Client client;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    private Employee employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return roomDoorsCount == that.roomDoorsCount
                && enterDoorsCount == that.enterDoorsCount
                && Objects.equals(id, that.id)
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