package cn.pbj.demo2020.ssm.dao;

import cn.pbj.demo2020.ssm.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @pClassName: UserMapper
 * @author: pengbingjiang
 * @create: 2020/12/15 09:27
 * @description: TODO
 */
public interface UserMapper {
    /**
     * 根据账户查询用户信息
     * @param account
     * @return
     */
    User findByAccount(@Param("account") String account);
}

