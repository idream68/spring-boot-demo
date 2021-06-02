package com.springboot.demo.shiro_redis.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis.model.Role;
import com.springboot.demo.shiro_redis.model.RoleResources;
import com.springboot.demo.shiro_redis.service.impl.RoleResourcesServiceImpl;
import com.springboot.demo.shiro_redis.service.impl.RoleServiceImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:01
 * @Description:
 **/
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Resource
    private RoleServiceImpl roleService;
    @Resource
    private RoleResourcesServiceImpl roleResourcesService;

    @RequestMapping
    public Map<String,Object> getAll(@RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){

        Map<String,Object> map = new HashMap<>();
        PageInfo<Role> pageInfo = roleService.selectByPage(start, length);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    //分配角色
    @RequestMapping("/saveRoleResources")
    public String saveRoleResources(RoleResources roleResources){
        if(StringUtils.isEmpty(roleResources.getRoleId()))
            return "error";
        try {
            roleResourcesService.save(roleResources);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/add")
    public String add(Role role) {
        try {
            roleService.save(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        try{
            roleService.removeById(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}
