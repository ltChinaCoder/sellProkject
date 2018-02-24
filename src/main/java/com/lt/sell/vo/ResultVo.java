package com.lt.sell.vo;

import lombok.Data;

/**
 * 返回结果对象
 */
@Data
public class ResultVo {
    private Integer code;
    private String msg;
    private Object data;
}
