package com.springboot.demo.springbootbeanparam;

import com.springboot.demo.springbootbeanparam.beans.Person;
import com.springboot.demo.springbootbeanparam.beans.PersonManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Editor zjhan
 * @Date 2021/7/23 14:22
 * @Description setter 注入示例
 */
@RestController
@Slf4j
public class PersonController {
    PersonManager personManager;

    @Autowired
    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    @GetMapping("/getPerson")
    public Person getPerson(String type) {
        return personManager.getPersonByType(type);
    }
}
