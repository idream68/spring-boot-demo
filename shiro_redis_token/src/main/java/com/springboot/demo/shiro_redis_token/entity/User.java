package com.springboot.demo.shiro_redis_token.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -6847342325707176113L;
    private Integer id;

    private String account;

    private String password;

    private String username;

    private Date regTime;
}