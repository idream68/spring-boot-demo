package com.springboot.demo.mybatis_pagehelper.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.mybatis_pagehelper.entity.User;
import com.springboot.demo.mybatis_pagehelper.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/12 17:48
 * @Description:
 **/
@Service
public class PageGetter {
    @Resource
    UserMapper userMapper;

    public PageInfo<User> getPage(int page, int size, String orderBy) {
        if (orderBy == null || orderBy.equals("")) {
            PageHelper.startPage(page, size);
        } else {
            PageHelper.startPage(page, size, orderBy);
        }
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.getUsers());
        int pageNum = pageInfo.getPageNum();
        int pageSize = pageInfo.getPageSize();
        long total = pageInfo.getTotal();
        long pages = pageInfo.getPages();
        List<User> users = pageInfo.getList();
        System.out.println("pageNum: " + pageNum);
        System.out.println("pageSize: " + pageSize);
        System.out.println("total: " + total);
        System.out.println("pages: " + pages);
        return pageInfo;
    }

}
