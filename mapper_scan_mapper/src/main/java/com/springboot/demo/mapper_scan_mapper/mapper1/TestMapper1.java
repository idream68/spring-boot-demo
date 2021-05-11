package com.springboot.demo.mapper_scan_mapper.mapper1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zjhan
 * @Date: 2021/5/11 11:45
 * @Description:
 **/
@Mapper
public interface TestMapper1 {
    @Select("select count(1) from table2")
    int getCount();
}
