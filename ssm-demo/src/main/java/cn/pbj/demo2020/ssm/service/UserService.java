package cn.pbj.demo2020.ssm.service;

import cn.pbj.demo2020.ssm.entity.User;

/**
 * @pClassName: UserService
 * @author: pengbingjiang
 * @create: 2020/12/15 09:33
 * @description: TODO
 */
public interface UserService {
    /**
     * 根据账户查询用户信息
     * @param account
     * @return
     */
    User findByAccount(String account);
}
