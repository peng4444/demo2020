package cn.pbj.demo2020.ssm.dao;

import cn.pbj.demo2020.ssm.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @pClassName: RoleMapper
 * @author: pengbingjiang
 * @create: 2020/12/15 09:28
 * @description: TODO
 */
public interface RoleMapper {

    /**
     * 根据userId查询角色信息
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(@Param("userId") Integer userId);
}