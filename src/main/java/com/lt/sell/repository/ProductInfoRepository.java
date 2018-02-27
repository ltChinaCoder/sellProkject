package com.lt.sell.repository;

import com.lt.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus(Integer productType);

    Page<ProductInfo> findByProductStatusAndCategoryType(Integer status, Integer type, Pageable pageable);

}
