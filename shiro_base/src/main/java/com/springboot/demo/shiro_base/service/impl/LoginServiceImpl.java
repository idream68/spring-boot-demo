package com.springboot.demo.shiro_base.service.impl;

import com.springboot.demo.shiro_base.dao.UserDao;
import com.springboot.demo.shiro_base.entry.User;
import com.springboot.demo.shiro_base.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: zjhan
 * @Date: 2021/5/25 16:47
 * @Description:
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserDao userDao;

    @Override
    public User getUserByName(String getMapByName) {
        return userDao.getMapByName(getMapByName);
    }
}
