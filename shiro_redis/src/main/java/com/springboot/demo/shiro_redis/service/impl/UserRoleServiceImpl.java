package com.springboot.demo.shiro_redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.shiro_redis.mapper.UserRoleMapper;
import com.springboot.demo.shiro_redis.model.UserRole;
import com.springboot.demo.shiro_redis.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:06
 * @Description:
 **/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
