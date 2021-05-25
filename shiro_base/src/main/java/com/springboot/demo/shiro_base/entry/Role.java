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
public class Role {
    private String id;
    private String roleName;

    private Set<Permissions> permissions;
}
