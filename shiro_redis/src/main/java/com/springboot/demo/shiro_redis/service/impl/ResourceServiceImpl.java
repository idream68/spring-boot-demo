package com.springboot.demo.shiro_redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.mapper.ResourcesMapper;
import com.springboot.demo.shiro_redis.model.Resources;
import com.springboot.demo.shiro_redis.service.ResourcesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:05
 * @Description:
 **/
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

    @Override
    public PageInfo<Resources> selectByPage(int pageNumber, int pageSize) {
        try {
            PageHelper.startPage(pageNumber, pageSize);
            List<Resources> users = list();
            return new PageInfo<>(users);
        } finally {
            PageHelper.clearPage();
        }
    }

    public List<Resources> userResources(int userId) {
        return getBaseMapper().userResources(userId);
    }
}
