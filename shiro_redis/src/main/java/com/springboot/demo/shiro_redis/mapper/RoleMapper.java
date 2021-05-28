package com.springboot.demo.shiro_redis.mapper;

import com.springboot.demo.shiro_redis.model.Role;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:02
 * @Description:
 **/
public interface RoleMapper {
    List<Role> queryRoleListWithSelected(Integer id);
}
