package com.springboot.demo.shiro_redis_token.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.shiro_redis_token.config.TokenCache;
import com.springboot.demo.shiro_redis_token.entity.User;
import com.springboot.demo.shiro_redis_token.entity.common.BaseDto;
import com.springboot.demo.shiro_redis_token.entity.common.Constant;
import com.springboot.demo.shiro_redis_token.entity.common.ResponseBean;
import com.springboot.demo.shiro_redis_token.exception.CustomException;
import com.springboot.demo.shiro_redis_token.exception.CustomUnauthorizedException;
import com.springboot.demo.shiro_redis_token.service.impl.UserServiceImpl;
import com.springboot.demo.shiro_redis_token.utils.JwtUtil;
import com.springboot.demo.shiro_redis_token.utils.UserUtil;
import com.springboot.demo.shiro_redis_token.utils.common.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * UserController
 * @author dolyw.com
 * @date 2018/8/29 15:45
 */
@RestController
@RequestMapping("/user")
@PropertySource("classpath:config.properties")
public class UserController {

    /**
     * RefreshToken过期时间
     */
    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    private final UserUtil userUtil;

    private final UserServiceImpl userService;

    @Autowired
    private TokenCache tokenCache;

    @Autowired
    public UserController(UserUtil userUtil, UserServiceImpl userService) {
        this.userUtil = userUtil;
        this.userService = userService;
    }

    /**
     * 获取用户列表
     * @param 
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author dolyw.com
     * @date 2018/8/30 10:41
     */
    @GetMapping
    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
    public ResponseBean user(@Validated BaseDto baseDto) {
        if (baseDto.getPage() == null || baseDto.getRows() == null) {
            baseDto.setPage(1);
            baseDto.setRows(10);
        }
        PageHelper.startPage(baseDto.getPage(), baseDto.getRows());
        List<User> users = userService.list();
        if (users == null || users.size() <= 0) {
            throw new CustomException("查询失败(Query Failure)");
        }
        PageInfo<User> selectPage = new PageInfo<User>(users);
        Map<String, Object> result = new HashMap<String, Object>(16);
        result.put("count", selectPage.getTotal());
        result.put("data", selectPage.getList());
        return new ResponseBean(HttpStatus.OK.value(), "查询成功(Query was successful)", result);
    }

    /**
     * 获取在线用户(查询Redis中的RefreshToken)
     * @param 
     * @return com.wang.model.common.ResponseBean
     * @author dolyw.com
     * @date 2018/9/6 9:58
     */
    @GetMapping("/online")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
    public ResponseBean online() {
        List<Object> users = new ArrayList<Object>();
        // 查询所有Redis键
        Set<byte[]> keys = tokenCache.getRedisManager().keys((Constant.PREFIX_SHIRO_REFRESH_TOKEN + "*")
                .getBytes(StandardCharsets.UTF_8));
        for (byte[] key : keys) {
            if (tokenCache.getRedisManager().get(key) != null) {
                // 根据:分割key，获取最后一个字符(帐号)
                String[] strArray = new String(key, StandardCharsets.UTF_8).split(":");
                String account = strArray[strArray.length - 1];
                User user = userService.getOneByAccount(account);
                users.add(user);
            }
        }
        if (users.size() <= 0) {
            throw new CustomException("查询失败(Query Failure)");
        }
        return new ResponseBean(HttpStatus.OK.value(), "查询成功(Query was successful)", users);
    }

