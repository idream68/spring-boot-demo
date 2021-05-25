package com.springboot.demo.swagger_demo.controller;

import com.springboot.demo.swagger_demo.entry.SwaggerDemoEntry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zjhan
 * @Date: 2021/5/25 14:23
 * @Description:
 **/
@Api("说明2")
@RestController
@RequestMapping("/c2")
public class SwaggerController2 {
    @GetMapping("/getId")
    @ApiOperation(value = "获取Id", notes = "获取Id测试说明", tags = {"测试说明"})
    public ResponseEntity<String> getId() {
        return new ResponseEntity<>("xx", HttpStatus.OK);
    }

    @ApiOperation("获取用户")
    @PostMapping("/getUser")
    public ResponseEntity<SwaggerDemoEntry> getUser(@RequestBody SwaggerDemoEntry s) {
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
