package com.springboot.demo.mybatis_pagehelper;

import com.springboot.demo.mybatis_pagehelper.service.PageGetter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zjhan
 * @Date: 2021/5/12 20:00
 * @Description:
 **/
@SpringBootTest
public class PageGetterTest {
    PageGetter pageGetter;

    @Test
    public void test() {
        System.out.println(pageGetter.getPage(1, 10, ""));
    }
}
