package com.springboot.demo.aspect.controller;

import com.springboot.demo.aspect.annotation.Customer;
import com.springboot.demo.aspect.model.ResponseU;
import com.springboot.demo.aspect.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zjhan
 * @Date: 2021/6/18 15:27
 * @Description:
 **/
@RestController
public class TestController {
    @GetMapping("/getName")
    @Customer(name = "name", desc = "{} 访问")
    public String test(@RequestParam("name") String name) {
        return name;
    }

    @PostMapping("/getUserName")
    @Customer(name = "user.name", desc = "{} 访问")
    public ResponseU<User> getName(@RequestBody User user) {
        ResponseU<User> resp = new ResponseU<User>();
        resp.setCode(200);
        resp.setData(user);
        resp.setDesc(user.getName());
        return resp;
    }
}
