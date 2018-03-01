package com.lt.sell.service.impl;

import com.lt.sell.dto.OrderMasterDto;
import com.lt.sell.service.OrderMasterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {
    @Autowired
    OrderMasterService orderMasterService;
    @Test
    public void create() {
        OrderMasterDto byOrderId = orderMasterService.findByOrderId("1519630773126950803");
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        BeanUtils.copyProperties(byOrderId, orderMasterDto);
        orderMasterDto.setOrderId("");
        orderMasterService.create(orderMasterDto);
    }
}