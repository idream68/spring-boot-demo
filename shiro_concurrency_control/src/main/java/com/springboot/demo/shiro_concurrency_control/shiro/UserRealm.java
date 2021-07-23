package com.springboot.demo.shiro_concurrency_control.shiro;

import com.springboot.demo.shiro_concurrency_control.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:07
 * @Description:
 **/
@Component
public class UserRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("admin");
        info.addStringPermission("admin");
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setId(1);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getPassword(), //密码
                ByteSource.Util.bytes(username),
                getName()  //realm name
        );
        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getId());
        return authenticationInfo;
    }

//
//    /**
//     * 根据userId 清除当前session存在的用户的权限缓存
//     * @param userIds 已经修改了权限的userId
//     */
//    public void clearUserAuthByUserId(List<Integer> userIds){
//        if(null == userIds || userIds.size() == 0)	return ;
//        //获取所有session
//        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
//        //定义返回
//        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
//        for (Session session:sessions){
//            //获取session登录信息。
//            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
//            if(obj != null && obj instanceof SimplePrincipalCollection){
//                //强转
//                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
//                //判断用户，匹配用户ID。
//                obj = spc.getPrimaryPrincipal();
//                if(null != obj && obj instanceof User){
//                    User user = (User) obj;
//                    System.out.println("user:"+user);
//                    //比较用户ID，符合即加入集合
//                    if(null != user && userIds.contains(user.getId())){
//                        list.add(spc);
//                    }
//                }
//            }
//        }
//        RealmSecurityManager securityManager =
//                (RealmSecurityManager) SecurityUtils.getSecurityManager();
//        UserRealm realm = (UserRealm)securityManager.getRealms().iterator().next();
//        for (SimplePrincipalCollection simplePrincipalCollection : list) {
//            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
//        }
//    }
}
