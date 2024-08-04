package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum WaterResistanceType {
    WATER_RESISTANCE("есть влагостойкость"),
    PARTIAL_WATER_RESISTANCE("частичная влагостойкость"),
    NO_WATER_RESISTANCE("нет влагостойкости")
    ;

    private final String rusName;

    WaterResistanceType(String rusName) {
        this.rusName = rusName;
    }
}
