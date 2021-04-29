package com.springboot.demo.redis_client.spring_data.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: zjhan
 * @Date: 2021/4/26 16:05
 * @Description:
 **/
@Service
public class RedisUtil {
//    @Resource
//    RedisTemplate<String, String> redisTemplate;

//    单机模式2：使用自己写的配置文件
    @Resource(name = "primaryRedisTemplate")
    RedisTemplate<String, String> primaryRedisTemplate;
//
//    @Resource(name = "secondRedisTemplate")
//    RedisTemplate<String, String> secondRedisTemplate;

    public String getPrimarySize(String key) {
        return primaryRedisTemplate.opsForValue().get(key);
    }
}
