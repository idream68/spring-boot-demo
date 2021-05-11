package com.springboot.demo.mapper_scan_mapper.mapper2;

import org.apache.ibatis.annotations.Select;

/**
 * @Author: zjhan
 * @Date: 2021/5/11 11:45
 * @Description:
 **/
public interface TestMapper2 {
    @Select("select count(1) from table3")
    int getCount();
}
