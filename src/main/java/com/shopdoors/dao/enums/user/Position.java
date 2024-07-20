package com.shopdoors.dao.enums.user;

import lombok.Getter;

@Getter
public enum Position {
    MANAGER("Менеджер"),
    MOUNTER("Установщик"),
    SHOP_ASSISTANT("Продавец-консультант"),
    DIRECTOR("Директор");

    private final String rusName;

    Position(String rusName) {
        this.rusName = rusName;
    }
}
