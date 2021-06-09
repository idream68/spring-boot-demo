package com.springboot.demo.shiro_redis_token.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = 8049624488924475291L;

    private Integer id;

    private String name;

    private String perCode;
}