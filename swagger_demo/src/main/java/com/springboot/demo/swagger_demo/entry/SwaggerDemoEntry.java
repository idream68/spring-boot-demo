package com.springboot.demo.swagger_demo.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zjhan
 * @Date: 2021/5/25 11:58
 * @Description:
 **/
@Data
@ApiModel("实例测试")
public class SwaggerDemoEntry {
    @ApiModelProperty("姓名")
    String name;
    @ApiModelProperty("年龄")
    @JsonIgnore
    int age = 10;
}
