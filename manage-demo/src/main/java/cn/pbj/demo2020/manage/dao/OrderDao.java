package cn.pbj.demo2020.manage.dao;

import cn.pbj.demo2020.manage.entity.Order;
import cn.pbj.demo2020.manage.entity.User;
import cn.pbj.demo2020.manage.vo.BarVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao extends BaseMapper<User> {
    List<Order> selectAll();

    List<BarVO> selectOrderEcharts();
}
