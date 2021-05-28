package com.springboot.demo.shiro_redis.service;

import com.springboot.demo.shiro_redis.model.UserRole;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:04
 * @Description:
 **/
public interface UserRoleService extends IService<UserRole> {
    void addUserRole(UserRole userRole);
}
