package com.lt.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements BaseEnum {
    NOTPAY(0, "未付款"),
    PAY(1, "已付款");
    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
