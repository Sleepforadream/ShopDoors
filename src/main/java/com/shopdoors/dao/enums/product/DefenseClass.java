package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum DefenseClass {
    FIRST("первый"),
    SECOND("второй"),
    THIRD("третий"),
    FOURTH("четвёртый")
    ;

    private final String rusName;

    DefenseClass(String rusName) {
        this.rusName = rusName;
    }
}
