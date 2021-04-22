package com.springboot.demo.controller_advice.interceptor;

import com.springboot.demo.controller_advice.exceptions.ControllerException;
import com.springboot.demo.controller_advice.exceptions.ControllerExceptionBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: zjhan
 * @Date: 2021/4/22 15:52
 * @Description: 当controller遇到异常时，捕捉响应异常，并进行处理
 **/
@RestController
@ControllerAdvice
public class ControllerInterceptor {
    @ExceptionHandler(ControllerExceptionBase.class)
    public ResponseEntity controllerExceptionBaseHandler() {
        return new ResponseEntity<Integer>(5000, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity controllerExceptionHandler() {
        return new ResponseEntity(50001, HttpStatus.EXPECTATION_FAILED);
    }
}
