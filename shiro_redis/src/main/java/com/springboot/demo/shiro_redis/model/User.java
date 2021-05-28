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
public class User {
    public enum Status {
        ENABLE(0, "正常使用中..."),
        DISABLE(1, "禁用"),
        LOCKED(2, "被锁");

        int code;
        String desc;

        Status(int code, String desc) {
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
    private int id;
    private String userName;
    private String password;
    private Status status;
}
