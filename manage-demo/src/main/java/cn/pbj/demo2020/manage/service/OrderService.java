package cn.pbj.demo2020.manage.service;

import cn.pbj.demo2020.manage.common.DataResult;
import cn.pbj.demo2020.manage.entity.Order;
import cn.pbj.demo2020.manage.vo.BarVOs;

public interface OrderService{
    DataResult<Order> selectAll();

    BarVOs selectOrderEcharts();
}
