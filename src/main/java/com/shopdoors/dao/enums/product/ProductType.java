package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum ProductType {
    ROOM_DOOR("межкомнатная дверь"),
    ENTRY_DOOR("входная дверь"),
    HANDLE("дверная ручка"),
    ROOM_HINGE("межкомнатная петля"),
    ROOM_LOCK("межкомнатный замок"),
    ENTRY_LOCK("входной замок"),
    RETAINER("дверной фиксатор"),
    PEEPHOLE("глазок"),
    FASTENING("крепление для плинтуса"),
    ADDITIONAL_ELEMENT("доборный элемент"),
    JAMB("дверной наличник"),
    PANNIER("дверной короб"),
    BASEBOARD("плинтус"),
    PANEL("панель"),
    FLOOR_COVERING("напольное покрытие")
    ;

    private final String rusName;

    ProductType(String rusName) {
        this.rusName = rusName;
    }
}
