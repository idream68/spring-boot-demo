package com.example.local_cache_demo.controller;

import com.example.local_cache_demo.service.LocalCacheDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Editor zjhan
 * @Date 2021/7/22 17:52
 * @Description TODO
 */
@Slf4j
@RestController
public class CacheController {
    @Autowired
    LocalCacheDemo localCacheDemo;

    @GetMapping("/getValue")
    public String getCacheValue(String key) {
        return localCacheDemo.getCache(key);
    }

    @PostMapping("/saveValue")
    public boolean saveCache(@RequestBody Data data) {
        try {
            localCacheDemo.addCache(data.getKey(), data.getValue());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @GetMapping("/getValues")
    public Map<String, String> getValues(String keys) {
        return localCacheDemo.getCaches(Arrays.asList(keys.split(",")));
    }
}
