package com.springboot.demo.shiro_redis.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:03
 * @Description:
 **/
@Data
public class Resources implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String resUrl;
    private int parentId;
}
