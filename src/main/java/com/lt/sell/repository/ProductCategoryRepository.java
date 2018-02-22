package com.lt.sell.repository;

import com.lt.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer> {

}
