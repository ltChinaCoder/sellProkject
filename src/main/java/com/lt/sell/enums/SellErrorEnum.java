package com.lt.sell.enums;

import lombok.Getter;

@Getter
public enum SellErrorEnum implements BaseEnum {
    PRODUCT_NOT_EXIST(1, "商品不存在"),
    STOCK__NOT_ENOUGH(2, "库存不足"),
    DETAIL_NOT_EXIST(4, "订单详情不存在"),
    ORDER_NOT_EXIST(3, "订单不存在"),
    ORDER_STATUS_ERROR(5, "订单状态不对"),
    ORDER_DETAIL_EMPTY(6, "订单详情为空"),
    ORDER_STATUS_UPDATE_ERROR(7, "订单更新状态失败"),
    ORDER_PAY_STATUS_ERROR(8, "订单付款状态不对");
    private Integer code;
    private String msg;

    SellErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
