package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum KeyRetainer {
    KEY_KEY("ключ-ключ"),
    KEY_RETAINER("ключ-фиксатор"),
    RETAINER("фиксатор")
    ;

    private final String rusName;

    KeyRetainer(String rusName) {
        this.rusName = rusName;
    }
}
