package cn.pbj.demo2020.ssm.dao.slave;

import cn.pbj.demo2020.ssm.entity.SysUser;

import java.util.List;

/**
 * @pClassName: SysUserDao
 * @author: pengbingjiang
 * @create: 2020/12/13 22:07
 * @description: TODO 系统用户
 */
public interface SysUserSlaveDao {
    /**
     * 查询所有用户信息
     */
    List<SysUser> queryUserAll();

    /**
     * 根据userId查询用户信息
     * @param userId  用户ID
     */
    List<SysUser> queryUserInfo(Long userId);

    /**
     * 根据userId更新用户的邮箱和手机号
     * @return
     */
    int updateUserInfo(SysUser user);
}

