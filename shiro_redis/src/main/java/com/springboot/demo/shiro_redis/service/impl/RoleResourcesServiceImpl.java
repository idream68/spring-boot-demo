package com.springboot.demo.shiro_redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.shiro_redis.mapper.RoleResourcesMapper;
import com.springboot.demo.shiro_redis.model.RoleResources;
import com.springboot.demo.shiro_redis.service.RoleResourcesService;
import org.springframework.stereotype.Service;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:06
 * @Description:
 **/
@Service
public class RoleResourcesServiceImpl extends ServiceImpl<RoleResourcesMapper, RoleResources> implements RoleResourcesService {
}
