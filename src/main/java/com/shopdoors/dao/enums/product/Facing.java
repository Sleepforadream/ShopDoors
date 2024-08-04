package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum Facing {
    SPL("spl"),
    METAL("метал"),
    PLASTIC("пластик"),
    LAMINATE("ламинатин"),
    ENAMEL("эмаль"),
    CORTEX("кортекс"),
    VENEER("натуральный шпон"),
    ECO_VENEER("эко-шпон"),
    MDF_PANEL("мдф-панель")
    ;

    private final String rusName;

    Facing(String rusName) {
        this.rusName = rusName;
    }
}
