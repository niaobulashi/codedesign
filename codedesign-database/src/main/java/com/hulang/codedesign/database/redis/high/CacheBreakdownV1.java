package com.hulang.codedesign.database.redis.high;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @description: 缓存击穿普通版本
 * @date: 2024/3/12 21:39
 * @author: HuLang
 * @version: V1.0
 **/
public class CacheBreakdownV1 {

    private RedisTemplate redisTemplate;
    /*public ExpressInfo findByDeliveryOrderId(Long id) {
        String key = "express:express-info";
        // 从Redis查询物流信息
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj == null) {
            // 数据库查询
            ExpressInfo expressInfo = expressMapper.selectByDeliveryOrderId(id);
            if (expressInfo != null) {
                redisTemplate.opsForValue().set(key + i, expressInfo);
                return expressInfo;
            } else {
                throw new RuntimeException("物流信息不存在");
            }
        }
        return (ExpressInfo) obj;
    }*/
}
