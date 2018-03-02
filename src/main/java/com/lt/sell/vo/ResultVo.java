package com.lt.sell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果对象
 */
@Data
public class ResultVo implements Serializable {
    private Integer code;
    private String msg;
    private Object data;
}
