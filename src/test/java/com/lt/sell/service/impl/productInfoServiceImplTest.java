package com.lt.sell.service.impl;

import com.lt.sell.dataobject.ProductInfo;
import com.lt.sell.repository.ProductInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class productInfoServiceImplTest {
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Test
    public void save() {
        ProductInfo one = productInfoRepository.findOne("1234");
        one.setProductPrice(new BigDecimal(20));
        productInfoRepository.save(one);
    }
}