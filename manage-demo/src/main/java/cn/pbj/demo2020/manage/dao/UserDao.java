package cn.pbj.demo2020.manage.dao;

import cn.pbj.demo2020.manage.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
    List<User> selectAll();
}
