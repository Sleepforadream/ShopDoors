package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum HingeType {
    HIDE("скрытые"),
    INVOICE("накладные"),
    INSERT("врезные")
    ;

    private final String rusName;

    HingeType(String rusName) {
        this.rusName = rusName;
    }
}
