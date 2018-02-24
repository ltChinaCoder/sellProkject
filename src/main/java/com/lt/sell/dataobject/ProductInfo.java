package com.lt.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class ProductInfo {
    @Id
    private String productId;
    private String productDescription;
    private String productName;
    private String productIcon;
    private BigDecimal productPrice;
    private Integer productStock;
    private Integer categoryType;
    private Integer productStatus;
}
