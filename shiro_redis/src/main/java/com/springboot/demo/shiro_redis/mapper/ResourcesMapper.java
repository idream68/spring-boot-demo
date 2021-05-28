package com.springboot.demo.shiro_redis.mapper;

import com.springboot.demo.shiro_redis.model.Resources;

import java.util.List;
import java.util.Map;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:02
 * @Description:
 **/
public interface ResourcesMapper {
    List<Resources> queryAll();

    List<Resources> loadUserResources(Map<String,Object> map);

    List<Resources> queryResourcesListWithSelected(Integer rid);
}