    /**
     * 登录授权
     * @param user
     * @return com.wang.model.common.ResponseBean
     * @author dolyw.com
     * @date 2018/8/30 16:21
     */
    @PostMapping("/login")
    public ResponseBean login(@RequestBody User user, HttpServletResponse httpServletResponse) {
        // 查询数据库中的帐号信息
        User userDtoTemp = userService.getOneByAccount(user.getAccount());
        if (userDtoTemp == null) {
            throw new CustomUnauthorizedException("该帐号不存在(The account does not exist.)");
        }
        if (user.getPassword().equals(userDtoTemp.getPassword())) {
            // 清除可能存在的Shiro权限信息缓存
            if (tokenCache.getRedisManager().get(
                    (Constant.PREFIX_SHIRO_CACHE + user.getAccount()).getBytes(StandardCharsets.UTF_8)) != null) {
                tokenCache.getRedisManager().del((Constant.PREFIX_SHIRO_CACHE + user.getAccount())
                        .getBytes(StandardCharsets.UTF_8));
            }
            // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            tokenCache.getRedisManager().set((Constant.PREFIX_SHIRO_REFRESH_TOKEN + user.getAccount()).getBytes(StandardCharsets.UTF_8),
                    currentTimeMillis.getBytes(StandardCharsets.UTF_8), Integer.parseInt(refreshTokenExpireTime));
            // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
            String token = JwtUtil.sign(user.getAccount(), currentTimeMillis);
            httpServletResponse.setHeader("Authorization", token);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            return new ResponseBean(HttpStatus.OK.value(), "登录成功(Login Success.)", null);
        } else {
            throw new CustomUnauthorizedException("帐号或密码错误(Account or Password Error.)");
        }
    }

    /**
     * 测试登录
     * @param
     * @return com.wang.model.common.ResponseBean
     * @author dolyw.com
     * @date 2018/8/30 16:18
     */
    @GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        // 登录了返回true
        if (subject.isAuthenticated()) {
            return new ResponseBean(HttpStatus.OK.value(), "您已经登录了(You are already logged in)", null);
        } else {
            return new ResponseBean(HttpStatus.OK.value(), "你是游客(You are guest)", null);
        }
    }

    /**
     * 测试登录注解(@RequiresAuthentication和subject.isAuthenticated()返回true一个性质)
     * @param
     * @return com.wang.model.common.ResponseBean
     * @author dolyw.com
     * @date 2018/8/30 16:18
     */
    @GetMapping("/article2")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(HttpStatus.OK.value(), "您已经登录了(You are already logged in)", null);
    }

    /**
     * 获取当前登录用户信息
     * @param
     * @return com.wang.model.common.ResponseBean
     * @author dolyw.com
     * @date 2019/3/15 11:51
     */
    @GetMapping("/info")
    @RequiresAuthentication
    public ResponseBean info() {
        // 获取当前登录用户
        User userDto = userUtil.getUser();
        // 获取当前登录用户Id
        Integer id = userUtil.getUserId();
        // 获取当前登录用户Token
        String token = userUtil.getToken();
        // 获取当前登录用户Account
        String account = userUtil.getAccount();
        return new ResponseBean(HttpStatus.OK.value(), "您已经登录了(You are already logged in)", userDto);
    }

    /**
     * 获取指定用户
     * @param id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author dolyw.com
     * @date 2018/8/30 10:42
     */
    @GetMapping("/{id}")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
    public ResponseBean findById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        if (user == null) {
            throw new CustomException("查询失败(Query Failure)");
        }
        return new ResponseBean(HttpStatus.OK.value(), "查询成功(Query was successful)", user);
    }

    /**
     * 新增用户
     * @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author dolyw.com
     * @date 2018/8/30 10:42
     */
    @PostMapping
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    public ResponseBean add(@RequestBody User user) {
        // 判断当前帐号是否存在
        User userTemp = userService.getOneByAccount(user.getAccount());
        if (userTemp != null && StringUtil.isNotBlank(userTemp.getPassword())) {
            throw new CustomUnauthorizedException("该帐号已存在(Account exist.)");
        }
        user.setRegTime(new Date());
        // 密码以帐号+密码的形式进行AES加密
        if (user.getPassword().length() > Constant.PASSWORD_MAX_LEN) {
            throw new CustomException("密码最多8位(Password up to 8 bits.)");
        }
        boolean count = userService.save(user);
        if (! count) {
            throw new CustomException("新增失败(Insert Failure)");
        }
        return new ResponseBean(HttpStatus.OK.value(), "新增成功(Insert Success)", user);
    }

    /**
     * 更新用户
     * @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author dolyw.com
     * @date 2018/8/30 10:42
     */
    @PutMapping
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    public ResponseBean update(@RequestBody User user) {
        User userTemp = userService.getOneByAccount(user.getAccount());
        if (userTemp == null) {
            throw new CustomUnauthorizedException("该帐号不存在(Account not exist.)");
        } else {
            user.setId(userTemp.getId());
        }
        if (!userTemp.getPassword().equals(user.getPassword())) {
            // 密码以帐号+密码的形式进行AES加密
            if (user.getPassword().length() > Constant.PASSWORD_MAX_LEN) {
                throw new CustomException("密码最多8位(Password up to 8 bits.)");
            }
        }
        boolean count = userService.updateById(user);
        if (! count) {
            throw new CustomException("更新失败(Update Failure)");
        }
        return new ResponseBean(HttpStatus.OK.value(), "更新成功(Update Success)", user);
    }

    /**
     * 删除用户
     * @param id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author dolyw.com
     * @date 2018/8/30 10:43
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    public ResponseBean delete(@PathVariable("id") Integer id) {
        boolean count = userService.removeById(id);
        if (!count) {
            throw new CustomException("删除失败，ID不存在(Deletion Failed. ID does not exist.)");
        }
        return new ResponseBean(HttpStatus.OK.value(), "删除成功(Delete Success)", null);
    }

    /**
     * 剔除在线用户
     * @param id
     * @return com.wang.model.common.ResponseBean
     * @author dolyw.com
     * @date 2018/9/6 10:20
     */
    @DeleteMapping("/online/{id}")
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    public ResponseBean deleteOnline(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        byte[] key = (Constant.PREFIX_SHIRO_REFRESH_TOKEN + user.getAccount()).getBytes(StandardCharsets.UTF_8);
        if (tokenCache.getRedisManager().get(key) != null) {
            tokenCache.getRedisManager().del(key);
            return new ResponseBean(HttpStatus.OK.value(), "剔除成功(Delete Success)", null);
        }
        throw new CustomException("剔除失败，Account不存在(Deletion Failed. Account does not exist.)");
    }
}