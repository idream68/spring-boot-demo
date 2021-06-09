package com.springboot.demo.shiro_redis_token.config;

import com.springboot.demo.shiro_redis_token.config.jwt.JwtToken;
import com.springboot.demo.shiro_redis_token.entity.Permission;
import com.springboot.demo.shiro_redis_token.entity.Role;
import com.springboot.demo.shiro_redis_token.entity.User;
import com.springboot.demo.shiro_redis_token.entity.common.Constant;
import com.springboot.demo.shiro_redis_token.service.impl.PermissionServiceImpl;
import com.springboot.demo.shiro_redis_token.service.impl.RoleServiceImpl;
import com.springboot.demo.shiro_redis_token.service.impl.UserServiceImpl;
import com.springboot.demo.shiro_redis_token.utils.JwtUtil;
import com.springboot.demo.shiro_redis_token.utils.common.StringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 自定义Realm
 * @author dolyw.com
 * @date 2018/8/30 14:10
 */
@Service
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private PermissionServiceImpl permissionService;
    @Autowired
    private TokenCache tokenCache;


    /**
     * 大坑，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String account = JwtUtil.getClaim(principalCollection.toString(), Constant.ACCOUNT);

        // 查询用户角色
        List<Role> roles = roleService.findRoleByUserAccount(account);
        for (Role role : roles) {
            if (role != null) {
                // 添加角色
                simpleAuthorizationInfo.addRole(role.getName());
                // 根据用户角色查询权限
                List<Permission> permissions = permissionService.findPermissionByRole(role.getId());
                for (Permission permission : permissions) {
                    if (permission != null) {
                        // 添加权限
                        simpleAuthorizationInfo.addStringPermission(permission.getPerCode());
                    }
                }
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得account，用于和数据库进行对比
        String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
        // 帐号为空
        if (StringUtil.isBlank(account)) {
            throw new AuthenticationException("Token中帐号为空(The account in Token is empty.)");
        }
        // 查询用户是否存在
        User user = userService.getOneByAccount(account);
        if (user == null) {
            throw new AuthenticationException("该帐号不存在(The account does not exist.)");
        }
        // 开始认证，要AccessToken认证通过，且Redis中存在RefreshToken，且两个Token时间戳一致
        if (JwtUtil.verify(token) && tokenCache.getRedisManager().get(account.getBytes(StandardCharsets.UTF_8)) != null) {
            // 获取RefreshToken的时间戳
            String currentTimeMillisRedis = new String(tokenCache.getRedisManager().get(account.getBytes(StandardCharsets.UTF_8)));
            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            if (JwtUtil.getClaim(token, Constant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                return new SimpleAuthenticationInfo(token, token, getName());
            }
        }
        throw new AuthenticationException("Token已过期(Token expired or incorrect.)");
    }
}
