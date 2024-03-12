package com.hulang.codedesign.database.redis.high;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.List;

/**
 * @description: 解决缓存击穿V2
 * 1. 对热点数据设置永不过期，或者算法计算出热点数据动态给定过期时间
 * 2. 使用加锁排队，使用synchronized关键字即可，大道至简
 * @date: 2024/3/12 21:39
 * @author: HuLang
 * @version: V1.0
 **/
public class CacheBreakdownV2 {

    private RedisTemplate redisTemplate;

    private RedissonClient redissonClient;


    /**
     * 查询商品分类信息
     * @param id
     * @return
     */
    public List<ProductCategory> findByDeliveryOrderId(Long id) {

        String key = "product:product-categroy";
        // 布隆过滤器
        RBloomFilter<Long> bloomFilter = redissonClient.getBloomFilter("key");
        if (!bloomFilter.contains(id)) {
            throw new RuntimeException("商品分类信息不存在");
        }

        // 从Redis查询查询商品分类信息
        Object obj = redisTemplate.opsForValue().get(key);
        List<ProductCategory> productCategoryList = null;
        if (obj == null) {
            synchronized (this) {
                // 进入 synchronized 时，再次查询，防止上一个抢到锁的线程已经更新了缓存
                obj = redisTemplate.opsForValue().get(key);
                if (obj != null) {
                    return (List<ProductCategory>) obj;
                }
                productCategoryList = selectByDeliveryOrderId(id);
                // 避免缓存雪崩，设置随机失效时间
                Duration expire = Duration.ofHours(2L).plus(Duration.ofSeconds((int) (Math.random() * 100)));
                redisTemplate.opsForValue().set(key, productCategoryList, expire);
            }
            return productCategoryList;
        }
        return productCategoryList;
    }


    private List<ProductCategory> selectByDeliveryOrderId(Long id) {
        return null;
    }
    /**
     * @description: ProductCategory
     * @date: 2024/3/12 21:51
     * @author: HuLang
     * @version: V1.0
     **/
    class ProductCategory {
        public String productId;
        public String productName;
        public String productCategory;
        public String productPrice;
        public String productImg;
        public String productDesc;
    }
}
