package com.springboot.demo.shiro_redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.mapper.RoleMapper;
import com.springboot.demo.shiro_redis.model.Role;
import com.springboot.demo.shiro_redis.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:06
 * @Description:
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    public PageInfo<Role> selectByPage(int pageNumber, int pageSize) {
        try {
            PageHelper.startPage(pageNumber, pageSize);
            List<Role> roles = list();
            return new PageInfo<>(roles);
        } finally {
            PageHelper.clearPage();
        }
    }
}
