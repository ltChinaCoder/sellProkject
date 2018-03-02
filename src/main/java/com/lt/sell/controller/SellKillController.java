package com.lt.sell.controller;

import com.lt.sell.service.SellKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/sellkill")
public class SellKillController {
    @Autowired
    SellKillService sellKillService;

    @RequestMapping("query/{productId}")
    public String querySumByProductInfo(@PathVariable String productId) {
        return sellKillService.querySellKillByProductInfo(productId);
    }

    @RequestMapping("order/{productId}")
    public String orderProductByProductInfo(@PathVariable String productId) {
        log.info("【创建单子】： productId={}", productId);
        sellKillService.orderProductByProductInfo(productId);
        return querySumByProductInfo(productId);
    }
}
