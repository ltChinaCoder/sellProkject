package com.lt.sell.service.impl;

import com.lt.sell.exception.SellKillException;
import com.lt.sell.service.RedisLock;
import com.lt.sell.service.SellKillService;
import com.lt.sell.util.KeyGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class SellKillServiceImpl implements SellKillService {
    static Map<String, Integer> stock = new HashMap<>();
    static Map<String, Integer> products = new HashMap<>();
    static Map<String, String> orders = new HashMap<>();
    @Autowired
    RedisLock redisLock;

    {
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    @Override
    public String querySellKillByProductInfo(String productInfo) {
        return "闄愰噺浠戒负:" + products.get(productInfo) + "锛屽凡缁忓崠浜" + orders.size() +
                stock.get(productInfo);
    }

    @Override
    @Transactional
    public void orderProductByProductInfo(String productInfo) {
        String key = "createOrder" + productInfo;
        String value = System.currentTimeMillis() + 10000 + "";
        boolean getLock = redisLock.lock(key, value);
        if (!getLock)
            throw new SellKillException("鏈嶅姟鍣ㄥ紑灏忓樊浜嗭紝绛変笅璇曡瘯", 3);
        int sum = 0;
        if (!products.containsKey(productInfo)) {
            throw new SellKillException("娌℃湁璇ュ晢鍝", 0);
        } else if ((sum = stock.get(productInfo)) == 0) {
            throw new SellKillException("娲诲姩缁撴潫", 1);
        } else {

            orders.put(KeyGenerateUtil.getUniqueKey(), null);
            // int sum= stock.get(productInfo);
            sum -= 1;
            //  stock.put(productInfo,sum);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productInfo, sum);
            redisLock.unlock(key, value);
        }
    }
}
