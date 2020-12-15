package cn.pbj.demo2020.ssm.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @pClassName: PermissionService
 * @author: pengbingjiang
 * @create: 2020/12/15 09:35
 * @description: TODO
 */
public interface PermissionService {

    /**
     * 根据角色id查询权限
     * @param roleIds
     * @return
     */
    List<String> findByRoleId(@Param("roleIds") List<Integer> roleIds);
}

