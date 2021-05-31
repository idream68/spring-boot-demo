package com.springboot.demo.mybatis_plus_pagehelper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.mybatis_plus_pagehelper.entity.User;
import com.springboot.demo.mybatis_plus_pagehelper.mapper.UserMapper;
import com.springboot.demo.mybatis_plus_pagehelper.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: zjhan
 * @Date: 2021/5/28 17:30
 * @Description:
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    public int getMyCount() {
        return getBaseMapper().getCount();
    }

    public PageInfo<User> getPage(int pageNumber, int pageSize) {
        Page<User> page = PageHelper.startPage(pageNumber, pageSize);
        List<User> users = list();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

}
