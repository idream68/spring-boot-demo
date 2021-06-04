package com.springboot.demo.controller;

import com.springboot.demo.service.CountGetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zjhan
 * @Date: 2021/5/11 11:50
 * @Description:
 **/
@RestController
public class CountController {
    @Autowired
    CountGetterService countGetterService;

    @GetMapping("/getCount1")
    public ResponseEntity<Integer> getCount() {
        return new ResponseEntity<>(countGetterService.getCount1(), HttpStatus.OK);
    }

    @GetMapping("/getCount2")
    public ResponseEntity<Integer> getCount1() {
        return new ResponseEntity<>(countGetterService.getCount2(), HttpStatus.OK);
    }

//    @GetMapping("/getCount3")
//    public ResponseEntity<Integer> getCount2() {
//        return new ResponseEntity<>(countGetterService.getCount3(), HttpStatus.OK);
//    }
//
//    @GetMapping("/getCount4")
//    public ResponseEntity<Integer> getCount3() {
//        return new ResponseEntity<>(countGetterService.getCount4(), HttpStatus.OK);
//    }
}
