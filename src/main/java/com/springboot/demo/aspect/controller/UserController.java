package com.springboot.demo.aspect.controller;

import com.springboot.demo.aspect.annotation.WebLog;
import com.springboot.demo.aspect.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/aspects")
public class UserController {
    @GetMapping("/getUser")
    public User getUser(@RequestParam("name") String name,
                        @RequestParam("age") int age) {
        return new User(name, age);
    }

    @WebLog("del")
    @PostMapping("/delUser")
    public boolean del(@RequestParam("name") String name) {
        new User().setName(name);
        return true;
    }
}
