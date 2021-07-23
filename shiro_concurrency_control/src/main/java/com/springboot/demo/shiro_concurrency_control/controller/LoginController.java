package com.springboot.demo.shiro_concurrency_control.controller;

import com.springboot.demo.shiro_concurrency_control.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zjhan
 * @Date: 2021/6/17 14:22
 * @Description:
 **/
@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(User user) {
        SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUsername(),user.getPassword()));
        return "su";
    }
}
