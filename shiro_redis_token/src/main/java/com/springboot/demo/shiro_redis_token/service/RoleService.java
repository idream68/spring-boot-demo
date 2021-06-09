package com.springboot.demo.shiro_redis_token.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.shiro_redis_token.entity.Role;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/6/9 10:57
 * @Description:
 **/
public interface RoleService extends IService<Role> {
    List<Role> findRoleByUserAccount(String account);
}
