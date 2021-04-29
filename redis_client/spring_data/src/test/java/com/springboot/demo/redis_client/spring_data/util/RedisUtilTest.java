package com.springboot.demo.redis_client.spring_data.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * @Author: zjhan
 * @Date: 2021/4/26 20:36
 * @Description:
 **/
@SpringBootTest
public class RedisUtilTest {
    @Resource
    RedisUtil redisUtil;

    @Test
    public void test() {
        System.out.println(redisUtil.getPrimarySize("test"));
    }
}
