package com.springboot.demo.shiro_redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:03
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int id;
    private String Name;
    private String description;
}
