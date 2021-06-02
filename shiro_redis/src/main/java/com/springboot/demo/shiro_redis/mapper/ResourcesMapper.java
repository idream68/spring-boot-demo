package com.springboot.demo.shiro_redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.shiro_redis.model.Resources;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 14:02
 * @Description:
 **/
public interface ResourcesMapper extends BaseMapper<Resources> {
    @Select("<script>" +
            "SELECT re.id,re.name,re.parent_id,re.res_url" +
            "        FROM resources re LEFT JOIN role_resources rr" +
            "        ON re.id = rr.resources_id" +
            "        LEFT JOIN user_role ur" +
            "        ON rr.role_id =ur.role_id" +
            "        WHERE ur.user_id=#{userid}" +
            "        GROUP BY re.id" +
            "</script>"
    )
    List<Resources> userResources(@Param("userid") int userId);
}
