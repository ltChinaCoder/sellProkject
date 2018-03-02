package com.lt.sell.service;

import com.lt.sell.dataobject.SellInfo;

public interface SellerService {
    public SellInfo findByOpenid(String openid);
}
