package com.springboot.demo.shiro_redis.utils;

import com.springboot.demo.shiro_redis.model.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:08
 * @Description: 加密算法
 **/
public class PasswordHelper {
    private String hashAlgorithmName = "SHA-1";
    private int hashIterations = 16;

    public void encryptPassword(User user) {
        SimpleHash password = new SimpleHash(hashAlgorithmName, user.getPassword(), ByteSource.Util.bytes(user.getUserName()), hashIterations);
        user.setPassword(password.toString());
    }

    public String encryptPassword(String userName, String password) {
        return new SimpleHash(hashAlgorithmName, password, ByteSource.Util.bytes(userName), hashIterations).toString();
    }
}
