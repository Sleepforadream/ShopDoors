package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum Color {
    BLACK("чёрный"),
    WHITE("белый"),
    BLUE("синий"),
    RED("красный"),
    GREEN("зелёный"),
    YELLOW("жёлтый"),
    ORANGE("оранжевый"),
    PINK("розовый"),
    PURPLE("пурпурный"),
    GRAPHITE("графитовый")
    ;

    private final String rusName;

    Color(String rusName) {
        this.rusName = rusName;
    }
}
