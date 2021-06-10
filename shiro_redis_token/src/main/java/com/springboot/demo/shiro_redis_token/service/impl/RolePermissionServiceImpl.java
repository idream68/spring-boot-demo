package com.springboot.demo.shiro_redis_token.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.shiro_redis_token.entity.RolePermission;
import com.springboot.demo.shiro_redis_token.mapper.RolePermissionMapper;
import com.springboot.demo.shiro_redis_token.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * @Author: zjhan
 * @Date: 2021/6/9 10:59
 * @Description:
 **/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
