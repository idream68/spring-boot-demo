package com.springboot.demo.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zjhan
 * @Date: 2021/5/28 16:52
 * @Description:
 **/
@TableName("user")
@Data
public class User implements Serializable {
    int id;
    String name;
    String code;
    Integer tId;
}
