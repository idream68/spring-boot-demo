package com.springboot.demo.aspect.model;

import lombok.Data;

/**
 * @Author: zjhan
 * @Date: 2021/6/21 10:00
 * @Description:
 **/

@Data
public class ResponseU<T> {
    int code;

    String desc;

    T data;



}
