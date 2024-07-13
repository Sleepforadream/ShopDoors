package com.shopdoors.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MeasurementDto {
    private String name;
    private String secondName;
    private String thirdName;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String fabric;
    private int roomDoorsCount;
    private int enterDoorsCount;
    private String measurementDate;
    private String measurementTime;
    private String info;
}
