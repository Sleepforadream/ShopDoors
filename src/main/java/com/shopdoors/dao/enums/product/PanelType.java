package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum PanelType {
    FOR_ENTRY("для входной двери"),
    FOR_SLOPE("для откосов")
    ;

    private final String rusName;

    PanelType(String rusName) {
        this.rusName = rusName;
    }
}
