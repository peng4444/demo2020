package cn.pbj.demo2020.ssm.service.impl;

import cn.pbj.demo2020.ssm.dao.RoleMapper;
import cn.pbj.demo2020.ssm.entity.Role;
import cn.pbj.demo2020.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @pClassName: RoleServiceImpl
 * @author: pengbingjiang
 * @create: 2020/12/15 09:34
 * @description: TODO
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据userId查询角色信息
     * @param id
     * @return
     */
    @Override
    public List<Role> findRoleByUserId(Integer id) {
        return roleMapper.findRoleByUserId(id);
    }
}
