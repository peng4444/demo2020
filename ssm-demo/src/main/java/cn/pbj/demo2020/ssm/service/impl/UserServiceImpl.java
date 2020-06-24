package cn.pbj.demo2020.ssm.service.impl;

import cn.pbj.demo2020.ssm.dao.UserDao;
import cn.pbj.demo2020.ssm.entity.User;
import cn.pbj.demo2020.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }
}
