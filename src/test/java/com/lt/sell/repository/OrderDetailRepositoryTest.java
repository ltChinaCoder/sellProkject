package com.lt.sell.repository;

import com.lt.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void test() {
        List<OrderDetail> byOrderId = orderDetailRepository.findByOrderId("1");
        for (OrderDetail orderDetail : byOrderId) {
            System.out.println(orderDetail);
        }


    }
}