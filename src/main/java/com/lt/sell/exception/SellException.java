package com.lt.sell.exception;

import com.lt.sell.enums.SellErrorEnum;

public class SellException extends RuntimeException {
    private Integer code;

    public SellException(SellErrorEnum productErrorEnum) {
        super(productErrorEnum.getMsg());
        this.code = productErrorEnum.getCode();
    }
}
