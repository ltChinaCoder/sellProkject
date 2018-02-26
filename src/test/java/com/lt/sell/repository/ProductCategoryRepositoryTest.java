package com.lt.sell.repository;

import com.lt.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Test
    public void testFindOne() {
        ProductCategory one = productCategoryRepository.findOne(1);
        System.out.println(one);

    }

    @Test

    public void testFindAll() {
        final List<ProductCategory> all = productCategoryRepository.findAll();
        for (ProductCategory productCategory : all) {
            System.out.println(productCategory);
        }

    }

    @Test
    public void testDeleteOne() {
        productCategoryRepository.delete(2);
    }

    @Test
    @Transactional
    public void testUpdateOne() {
        ProductCategory one = new ProductCategory();

        one.setCategoryName("123");
        one.setCategoryType(5);
        productCategoryRepository.save(one);
    }

    @Test
    public void testfindByCategoryTypeIn() {
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(3, 4, 5));
        for (ProductCategory productCategory : byCategoryTypeIn) {
            System.out.println(productCategory);
        }
    }
}