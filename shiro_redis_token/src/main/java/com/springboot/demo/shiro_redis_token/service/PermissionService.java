package com.springboot.demo.shiro_redis_token.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.shiro_redis_token.entity.Permission;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/6/9 10:35
 * @Description:
 **/
public interface PermissionService extends IService<Permission> {
    List<Permission> findPermissionByRole(int roleId);
}
