package cn.pbj.demo2020.ssm.dao;

import cn.pbj.demo2020.ssm.entity.SysUser;

import java.util.List;

/**
 * @pClassName: SysUserDao
 * @author: pengbingjiang
 * @create: 2020/12/13 22:07
 * @description: TODO
 */
public interface SysUserDao {
    /**
     * 根据userId查询用户信息
     * @param userId  用户ID
     */
    List<SysUser> queryUserInfo(Long userId);
}

