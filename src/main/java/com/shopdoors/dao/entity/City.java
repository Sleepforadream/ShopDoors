package com.shopdoors.dao.entity;

public enum City {
    Sterlitamak("Стерлитамак"),
    Salavat("Салават"),
    Ishimbay("Ишимбай"),
    Tolbazy("Толбазы"),
    Meleuz("Мелеуз"),
    Ufa("Уфа");

    private final String rusName;

    City(String rusName) {
        this.rusName = rusName;
    }
}
