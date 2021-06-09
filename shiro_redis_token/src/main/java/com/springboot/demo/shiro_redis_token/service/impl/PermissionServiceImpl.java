package com.springboot.demo.shiro_redis_token.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.shiro_redis_token.entity.Permission;
import com.springboot.demo.shiro_redis_token.mapper.PermissionMapper;
import com.springboot.demo.shiro_redis_token.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/6/9 10:35
 * @Description:
 **/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    public List<Permission> findPermissionByRole(int roleId) {
        return getBaseMapper().findPermissionByRole(roleId);
    }
}
