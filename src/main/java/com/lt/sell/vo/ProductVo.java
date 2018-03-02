package com.lt.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductVo implements Serializable {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> products;
}
