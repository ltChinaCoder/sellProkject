package com.lt.sell.repository;

import com.lt.sell.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Test
    public void findByBuyOpenid() {
        Page<OrderMaster> byBuyOpenid = orderMasterRepository.findByBuyOpenid("183761872asdasd", new PageRequest(0, 2));
        for (OrderMaster orderMaster : byBuyOpenid) {
            System.out.println(orderMaster);
        }
    }
}