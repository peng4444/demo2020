package cn.pbj.demo2020.ssm.dao;

import cn.pbj.demo2020.ssm.entity.SysFileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @pClassName: SysFileInfoDao
 * @author: pengbingjiang
 * @create: 2020/12/13 21:20
 * @description: TODO
 */
public interface SysFileInfoDao extends JpaRepository<SysFileInfo, Integer> {

}

