package com.lt.sell.service.impl;

import com.lt.sell.dataobject.SellInfo;
import com.lt.sell.repository.SellerInfoRepository;
import com.lt.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoImpl implements SellerService {
    @Autowired
    SellerInfoRepository sellerInfoRepository;

    @Override
    public SellInfo findByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
