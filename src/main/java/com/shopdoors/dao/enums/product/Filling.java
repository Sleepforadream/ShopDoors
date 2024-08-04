package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum Filling {
    SOLID_PINE("массив сосны"),
    SOLID_OAK("массив дуба"),
    LVL_TIMBER("lvl брус"),
    MDF("мдф"),
    FOAM_POLYURETHANE("вспененный пенополиуретан"),
    MINERAL_WADDING("минеральная вата"),
    METAL("метал"),
    BUNG("пробка"),
    NONE("нет наполнения");

    private final String rusName;

    Filling(String rusName) {
        this.rusName = rusName;
    }
}
