package com.springboot.demo.shiro_redis_token.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = 8049624488924475291L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String perCode;
}