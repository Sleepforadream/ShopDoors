package com.shopdoors.dao.enums.user;

public enum Fabric {
    SOFIA("София"),
    KD("Краснодеревщик"),
    MEZZO_PORTE("Меццо Порте"),
    SYNERGY("Синержи"),
    VDK("Владимирская Дверная Компания"),
    VELL_DORIS("Велл Дорис"),
    LINE_DOOR("Лайн Дор"),
    TOREX("Торекс"),
    GUARDIAN("Гардиан"),
    MORELLI("Морелли"),
    PUNTO("Пунто"),
    ARMADILLO("Армадилло"),
    FUARO("Фуаро"),
    ;

    private final String rusName;

    Fabric(String rusName) {
        this.rusName = rusName;
    }
}
