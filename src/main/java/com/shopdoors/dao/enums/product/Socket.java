package com.shopdoors.dao.enums.product;

import lombok.Getter;

@Getter
public enum Socket {
    SQUARE_THICK("толстое квадратное"),
    SQUARE_THIN("тонкое квадратное"),
    ROUND_THICK("толстое круглое"),
    ROUND_THIN("тонкое круглое"),
    WITHOUT_SOCKET("без обрамления")
    ;

    private final String rusName;

    Socket(String rusName) {
        this.rusName = rusName;
    }
}
