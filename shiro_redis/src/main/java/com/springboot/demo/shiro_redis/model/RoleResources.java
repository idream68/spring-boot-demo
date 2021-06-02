package com.springboot.demo.shiro_redis.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:03
 * @Description:
 **/
@Data
public class RoleResources implements Serializable {
    private Integer id;
    private int roleId;
    private int resourcesId;
}
