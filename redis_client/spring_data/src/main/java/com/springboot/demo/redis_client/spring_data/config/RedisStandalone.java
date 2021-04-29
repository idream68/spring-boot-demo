package com.springboot.demo.redis_client.spring_data.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: zjhan
 * @Date: 2021/4/26 16:19
 * @Description: 单机模式配置，多db
 **/
@Configuration
public class RedisStandalone {
    @Value("${spring.redis.password}")
    String password;
    @Value("${spring.redis.host}")
    String host;
    @Value("${spring.redis.port}")
    Integer port;
    @Value("${spring.redis.jedis.pool.max-wait}")
    Integer maxWait;
    @Value("${spring.redis.jedis.pool.min-idle}")
    Integer maxIdle;
    @Value("${spring.redis.jedis.pool.max-idle}")
    Integer minIdle;
    @Value("${spring.redis.jedis.pool.max-active}")
    Integer maxActive;
    @Value("${spring.redis.timeout}")
    Integer timeout;
    @Value("${spring.redis.databases.primary}")
    Integer primaryDB;
    @Value("${spring.redis.databases.second}")
    Integer secondDB;


    public JedisConnectionFactory defaultRedisConnectionFactory(int db){
        return getJedisConnectionFactory(db, host, port, password);
    }

    private JedisConnectionFactory getJedisConnectionFactory(int dbIndex, String host, int port, String pwd) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(dbIndex);
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(pwd));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    public RedisTemplate defaultRedisTemplate(int db){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer(Object.class));
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer(Object.class));
        redisTemplate.setConnectionFactory(defaultRedisConnectionFactory(db));
        return redisTemplate;
    }

    @Bean(name = "primaryRedisTemplate")
    public RedisTemplate userRedis() {
        return defaultRedisTemplate(primaryDB);
    }

    @Bean(name = "secondRedisTemplate")
    public RedisTemplate basicRedis() {
        return defaultRedisTemplate(secondDB);
    }

}
