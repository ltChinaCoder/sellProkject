package com.lt.sell.dataobject;

import com.lt.sell.enums.OrderStatusEnum;
import com.lt.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class OrderMaster {
    @Id
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

}
