package com.springboot.demo.shiro_redis.service;

import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.model.Resources;

import java.util.List;
import java.util.Map;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:04
 * @Description:
 **/
public interface ResourcesService extends IService<Resources> {
    PageInfo<Resources> selectByPage(Resources resources, int start, int length);

    List<Resources> queryAll();

    List<Resources> loadUserResources(Map<String,Object> map);

    List<Resources> queryResourcesListWithSelected(Integer rid);
}
