package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum Metal {
    THICKNESS_01("1мм"),
    THICKNESS_02("2мм"),
    THICKNESS_03("3мм"),
    THICKNESS_04("4мм"),
    THICKNESS_05("5мм"),
    THICKNESS_06("6мм"),
    THICKNESS_07("7мм"),
    THICKNESS_08("8мм"),
    THICKNESS_09("9мм"),
    THICKNESS_10("10мм"),
    THICKNESS_11("11мм"),
    THICKNESS_12("12мм")
    ;

    private final String rusName;

    Metal(String rusName) {
        this.rusName = rusName;
    }
}
