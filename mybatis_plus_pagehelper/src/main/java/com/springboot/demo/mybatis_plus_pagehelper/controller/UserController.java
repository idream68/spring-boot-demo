package com.springboot.demo.mybatis_plus_pagehelper.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.mybatis_plus_pagehelper.entity.User;
import com.springboot.demo.mybatis_plus_pagehelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 16:51
 * @Description:
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getCount")
    public ResponseEntity<Integer> getCount() {
        return new ResponseEntity<>(userService.getMyCount(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> list() {
        return new ResponseEntity<>(userService.list(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<PageInfo<User>> page(int pageNumber, int pageSize) {
        try {
           PageInfo<User> pageInfo = userService.getPage(pageNumber, pageSize);
            return new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } finally {
            PageHelper.clearPage();
        }
    }

}
