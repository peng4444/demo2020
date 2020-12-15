package cn.pbj.demo2020.ssm.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @pClassName: PermissionMapper
 * @author: pengbingjiang
 * @create: 2020/12/15 09:29
 * @description: TODO
 */
public interface PermissionMapper {

    /**
     * 根据角色id查询权限
     * @param roleIds
     * @return
     */
    List<String> findByRoleId(@Param("roleIds") List<Integer> roleIds);
}
