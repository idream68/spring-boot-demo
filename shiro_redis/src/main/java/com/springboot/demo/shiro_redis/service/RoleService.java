package com.springboot.demo.shiro_redis.service;

import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.model.Role;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:05
 * @Description:
 **/
public interface RoleService extends IService<Role> {
    public List<Role> queryRoleListWithSelected(Integer uid);

    PageInfo<Role> selectByPage(Role role, int start, int length);

    /**
     * 删除角色 同时删除角色资源表中的数据
     * @param roleId
     */
    void delRole(Integer roleId);
}
