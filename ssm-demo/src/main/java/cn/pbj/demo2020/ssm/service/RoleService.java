package cn.pbj.demo2020.ssm.service;

import cn.pbj.demo2020.ssm.entity.Role;

import java.util.List;

/**
 * @pClassName: RoleService
 * @author: pengbingjiang
 * @create: 2020/12/15 09:34
 * @description: TODO
 */
public interface RoleService {

    /**
     * 根据userId查询角色信息
     * @param id
     * @return
     */
    List<Role> findRoleByUserId(Integer id);
}

