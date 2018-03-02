package com.lt.sell.exception;

public class SellKillException extends RuntimeException {
    private Integer code;

    public SellKillException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
