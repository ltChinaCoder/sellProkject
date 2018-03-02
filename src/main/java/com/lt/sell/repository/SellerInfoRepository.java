package com.lt.sell.repository;

import com.lt.sell.dataobject.SellInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellInfo, String> {
    SellInfo findByOpenid(String openId);

    SellInfo findByUsernameAndPassword(String username, String password);
}
