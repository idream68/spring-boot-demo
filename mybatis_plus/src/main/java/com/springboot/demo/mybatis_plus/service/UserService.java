package com.springboot.demo.mybatis_plus.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.mybatis_plus.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 17:29
 * @Description:
 **/

public interface UserService extends IService<User> {
    int getMyCount();
}
