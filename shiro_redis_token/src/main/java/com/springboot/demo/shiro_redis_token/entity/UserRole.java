package com.springboot.demo.shiro_redis_token.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 3748385491628782049L;
    private Integer id;

    private Integer userId;

    private Integer roleId;
}