package com.springboot.demo.mybatis_plus.controller;


import com.springboot.demo.mybatis_plus.entity.User;
import com.springboot.demo.mybatis_plus.service.UserService;
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

}
