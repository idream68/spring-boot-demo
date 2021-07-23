package com.springboot.demo.springbootbeanparam.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Editor zjhan
 * @Date 2021/7/23 14:03
 * @Description TODO
 */
@Component
public class Teacher extends Person {
    @PostConstruct
    public void initType() {
        this.type = "Teacher";
    }

    public String getType() {
        return type;
    }
}
