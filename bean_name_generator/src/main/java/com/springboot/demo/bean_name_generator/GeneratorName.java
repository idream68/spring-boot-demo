package com.springboot.demo.bean_name_generator;

import org.springframework.stereotype.Component;

/**
 * @Author: zjhan
 * @Date: 2021/5/6 15:59
 * @Description:
 **/
@Component
public class GeneratorName {
    public void getName() {
        System.out.println(GeneratorName.class.getName());
    }
}
