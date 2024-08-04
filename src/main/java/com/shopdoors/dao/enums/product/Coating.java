package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum Coating {
    CHROME("хром"),
    MATTE_CHROME("матовый хром"),
    GOLD("золото"),
    MATTE_GOLD("матовое золото"),
    BRONZE("бронза"),
    BLACK("чёрный"),
    WHITE("белый"),
    GRAPHITE("графит")
    ;

    private final String rusName;

    Coating(String rusName) {
        this.rusName = rusName;
    }
}
