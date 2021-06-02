package com.springboot.demo.shiro_redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.model.Role;
import com.springboot.demo.shiro_redis.model.User;
import org.springframework.stereotype.Service;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:05
 * @Description:
 **/
@Service
public interface UserService extends IService<User> {
    PageInfo<User> selectByPage(int pageNumber, int pageSize);
}
