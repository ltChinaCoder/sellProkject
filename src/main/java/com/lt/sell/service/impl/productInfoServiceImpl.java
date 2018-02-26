package com.lt.sell.service.impl;

import com.lt.sell.dataobject.ProductInfo;
import com.lt.sell.dto.CartDto;
import com.lt.sell.enums.ProductStatusEnum;
import com.lt.sell.enums.SellErrorEnum;
import com.lt.sell.exception.SellException;
import com.lt.sell.repository.ProductInfoRepository;
import com.lt.sell.service.ProductInfoService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class productInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoRepository productInfoRepository;

    public productInfoServiceImpl() {
        super();
    }

    @Override
    public ProductInfo findOne(String id) {
        return productInfoRepository.findOne(id);
    }

    @Override
    public Page<ProductInfo> findAll(org.springframework.data.domain.Pageable pageable) {

        return productInfoRepository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.up.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {

        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtos) {
        Assert.assertNotNull(cartDtos);
        for (CartDto cartDto : cartDtos) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if (productInfo == null)
                throw new SellException(SellErrorEnum.PRODUCT_NOT_EXIST);
            productInfo.setProductStock(productInfo.getProductStock() + cartDto.getProductQuantity());
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtos) {
        Assert.assertNotNull(cartDtos);
        for (CartDto cartDto : cartDtos) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if (productInfo == null)
                throw new SellException(SellErrorEnum.PRODUCT_NOT_EXIST);
            productInfo.setProductStock(productInfo.getProductStock() - cartDto.getProductQuantity());
            productInfoRepository.save(productInfo);
        }
    }
}
