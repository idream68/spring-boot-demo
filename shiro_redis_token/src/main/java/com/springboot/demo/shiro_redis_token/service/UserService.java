package com.springboot.demo.shiro_redis_token.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.shiro_redis_token.entity.User;

/**
 * @Author: zjhan
 * @Date: 2021/6/9 10:57
 * @Description:
 **/
public interface UserService extends IService<User> {
    User getOneByAccount(String account);
}
