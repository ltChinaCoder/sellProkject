package com.lt.sell.repository;

import com.lt.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    ProductInfoRepository productInfoRepository;


    @Test
    public void testSave() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("很好吃");
        productInfo.setProductId("1234");
        productInfo.setProductPrice(new BigDecimal(9));
        productInfo.setProductStock(100);
        productInfo.setProductName("扬州炒饭");
        productInfo.setProductStatus(1);
        productInfoRepository.save(productInfo);
    }
}