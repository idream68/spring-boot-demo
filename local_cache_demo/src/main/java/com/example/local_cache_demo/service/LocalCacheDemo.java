package com.example.local_cache_demo.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Editor zjhan
 * @Date 2021/7/22 17:41
 * @Description 本地缓存使用简单方法
 */
@Service
public class LocalCacheDemo {
    private final Cache<String, String> cache = Caffeine.newBuilder()
            .maximumSize(10)
            .expireAfterAccess(1, TimeUnit.HOURS)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    /**
     * 添加数据
     * @param key
     * @param value
     */
    public void addCache(String key, String value) {
        cache.put(key, value);
    }

    /**
     * 获取单个数据
     * @param key
     * @return
     */
    public String getCache(String key) {
        return cache.getIfPresent(key);
    }

    /**
     * 获取多个数据
     * @param keys
     * @return
     */
    public Map<String, String> getCaches(List<String> keys) {
        return cache.getAllPresent(keys);
    }

}
