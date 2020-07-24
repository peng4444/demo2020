package cn.pbj.demo2020.manage.service;

import cn.pbj.demo2020.manage.common.DataResult;
import cn.pbj.demo2020.manage.entity.User;

/**
 * @pClassName: UserService
 * @author: pengbingjiang
 * @create: 2020/7/23 14:54
 * @description: TODO
 */
public interface UserService {
    DataResult<User> selectAll();

    DataResult<User> selectAllByPage(Integer page, Integer limit);
}
