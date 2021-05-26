package com.springboot.demo.shiro_jwt.controller;
import com.springboot.demo.shiro_jwt.domain.BaseResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zjhan
 * @Date: 2021/5/26 17:26
 * @Description:
 **/

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/delete")
    @RequiresRoles(value = { "admin" })
    public Object deleteArticle(ModelMap model) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        ret.setErrCode(0);
        ret.setMsg("文章删除成功！");
        return ret;
    }

    @GetMapping("/read")
    @RequiresPermissions(value = { "article:read" })
    public Object readArticle(ModelMap model) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        ret.setErrCode(0);
        ret.setMsg("请您鉴赏！");
        return ret;
    }
}
