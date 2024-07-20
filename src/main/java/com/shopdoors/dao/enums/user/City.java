package com.shopdoors.dao.enums.user;

import lombok.Getter;

@Getter
public enum City {
    STERLITAMAK("Стерлитамак"),
    SALAVAT("Салават"),
    ISHIMBAY("Ишимбай"),
    TOLBAZY("Толбазы"),
    MELEUZ("Мелеуз"),
    UFA("Уфа");

    private final String rusName;

    City(String rusName) {
        this.rusName = rusName;
    }
}
