package cn.pbj.demo2020.ssm.controller;

import cn.pbj.demo2020.ssm.entity.SysUser;
import cn.pbj.demo2020.ssm.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @pClassName: SysUserController
 * @author: pengbingjiang
 * @create: 2020/12/14 09:16
 * @description: TODO
 * [Spring Boot2(二)：使用Spring Boot2集成Mybatis缓存机制](https://www.cnblogs.com/niaobulashi/p/mybatis-2levelcache.html)
 */
@RestController
public class SysUserController {

    private Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询所有用户信息
     * @return
     */
    @RequestMapping("/getAll")
    private List<SysUser> getUser() {
        List<SysUser> userList = sysUserService.queryUserAll();
        return userList;
    }

    /**
     * 根据userId查询用户信息
     * @return
     */
    @RequestMapping("/getUser")
    private List<SysUser> getUser(@RequestParam(value = "userId", required = false) Long userId) {
        List<SysUser> userList = sysUserService.queryUserInfo(userId);
        return userList;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping("/updateUser")
    private int updateUser(@RequestBody SysUser user) {
        return sysUserService.updateUserInfo(user);
    }

}
