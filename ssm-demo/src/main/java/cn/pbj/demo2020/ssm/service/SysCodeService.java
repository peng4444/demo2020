package cn.pbj.demo2020.ssm.service;

import cn.pbj.demo2020.ssm.entity.SysCode;

import java.util.List;

/**
 * @pClassName: SysCodeService
 * @author: pengbingjiang
 * @create: 2020/12/14 10:23
 * @description: TODO
 */
public interface SysCodeService {

    /**
     * 查询所有数字字典
     */
    List<SysCode> queryCodeAll();
}
