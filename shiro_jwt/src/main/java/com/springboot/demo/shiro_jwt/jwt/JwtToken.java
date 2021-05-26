package com.springboot.demo.shiro_jwt.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: zjhan
 * @Date: 2021/5/26 17:27
 * @Description:
 **/
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = 1L;

    // 加密后的 JWT token串
    private String token;

    private String userName;

    public JwtToken(String token) {
        this.token = token;
        this.userName = JwtUtils.getClaimFiled(token, "username");
    }

    @Override
    public Object getPrincipal() {
        return this.userName;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
