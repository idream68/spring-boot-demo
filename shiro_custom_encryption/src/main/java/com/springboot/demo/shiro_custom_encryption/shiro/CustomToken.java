package com.springboot.demo.shiro_custom_encryption.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: zjhan
 * @Date: 2021/6/11 15:26
 * @Description:
 **/
@Data
@AllArgsConstructor
public class CustomToken implements AuthenticationToken {
    private static final long serialVersionUID = -6808985226912027938L;

    String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
