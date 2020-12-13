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
     * 查询用户的所有菜单ID
     */
    List<SysUser> queryUserInfo(Long userId);
}

