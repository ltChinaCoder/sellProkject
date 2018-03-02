package com.lt.sell.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean lock(String key, String value) {
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        //检查是否发生死锁
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if (currentValue != null && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if (oldValue != null && oldValue.equals(currentValue))
                return true;
        }
        return false;
    }

    public void unlock(String key, String value) {
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if (currentValue != null && currentValue.equals(value))
            stringRedisTemplate.opsForValue().getOperations().delete(key);
    }
}

