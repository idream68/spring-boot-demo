package com.springboot.demo.security_base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zjhan
 * @Date: 2021/6/17 15:13
 * @Description:
 **/
@RestController
public class LoginController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
