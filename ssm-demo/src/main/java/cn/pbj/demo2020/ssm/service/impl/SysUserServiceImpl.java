package cn.pbj.demo2020.ssm.service.impl;

import cn.pbj.demo2020.ssm.dao.SysUserDao;
import cn.pbj.demo2020.ssm.entity.SysUser;
import cn.pbj.demo2020.ssm.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @pClassName: SysUserServiceImpl
 * @author: pengbingjiang
 * @create: 2020/12/13 22:10
 * @description: TODO
 */
@Service("sysUserService")
public class SysUserServiceImpl  implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;
    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    @Override
    public List<SysUser> queryUserInfo(Long userId) {
        return sysUserDao.queryUserInfo(userId);
    }
}

