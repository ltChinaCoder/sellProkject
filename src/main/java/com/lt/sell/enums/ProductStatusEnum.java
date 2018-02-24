package com.lt.sell.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    up(0, "在架"),
    down(1, "下架");
    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
