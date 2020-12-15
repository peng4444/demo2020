package cn.pbj.demo2020.ssm.service.impl;

import cn.pbj.demo2020.ssm.dao.UserMapper;
import cn.pbj.demo2020.ssm.entity.User;
import cn.pbj.demo2020.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @pClassName: UserServiceImpl
 * @author: pengbingjiang
 * @create: 2020/12/15 09:33
 * @description: TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据账户查询用户信息
     * @param account
     * @return
     */
    @Override
    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }
}

