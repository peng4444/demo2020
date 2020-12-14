package cn.pbj.demo2020.ssm.dao;

import cn.pbj.demo2020.ssm.entity.SysCode;

import java.util.List;

/**
 * @pClassName: SysCodeDao
 * @author: pengbingjiang
 * @create: 2020/12/14 10:22
 * @description: TODO
 */
public interface SysCodeDao {

    /**
     * 查询所有数字字典
     */
    List<SysCode> queryCodeAll();

}
