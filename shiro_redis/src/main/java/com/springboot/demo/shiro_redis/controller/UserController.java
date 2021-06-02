package com.springboot.demo.shiro_redis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.model.User;
import com.springboot.demo.shiro_redis.model.UserRole;
import com.springboot.demo.shiro_redis.model.UserStatus;
import com.springboot.demo.shiro_redis.service.impl.UserRoleServiceImpl;
import com.springboot.demo.shiro_redis.service.impl.UserServiceImpl;
import com.springboot.demo.shiro_redis.utils.PasswordHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:02
 * @Description:
 **/
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserServiceImpl userService;
    @Resource
    private UserRoleServiceImpl userRoleService;

    @RequestMapping
    public Map<String,Object> getAll(@RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<User> pageInfo = userService.selectByPage(start, length);
        System.out.println("pageInfo.getTotal():"+pageInfo.getTotal());
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }


    /**
     * 保存用户角色
     * @param userRole 用户角色
     *  	  此处获取的参数的角色id是以 “,” 分隔的字符串
     * @return
     */
    @RequestMapping("/saveUserRoles")
    public String saveUserRoles(UserRole userRole){
        if(StringUtils.isEmpty(userRole.getUserId()))
            return "error";
        try {
            userRoleService.save(userRole);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/add")
    public String add(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUsername());
        User u = userService.getOne(queryWrapper);
        if(u != null)
            return "error";
        try {
            user.setUserStatus(UserStatus.ENABLE);
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            userService.save(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        try{
            userService.removeById(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}
