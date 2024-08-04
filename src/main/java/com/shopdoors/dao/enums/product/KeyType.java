package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum KeyType {
    SUVALDNY("сувальдный"),
    CYLINDER("цилиндровый"),
    ELECTRIC("электронный"),
    NONE("нет")
    ;

    private final String rusName;

    KeyType(String rusName) {
        this.rusName = rusName;
    }
}
