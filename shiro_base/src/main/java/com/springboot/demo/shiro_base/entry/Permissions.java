package com.springboot.demo.shiro_base.entry;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: zjhan
 * @Date: 2021/5/25 16:46
 * @Description:
 **/
@Data
@AllArgsConstructor
public class Permissions {
    private String id;
    private String permissionsName;
}
