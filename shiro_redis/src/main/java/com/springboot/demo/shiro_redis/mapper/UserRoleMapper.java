package com.springboot.demo.shiro_redis.mapper;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:02
 * @Description:
 **/
public interface UserRoleMapper {
    List<Integer> findUserIdByRoleId(Integer roleId);
}
