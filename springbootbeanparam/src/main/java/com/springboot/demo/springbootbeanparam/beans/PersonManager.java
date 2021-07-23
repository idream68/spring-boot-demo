package com.springboot.demo.springbootbeanparam.beans;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Editor zjhan
 * @Date 2021/7/23 14:04
 * @Description 构造器注入示例
 */
@Service
public class PersonManager {
    private final Map<String, Person> personMap = new HashMap<>();

    public PersonManager(List<Person> personList) {
        personList.forEach(x->personMap.put(x.getType(), x));
    }

    public Person getPersonByType(String type) {
        return personMap.get(type);
    }
}
