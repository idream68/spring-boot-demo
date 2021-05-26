package com.springboot.demo.shiro_jwt.domain;

import java.io.Serializable;

/**
 * @Author: zjhan
 * @Date: 2021/5/26 17:26
 * @Description:
 **/
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id; // 主键ID

    private String name; // 登录用户名

    private String password; // 登录密码

    private String nickName; // 昵称

    private Boolean locked; // 账户是否被锁定

    public UserEntity() {
        super();
    }

    public UserEntity(Long id, String name, String password, String nickName, Boolean locked) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.locked = locked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
