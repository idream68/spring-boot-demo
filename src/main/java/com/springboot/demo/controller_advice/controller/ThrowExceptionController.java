package com.springboot.demo.controller_advice.controller;


import com.springboot.demo.controller_advice.exceptions.ControllerException;
import com.springboot.demo.controller_advice.exceptions.ControllerExceptionBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zjhan
 * @Date: 2021/4/22 15:48
 * @Description:
 **/
@RestController
@RequestMapping("/controller_advice")
public class ThrowExceptionController {
    @GetMapping("/exceptionBase")
    public ResponseEntity throwExceptionBase() throws ControllerExceptionBase {
        throw new ControllerExceptionBase("test");
    }

    @GetMapping("/exception")
    public ResponseEntity throwException() throws ControllerException {
        throw new ControllerException();
    }

}
