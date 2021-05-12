package com.springboot.demo.mybatis_pagehelper.mapper;

import com.springboot.demo.mybatis_pagehelper.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/5/12 17:48
 * @Description:
 **/
@Mapper
@Repository
public interface UserMapper {
    @Results({
           @Result(property = "uId", column = "u_id", id = true),
           @Result(property = "uName", column = "u_name")
    })
    @Select("select u_id, u_name from user")
    public List<User> getUsers();
}
