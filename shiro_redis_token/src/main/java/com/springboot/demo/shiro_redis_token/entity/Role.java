package com.springboot.demo.shiro_redis_token.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = -2127916225543093120L;
    private Integer id;

    private String name;
}