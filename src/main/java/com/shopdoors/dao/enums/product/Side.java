package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum Side {
    LEFT("левая"),
    RIGHT("правая"),
    UNIVERSAL("универсальная");

    private final String rusName;

    Side(String rusName) {
        this.rusName = rusName;
    }
}
