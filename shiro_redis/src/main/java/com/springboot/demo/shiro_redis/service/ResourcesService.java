package com.springboot.demo.shiro_redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.model.Resources;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:04
 * @Description:
 **/
@Service
public interface ResourcesService extends IService<Resources> {
    PageInfo<Resources> selectByPage(int pageNumber, int pageSize);

    List<Resources> userResources(int userId);
}
