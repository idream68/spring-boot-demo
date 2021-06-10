package com.springboot.demo.shiro_redis_token.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -6847342325707176113L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;

    private String password;

    private String username;
}