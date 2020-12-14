package cn.pbj.demo2020.ssm.controller;

import cn.pbj.demo2020.ssm.dao.master.SysUserMasterDao;
import cn.pbj.demo2020.ssm.dao.slave.SysUserSlaveDao;
import cn.pbj.demo2020.ssm.entity.SysUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @pClassName: SysUserMasterSlaveController
 * @author: pengbingjiang
 * @create: 2020/12/14 12:28
 * @description: TODO
 * [Spring Boot2(四)：使用Spring Boot多数据源实现过程](https://www.cnblogs.com/niaobulashi/p/mybatis-mutls.html)
 */
@RestController
public class SysUserMasterSlaveController {
    @Resource
    private SysUserMasterDao sysUserMasterDao;

    @Resource
    private SysUserSlaveDao sysUserSlaveDao;

    /**
     * 查询所有用户信息Master
     * @return
     */
    @RequestMapping("/getUserMasterAll")
    private List<SysUser> getUserMaster() {
        System.out.println("查询主库");
        List<SysUser> userList = sysUserMasterDao.queryUserAll();
        return userList;
    }

    /**
     * 查询所有用户信息Slave
     * @return
     */
    @RequestMapping("/getUserSlaveAll")
    private List<SysUser> getUserSlave() {
        System.out.println("查询从库");
        List<SysUser> userList = sysUserSlaveDao.queryUserAll();
        return userList;
    }

    /**
     * 根据userId查询用户信息Master
     * @return
     */
    @RequestMapping("/getUserMasterById")
    private List<SysUser> getUserMasterById(@RequestParam(value = "userId", required = false) Long userId) {
        List<SysUser> userList = sysUserMasterDao.queryUserInfo(userId);
        return userList;
    }

    /**
     * 根据userId查询用户信息Slave
     * @return
     */
    @RequestMapping("/getUserSlaveById")
    private List<SysUser> getUserSlaveById(@RequestParam(value = "userId", required = false) Long userId) {
        List<SysUser> userList = sysUserSlaveDao.queryUserInfo(userId);
        return userList;
    }
}
