package com.springboot.demo.shiro_redis.model;

/**
 * @Author: zjhan
 * @Date: 2021/5/31 20:53
 * @Description:
 **/
public enum UserStatus {
    ENABLE(0, "启用"),
    DISABLE(1, "禁用"),
    LOCKED(2, "被锁");

    public int code;
    public String desc;

    UserStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
