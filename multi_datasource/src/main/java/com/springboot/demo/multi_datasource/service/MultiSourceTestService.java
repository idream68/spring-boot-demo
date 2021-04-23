package com.springboot.demo.multi_datasource.service;

import com.springboot.demo.multi_datasource.mapper.PrimaryMapper;
import com.springboot.demo.multi_datasource.mapper.SecondMapper;
import com.springboot.demo.multi_datasource.mapper.ThirdMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MultiSourceTestService {
    @Resource
    PrimaryMapper primaryMapper;
    @Resource
    SecondMapper secondMapper;
    @Resource
    ThirdMapper thirdMapper;

    public int getPrimaryData() {
        return primaryMapper.getCount();
    }

    public int getSecondData() {
        return secondMapper.getCount();
    }

    public int getThirdData() {
        return thirdMapper.getCount();
    }

}
