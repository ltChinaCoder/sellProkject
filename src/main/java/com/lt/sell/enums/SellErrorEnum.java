package com.lt.sell.enums;

import lombok.Getter;

@Getter
public enum SellErrorEnum implements BaseEnum {
    PRODUCT_NOT_EXIST(1, "鍟嗗搧涓嶅瓨鍦"),
    STOCK__NOT_ENOUGH(2, "搴撳瓨涓嶈冻"),
    DETAIL_NOT_EXIST(4, "璁㈠崟璇︽儏涓嶅瓨鍦"),
    ORDER_NOT_EXIST(3, "璁㈠崟涓嶅瓨鍦"),
    ORDER_STATUS_ERROR(5, "璁㈠崟鐘舵�涓嶅"),
    ORDER_DETAIL_EMPTY(6, "璁㈠崟璇︽儏涓虹┖"),
    ORDER_STATUS_UPDATE_ERROR(7, "璁㈠崟鏇存柊鐘舵�澶辫触"),
    ORDER_PAY_STATUS_ERROR(8, "璁㈠崟浠樻鐘舵�涓嶅");
    private Integer code;
    private String msg;

    SellErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
