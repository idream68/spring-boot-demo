package com.springboot.demo.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.mybatis_plus.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 17:24
 * @Description:
 **/
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT count(1) from user;")
    int getCount();
}
