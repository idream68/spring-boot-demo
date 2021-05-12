package com.springboot.demo.mybatis_pagehelper.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.demo.mybatis_pagehelper.entity.User;
import com.springboot.demo.mybatis_pagehelper.service.PageGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: zjhan
 * @Date: 2021/5/12 17:48
 * @Description:
 **/
@RestController
public class PageController {
    @Autowired
    PageGetter pageGetter;

    @GetMapping("/getUserPage")
    public ResponseEntity<PageInfo<User>> getUserPage(int pageNumber, int size, @RequestParam(required = false) String desc) {
        return new ResponseEntity<>(pageGetter.getPage(pageNumber, size, desc), HttpStatus.OK);
    }

}
