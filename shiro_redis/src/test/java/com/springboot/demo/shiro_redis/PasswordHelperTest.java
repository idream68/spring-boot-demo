package com.springboot.demo.shiro_redis;

import com.springboot.demo.shiro_redis.utils.PasswordHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zjhan
 * @Date: 2021/6/1 19:57
 * @Description:
 **/

@SpringBootTest
public class PasswordHelperTest {

    @Test
    public void encryptPasswordTest() {
        System.out.println(new PasswordHelper().encryptPassword("user", "123456"));
    }
}
