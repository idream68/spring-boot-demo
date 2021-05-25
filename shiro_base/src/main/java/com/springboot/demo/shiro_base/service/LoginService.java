package com.springboot.demo.shiro_base.service;

import com.springboot.demo.shiro_base.entry.User;

/**
 * @Author: zjhan
 * @Date: 2021/5/25 16:47
 * @Description:
 **/
public interface LoginService {
    public User getUserByName(String getMapByName);
}
