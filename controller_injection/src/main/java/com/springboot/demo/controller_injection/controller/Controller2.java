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
@RequestMapping("/controller2")
public class Controller2 {
    @Autowired
    Controller1 controller1;

    @GetMapping("/get")
    public ResponseEntity<String> getClassName() {
        return new ResponseEntity<String>(Controller2.class.getName(), HttpStatus.OK);
    }

    @GetMapping("/get1")
    public ResponseEntity<String> getClass1Name() {
        return new ResponseEntity<String>(controller1.getClassName().getBody(), HttpStatus.OK);
    }

    @GetMapping("/getR")
    public Response getClassResponse() {
        return new Response(Controller2.class.getName());
    }

    @GetMapping("/getR1")
    public Response getClass1Response() {
        return new Response(controller1.getClassResponse().getClassName());
    }
}
