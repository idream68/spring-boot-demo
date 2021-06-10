package com.springboot.demo.shiro_redis_token.utils;

import com.springboot.demo.shiro_redis_token.entity.User;
import com.springboot.demo.shiro_redis_token.entity.common.Constant;
import com.springboot.demo.shiro_redis_token.exception.CustomException;
import com.springboot.demo.shiro_redis_token.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    private final UserServiceImpl userService;

    @Autowired
    public UserUtil(UserServiceImpl userService) {
        this.userService = userService;
    }

    public User getUser() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        // 解密获得Account
        String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
        User user = userService.getOneByAccount(account);
        // 用户是否存在
        if (user == null) {
            throw new CustomException("该帐号不存在(The account does not exist.)");
        }
        return user;
    }

    public Integer getUserId() {
        return getUser().getId();
    }

    public String getToken() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }

    public String getAccount() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        // 解密获得Account
        return JwtUtil.getClaim(token, Constant.ACCOUNT);
    }
}
