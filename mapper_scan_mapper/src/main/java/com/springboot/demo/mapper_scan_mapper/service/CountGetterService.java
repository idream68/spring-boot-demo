package com.springboot.demo.mapper_scan_mapper.service;

import com.springboot.demo.mapper_scan_mapper.mapper1.TestMapper;
import com.springboot.demo.mapper_scan_mapper.mapper1.TestMapper1;
import com.springboot.demo.mapper_scan_mapper.mapper2.TestMapper2;
import com.springboot.demo.mapper_scan_mapper.mapper2.TestMapper3;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Author: zjhan
 * @Date: 2021/5/11 11:47
 * @Description:
 **/
@Repository
public class CountGetterService {
    @Resource
    TestMapper testMapper;
    @Resource
    TestMapper1 testMapper1;
//    @Resource
//    TestMapper2 testMapper2;
//    @Resource
//    TestMapper3 testMapper3;

    public int getCount1() {
        return testMapper.getCount();
    }

    public int getCount2() {
        return testMapper1.getCount();
    }

//    public int getCount3() {
//        return testMapper2.getCount();
//    }
//
//    public int getCount4() {
//        return testMapper3.getCount();
//    }
}
