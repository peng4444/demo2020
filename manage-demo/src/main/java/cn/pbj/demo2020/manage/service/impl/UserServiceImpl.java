package cn.pbj.demo2020.manage.service.impl;

import cn.pbj.demo2020.manage.common.DataResult;
import cn.pbj.demo2020.manage.dao.UserDao;
import cn.pbj.demo2020.manage.entity.User;
import cn.pbj.demo2020.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @pClassName: UserServiceImpl
 * @author: pengbingjiang
 * @create: 2020/7/23 18:40
 * @description: TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public DataResult<User> selectAll() {
        DataResult<User> result = new DataResult<>();
        result.setCode(0);
        result.setMsg("");
        List<User> users = userDao.selectAll();
        result.setCount(users.size());
        result.setData(users);
        return result;
    }

    @Override
    public DataResult<User> selectAllByPage(Integer page, Integer limit) {
        DataResult<User> result = new DataResult<>();
        result.setCode(0);
        result.setMsg("");
        List<User> users = userDao.selectAllByPage(page,limit);
        result.setCount(users.size());
        result.setData(users);
        return result;
    }
}
