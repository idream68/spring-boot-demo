package com.springboot.demo.shiro_base.entry;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @Author: zjhan
 * @Date: 2021/5/25 16:46
 * @Description:
 **/
@Data
@AllArgsConstructor
public class User {
    private String id;
    private String userName;
    private String password;

    private Set<Role> roles;
}
