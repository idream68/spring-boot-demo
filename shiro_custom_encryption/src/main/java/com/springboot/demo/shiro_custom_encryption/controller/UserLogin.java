package com.springboot.demo.shiro_custom_encryption.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zjhan
 * @Date: 2021/6/11 17:10
 * @Description:
 **/
@RestController
public class UserLogin {
    @PostMapping("/login")
    public String login() {
        return "xx";
    }

    @GetMapping("/logout")
    @RequiresPermissions("xx")
    public String logout() {
        return "yy";
    }
}
