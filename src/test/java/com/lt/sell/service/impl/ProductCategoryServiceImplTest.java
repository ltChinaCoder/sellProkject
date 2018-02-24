package com.lt.sell.service.impl;

import com.lt.sell.dataobject.ProductCategory;
import com.lt.sell.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    ProductCategoryService productCategoryService;

    @Test
    public void findOne() {
        System.out.println(productCategoryService.findOne(2));
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = productCategoryService.findAll();
        for (ProductCategory productCategory : all) {
            System.out.println(productCategory);
        }
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategories = productCategoryService.findByCategoryTypeIn(Arrays.asList(55));
        //  org.junit.Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("汽配2");
        productCategory.setCategoryType(7);
        productCategoryService.save(productCategory);
    }
}