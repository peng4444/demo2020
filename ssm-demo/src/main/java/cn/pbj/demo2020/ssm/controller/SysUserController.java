package cn.pbj.demo2020.ssm.controller;

import cn.pbj.demo2020.ssm.entity.SysUser;
import cn.pbj.demo2020.ssm.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @pClassName: SysUserController
 * @author: pengbingjiang
 * @create: 2020/12/14 09:16
 * @description: TODO
 * [Spring Boot2(二)：使用Spring Boot2集成Mybatis缓存机制](https://www.cnblogs.com/niaobulashi/p/mybatis-2levelcache.html)
 * [Spring boot多模块项目 + Swagger 让你的API可视化](https://www.cnblogs.com/xpwi/p/10609104.html)
 */
@RestController
@Api(value="系统用户测试模块")
@RequestMapping("/user")
public class SysUserController {

    private Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询所有用户信息
     * @return
     */
    @ApiOperation(value = "获取所有的系统用户信息",notes = "请求参数为空")
    @GetMapping("/getAll")
    private List<SysUser> getUser() {
        List<SysUser> userList = sysUserService.queryUserAll();
        return userList;
    }

    /**
     * 根据userId查询用户信息
     * @return
     */
    @PostMapping("/getUserById")
    @ApiOperation(value = "根据用户id获取所系统用户详细信息",notes = "根据用户id获取所系统用户详细信息")
    @ApiImplicitParam(name = "/getUserById/{userId}",value = "用户ID",required = true,dataType = "Long",paramType = "path")
    private List<SysUser> getUser(Long userId) {
        List<SysUser> userList = sysUserService.queryUserInfo(userId);
        return userList;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @ApiOperation(value = "更新系统用户信息",notes = "根据用户id来更新系统用户信息")
    @ApiImplicitParam(name = "user",value = "系统用户实体SysUser",required = true,dataType = "Integer")
    @PostMapping("/updateUser")
    private int updateUser(@RequestBody SysUser user) {
        return sysUserService.updateUserInfo(user);
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String  jsonTest() {
        return " hi you!";
    }
}
