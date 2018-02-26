package com.lt.sell.dto;

import com.lt.sell.dataobject.OrderDetail;
import com.lt.sell.enums.OrderStatusEnum;
import com.lt.sell.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderMasterDto {
    private String orderId;
    private String buyName;
    private String buyAddress;
    private String buyPhone;
    private String buyOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    private Integer payStatus = PayStatusEnum.NOTPAY.getCode();
    private Date createTime;
    private Date updateTime;
    private List<OrderDetail> details;
}
