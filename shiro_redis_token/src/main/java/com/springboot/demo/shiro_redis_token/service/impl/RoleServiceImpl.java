package com.springboot.demo.shiro_redis_token.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.shiro_redis_token.entity.Role;
import com.springboot.demo.shiro_redis_token.mapper.RoleMapper;
import com.springboot.demo.shiro_redis_token.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/6/9 11:00
 * @Description:
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    public List<Role> findRoleByUserAccount(String account) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        return list(queryWrapper);
    }
}
