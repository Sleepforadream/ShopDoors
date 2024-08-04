package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum TongueType {
    MECHANICAL("механический"),
    MAGNETIC("магнитный")
    ;

    private final String rusName;

    TongueType(String rusName) {
        this.rusName = rusName;
    }
}
