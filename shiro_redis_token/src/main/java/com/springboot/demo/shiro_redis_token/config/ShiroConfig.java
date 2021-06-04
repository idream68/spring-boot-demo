package com.springboot.demo.shiro_redis_token.config;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @Author: zjhan
 * @Date: 2021/6/4 16:31
 * @Description:
 **/
@Configuration
public class ShiroConfig {
    String host;

    String password;

    int timeout;

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setTimeout(timeout);
        if (StringUtils.hasText(password)){
            redisManager.setPassword(password);
        }
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        设置key前缀
        redisCacheManager.setKeyPrefix("cache-demo");
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }
}
