package com.springboot.demo.shiro_redis_token.entity.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页排序通用Dto
 * @author dolyw.com
 * @date 2018/9/10 10:10
 */
@Data
public class BaseDto implements Serializable {
    private static final long serialVersionUID = -6251633514390866317L;

    private Integer page;

    private Integer rows;

    private String sidx;

    private String sord;

}
