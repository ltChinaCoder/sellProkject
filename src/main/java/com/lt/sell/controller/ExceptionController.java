package com.lt.sell.controller;

import com.lt.sell.exception.SellKillException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(SellKillException.class)
    @ResponseBody
    public String sendSellKillErrorMsg(SellKillException e) {
        return e.getMessage();
    }
}
