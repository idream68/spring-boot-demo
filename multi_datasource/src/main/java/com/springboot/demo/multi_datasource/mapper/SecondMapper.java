package com.springboot.demo.multi_datasource.mapper;

import com.springboot.demo.multi_datasource.annotation.MyDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SecondMapper {
    @MyDataSource(dataSource = "second")
    @Select("select count(1) from second")
    int getCount();
}
