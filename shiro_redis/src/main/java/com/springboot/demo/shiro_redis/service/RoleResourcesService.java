package com.springboot.demo.shiro_redis.service;

import com.springboot.demo.shiro_redis.model.RoleResources;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:04
 * @Description:
 **/
public interface RoleResourcesService extends IService<RoleResources> {
    void addRoleResources(RoleResources roleResources);
}
