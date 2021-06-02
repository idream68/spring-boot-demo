package com.springboot.demo.shiro_redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:05
 * @Description:
 **/
@Service
public interface RoleService extends IService<Role> {
    PageInfo<Role> selectByPage(int pageNumber, int pageSize);
}
