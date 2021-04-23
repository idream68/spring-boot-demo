package com.springboot.demo.multi_datasource.mapper;

import com.springboot.demo.multi_datasource.mysql.MyDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PrimaryMapper {
    @MyDataSource(dataSource = "primary")
    @Select("select count(1) from primary")
    int getCount();
}
