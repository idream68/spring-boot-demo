package com.springboot.demo.shiro_redis_token.config;

import com.springboot.demo.shiro_redis_token.config.jwt.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zjhan
 * @Date: 2021/6/4 16:31
 * @Description:
 **/
@Configuration
public class ShiroConfig {
    @Autowired
    TokenCache redisCacheGenerator;

    /**
     * 交由 Spring 来自动地管理 Shiro-Bean 的生命周期
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 为 Spring-Bean 开启对 Shiro 注解的支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    /**
     * 配置 SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator());
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * 添加自己的过滤器，自定义url规则
     * Shiro自带拦截器配置规则
     * rest：比如/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user：method] ,其中method为post，get，delete等
     * port：比如/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal：//serverName：8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数
     * perms：比如/admins/user/**=perms[user：add：*],perms参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，比如/admins/user/**=perms["user：add：*,user：modify：*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法
     * roles：比如/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，比如/admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。//要实现or的效果看http://zgzty.blog.163.com/blog/static/83831226201302983358670/
     * anon：比如/admins/**=anon 没有参数，表示可以匿名使用
     * authc：比如/admins/user/**=authc表示需要认证才能使用，没有参数
     * authcBasic：比如/admins/user/**=authcBasic没有参数表示httpBasic认证
     * ssl：比如/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
     * user：比如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查
     * 详情见文档 http://shiro.apache.org/web.html#urls-
     * @param securityManager
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @author dolyw.com
     * @date 2018/8/31 10:57
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 添加自己的过滤器取名为jwt
        Map<String, Filter> filterMap = new HashMap<>(16);
        filterMap.put("jwt", new JwtFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        // 自定义url规则使用LinkedHashMap有序Map
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>(16);
        // Swagger接口文档
        // filterChainDefinitionMap.put("/v2/api-docs", "anon");
        // filterChainDefinitionMap.put("/webjars/**", "anon");
        // filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        // filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        // filterChainDefinitionMap.put("/doc.html", "anon");
        // 公开接口
        // filterChainDefinitionMap.put("/api/**", "anon");
        // 所有请求通过我们自己的JWTFilter
        filterChainDefinitionMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }
}
