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
    public enum Status {
        OK(1, "正常使用"),
        DISABLED(2, "禁用"),
        LOCKED(3, "被锁定");

        private String desc;
        private int code;

        Status(int code, String desc) {
            this.desc = desc;
            this.code = code;
        }
    }

    private String id;
    private String userName;
    private String password;
    private Status status;

    private Set<Role> roles;
}
