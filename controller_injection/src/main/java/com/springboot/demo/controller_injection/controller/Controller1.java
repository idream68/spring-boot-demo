package com.springboot.demo.controller_injection.controller;

import com.springboot.demo.controller_injection.entry.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zjhan
 * @Date: 2021/5/6 17:00
 * @Description:
 **/
@RestController
@RequestMapping("/controller1")
public class Controller1 {
    @Autowired
    Controller2 controller2;

    @GetMapping("/get")
    public ResponseEntity<String> getClassName() {
        return new ResponseEntity<String>(Controller1.class.getName(), HttpStatus.OK);
    }

    @GetMapping("/get1")
    public ResponseEntity<String> getClass1Name() {
        return new ResponseEntity<String>(controller2.getClassName().getBody(), HttpStatus.OK);
    }

    @GetMapping("/getR")
    public Response getClassResponse() {
        return new Response(Controller1.class.getName());
    }

    @GetMapping("/getR1")
    public Response getClass2Name() {
        return new Response(controller2.getClassResponse().getClassName());
    }
}
