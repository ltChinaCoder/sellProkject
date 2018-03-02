package com.lt.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements BaseEnum {
    NOTPAY(0, "�ϼ���"),
    PAY(1, "�¼�");
    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
