package com.springboot.demo.shiro_redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.mapper.UserMapper;
import com.springboot.demo.shiro_redis.model.User;
import com.springboot.demo.shiro_redis.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:06
 * @Description:
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    public PageInfo<User> selectByPage(int pageNumber, int pageSize) {
        try {
            PageHelper.startPage(pageNumber, pageSize);
            List<User> users = list();
            return new PageInfo<>(users);
        } finally {
            PageHelper.clearPage();
        }
    }
}
