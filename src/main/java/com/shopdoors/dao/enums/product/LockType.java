package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum LockType {
    KEY("под ключ"),
    RETAINER("под фиксатор")
    ;

    private final String rusName;

    LockType(String rusName) {
        this.rusName = rusName;
    }
}
