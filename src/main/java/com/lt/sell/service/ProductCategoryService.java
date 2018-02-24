package com.lt.sell.service;

import com.lt.sell.dataobject.ProductCategory;

import java.util.List;


public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);

    //更新和新增都用save
    ProductCategory save(ProductCategory productCategory);

}
