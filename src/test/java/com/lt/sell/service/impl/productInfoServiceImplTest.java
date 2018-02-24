package com.lt.sell.service.impl;

import com.lt.sell.dataobject.ProductInfo;
import com.lt.sell.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class productInfoServiceImplTest {
    @Autowired
    ProductInfoService productInfoService;

    @Test
    public void test() {
//       List<ProductInfo> upAll = productInfoService.findUpAll();
//       for (ProductInfo productInfo : upAll) {
//           System.out.println(productInfo);
//       }
        Page<ProductInfo> all = productInfoService.findAll(new PageRequest(0, 1));
        for (ProductInfo productInfo : all) {
            System.out.println(productInfo);
        }
    }

    @Test
    public void main() {
        if ((23 == 23) || (100 / 0 == 0))
            System.out.println("yes");
        else
            System.out.println("no");
    }
}