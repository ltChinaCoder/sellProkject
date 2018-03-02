package com.lt.sell.controller;

import com.lt.sell.dataobject.OrderMaster;
import com.lt.sell.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    OrderMasterService orderMasterService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "page", defaultValue = "10") Integer size, Map<String, Object> map) {
        Page<OrderMaster> orderMasters = orderMasterService.findAll(new PageRequest(page - 1, size));
        // orderMasters.getTotalPages()
        map.put("orderMasters", orderMasters);
        return new ModelAndView("order/list", map);
    }
}
