package com.springboot.demo.aspect.annotation;

import java.lang.annotation.*;

/**
 * @Author: zjhan
 * @Date: 2021/6/18 15:26
 * @Description:
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Customer {

    String name();

    String desc();
}
