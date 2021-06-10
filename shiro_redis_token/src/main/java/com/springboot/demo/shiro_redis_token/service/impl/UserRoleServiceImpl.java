package com.springboot.demo.shiro_redis_token.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.shiro_redis_token.entity.UserRole;
import com.springboot.demo.shiro_redis_token.mapper.UserRoleMapper;
import com.springboot.demo.shiro_redis_token.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @Author: zjhan
 * @Date: 2021/6/9 11:01
 * @Description:
 **/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
