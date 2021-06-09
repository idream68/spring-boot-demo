package com.springboot.demo.shiro_redis_token.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.shiro_redis_token.entity.User;
import com.springboot.demo.shiro_redis_token.mapper.UserMapper;
import com.springboot.demo.shiro_redis_token.service.UserService;

/**
 * @Author: zjhan
 * @Date: 2021/6/9 11:01
 * @Description:
 **/
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    public User getOneByAccount(String account) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        return getOne(queryWrapper);
    }
}
