package com.springboot.demo.controller_advice.exceptions;

/**
 * @Author: zjhan
 * @Date: 2021/4/22 15:48
 * @Description:
 **/
public class ControllerExceptionBase extends Exception {
    private int code;
    public ControllerExceptionBase(String message) {
        super(message);
        this.code = 5000;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
