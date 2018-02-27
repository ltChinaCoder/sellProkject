package com.lt.sell.service;

import com.lt.sell.dataobject.ProductInfo;
import com.lt.sell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findOne(String id);
    Page<ProductInfo> findAll(Pageable pageable);
    List<ProductInfo> findUpAll();
    ProductInfo save(ProductInfo productInfo);

    void increaseStock(List<CartDto> cartDtos);
    void decreaseStock(List<CartDto> cartDtos);
}
