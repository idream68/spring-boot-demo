package com.springboot.demo.shiro_redis.service;

import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.model.User;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:05
 * @Description:
 **/
public interface UserService extends IService<User> {
    PageInfo<User> selectByPage(User user, int start, int length);

    User selectByUsername(String username);

    void delUser(Integer userid);
}
