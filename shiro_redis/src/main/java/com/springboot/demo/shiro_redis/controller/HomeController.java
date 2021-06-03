package com.springboot.demo.shiro_redis.controller;

import com.springboot.demo.shiro_redis.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:01
 * @Description:
 **/
@RestController
public class HomeController {
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "未登录";
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(HttpServletRequest request, User user, Model model){
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "用户名或密码不能为空";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
            return "登陆成功";
        } catch (UnknownAccountException e) {
            token.clear();
            request.setAttribute("msg", "用户不存在");
            return "用户不存在";
        } catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "用户已经被锁定不能登录，请与管理员联系！";
        } catch (DisabledAccountException e) {
            token.clear();
            request.setAttribute("msg", "用户已禁用不能登录，情欲管理员联系！");
            return "用户已禁用不能登录，情欲管理员联系！";
        }  catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            return "用户或密码不正确！";
        }
    }
}
