package cn.pbj.demo2020.ssm.service.impl;

import cn.pbj.demo2020.ssm.dao.PermissionMapper;
import cn.pbj.demo2020.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @pClassName: PermissionServiceImpl
 * @author: pengbingjiang
 * @create: 2020/12/15 09:35
 * @description: TODO
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 根据角色id查询权限
     * @param roleIds
     * @return
     */
    @Override
    public List<String> findByRoleId(List<Integer> roleIds) {
        return permissionMapper.findByRoleId(roleIds);
    }
}

