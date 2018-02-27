package com.lt.sell.service.impl;

import com.lt.sell.dataobject.OrderDetail;
import com.lt.sell.dto.OrderMasterDto;
import com.lt.sell.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {
    @Autowired
    OrderMasterService orderMasterService;

    @Test
    public void create() {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        orderMasterDto.setBuyAddress("北京");
        orderMasterDto.setBuyName("刘涛");
        orderMasterDto.setBuyOpenid("110110");
        orderMasterDto.setBuyPhone("18813011951");
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1234");
        orderDetail.setProductQuantity(50);
        orderDetails.add(orderDetail);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("12344");
        orderDetail2.setProductQuantity(100);
        orderDetails.add(orderDetail2);
        orderMasterDto.setDetails(orderDetails);
        orderMasterService.create(orderMasterDto);
    }

    @Test
    public void testFind() {
        OrderMasterDto byOrderId = orderMasterService.findByOrderId("1519630649722311926");
        //  orderMasterService.cancelOrder(byOrderId);
        orderMasterService.payOrder(byOrderId);
    }
}