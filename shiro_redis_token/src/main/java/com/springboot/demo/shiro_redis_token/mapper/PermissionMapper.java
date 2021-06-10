package com.springboot.demo.shiro_redis_token.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.shiro_redis_token.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("select * from xxx")
    List<Permission> findPermissionByRole(@Param("roleId") int roleId);

}