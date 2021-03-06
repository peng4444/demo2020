package cn.pbj.demo2020.ssm.service;

import cn.pbj.demo2020.ssm.entity.SysUser;

import java.util.List;

/**
 * @pClassName: SysUserService
 * @author: pengbingjiang
 * @create: 2020/12/13 22:10
 * @description: TODO
 */
public interface SysUserService {
    /**
     * 查询所有用户信息
     */
    List<SysUser> queryUserAll();

    /**
     * 根据userId查询用户信息
     */
    List<SysUser> queryUserInfo(Long userId);

    /**
     * 根据userId更新用户的邮箱和手机号
     */
    int updateUserInfo(SysUser user);
}

