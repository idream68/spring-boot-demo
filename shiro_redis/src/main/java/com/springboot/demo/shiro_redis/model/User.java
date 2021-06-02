package com.springboot.demo.shiro_redis.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:03
 * @Description:
 **/
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.status = userStatus.code;
    }

    public UserStatus getUserStatus() {
        switch (this.status) {
            case 0: return UserStatus.ENABLE;
            case 2: return UserStatus.LOCKED;
            default: return UserStatus.DISABLE;
        }
    }
}
