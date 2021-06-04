package com.springboot.demo.mapper1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zjhan
 * @Date: 2021/5/11 11:42
 * @Description:
 **/
@Mapper
public interface TestMapper {
    @Select("select count(1) from table1")
    int getCount();
}
