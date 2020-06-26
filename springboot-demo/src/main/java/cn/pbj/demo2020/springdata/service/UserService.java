package cn.pbj.demo2020.springdata.service;

import cn.pbj.demo2020.springdata.dao.UserDao;
import cn.pbj.demo2020.springdata.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @pClassName: UserService
 * @author: pengbingjiang
 * @create: 2020/6/26 10:46
 * @description: TODO
 */
@Service
@Transactional
public class UserService {

    @Resource
    UserDao userDao;

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
