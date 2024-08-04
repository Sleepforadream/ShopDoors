package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum PeepholeType {
    LIGHT_ENHANCED("светоусиленный"),
    BULLETPROOF("пуленепробиваемый"),
    ELECTRIC("электронный")
    ;

    private final String rusName;

    PeepholeType(String rusName) {
        this.rusName = rusName;
    }
}
