package com.springboot.demo.mapper_scan_mapper.mapper1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zjhan
 * @Date: 2021/5/11 11:42
 * @Description:
 **/
@Mapper
public interface TestMapper {
    @Select("select count(1) from application")
    int getCount();
}
