package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum MoldingType {
    TELESCOPIC("телескопический"),
    FLAT("плоский")
    ;

    private final String rusName;

    MoldingType(String rusName) {
        this.rusName = rusName;
    }
}
