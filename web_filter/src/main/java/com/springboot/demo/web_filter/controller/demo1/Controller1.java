package com.springboot.demo.web_filter.controller.demo1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zjhan
 * @Date: 2021/4/22 16:43
 * @Description:
 **/
@RestController
@RequestMapping("/web_filter/demo1")
public class Controller1 {
    @GetMapping("/get")
    public ResponseEntity get() {
        return new ResponseEntity("demo1", HttpStatus.OK);
    }

}
