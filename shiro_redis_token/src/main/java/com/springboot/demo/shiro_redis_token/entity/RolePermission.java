package com.springboot.demo.shiro_redis_token.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 2139532120884855941L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer roleId;

    private Integer permissionId;
}