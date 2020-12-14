package cn.pbj.demo2020.ssm.service.impl;

import cn.pbj.demo2020.ssm.dao.SysUserDao;
import cn.pbj.demo2020.ssm.entity.SysUser;
import cn.pbj.demo2020.ssm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @pClassName: SysUserServiceImpl
 * @author: pengbingjiang
 * @create: 2020/12/13 22:10
 * @description: TODO
 */
@Service
public class SysUserServiceImpl  implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 查询所有用户信息
     */
    @Override
    public List<SysUser> queryUserAll() {
        return sysUserDao.queryUserAll();
    }

    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    @Override
    public List<SysUser> queryUserInfo(Long userId) {
        return sysUserDao.queryUserInfo(userId);
    }

    /**
     * 根据userId更新用户的邮箱和手机号
     */
    @Override
    public int updateUserInfo(SysUser user) {
        return sysUserDao.updateUserInfo(user);
    }
}

