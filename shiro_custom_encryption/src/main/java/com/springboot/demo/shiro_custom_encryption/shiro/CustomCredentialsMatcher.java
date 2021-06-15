package com.springboot.demo.shiro_custom_encryption.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @Author: zjhan
 * @Date: 2021/6/11 15:36
 * @Description:
 **/
public class CustomCredentialsMatcher implements CredentialsMatcher {

    /**
     * 自定义认证方法（密码，token等自定义验证方法），登录，和其他方法都需要验证
     * @param token
     * @param info
     * @return true 验证成功，false 验证失败
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return true;
    }
}
