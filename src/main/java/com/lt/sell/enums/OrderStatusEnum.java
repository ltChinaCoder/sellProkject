package com.lt.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements BaseEnum {
    NEW(0, "新单"),
    COMPLETED(1, "完成的单"),
    CANCELED(2, "取消的单");
    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
