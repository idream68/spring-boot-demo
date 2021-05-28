package com.springboot.demo.mybatis_plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.mybatis_plus.entity.User;
import com.springboot.demo.mybatis_plus.mapper.UserMapper;
import com.springboot.demo.mybatis_plus.service.UserService;
import org.springframework.stereotype.Service;


/**
 * @Author: zjhan
 * @Date: 2021/5/28 17:30
 * @Description:
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    public int getMyCount() {
        return getBaseMapper().getCount();
    }
}
