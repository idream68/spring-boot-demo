package com.springboot.demo.shiro_redis_token.config;

import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * @Author: zjhan
 * @Date: 2021/6/9 15:30
 * @Description:
 **/
@Service
public class TokenCache {
    @Value("spring.redis.host")
    String host;
    @Value("spring.redis.password")
    String password;
    @Value("spring.redis.timeout")
    int timeout;

    private RedisManager redisManager;

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    @PostConstruct
    public void redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setTimeout(timeout);
        if (StringUtils.hasText(password)){
            redisManager.setPassword(password);
        }
        this.redisManager = redisManager;
    }

    public RedisManager getRedisManager() {
        return this.redisManager;
    }
}
