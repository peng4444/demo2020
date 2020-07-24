package cn.pbj.demo2020.manage.service.impl;

import cn.pbj.demo2020.manage.common.DataResult;
import cn.pbj.demo2020.manage.dao.OrderDao;
import cn.pbj.demo2020.manage.entity.Order;
import cn.pbj.demo2020.manage.service.OrderService;
import cn.pbj.demo2020.manage.vo.BarVO;
import cn.pbj.demo2020.manage.vo.BarVOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @pClassName: OrderServiceImpl
 * @author: pengbingjiang
 * @create: 2020/7/24 20:06
 * @description: TODO
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Override
    public DataResult<Order> selectAll() {
        DataResult<Order> dataResult = new DataResult<>();
        dataResult.setCode(0);
        dataResult.setMsg("查询所有订单");
        dataResult.setCount(orderDao.selectAll().size());
        dataResult.setData(orderDao.selectAll());
        return dataResult;
    }

    @Override
    public BarVOs selectOrderEcharts() {
        List<BarVO> barVOS = orderDao.selectOrderEcharts();
        List<String> name = new ArrayList<>();
        List<String> value = new ArrayList<>();
        for (BarVO barVO : barVOS) {
            name.add(barVO.getNames());
            value.add(barVO.getValues());
        }
        BarVOs barVOs = new BarVOs();
        barVOs.setName(name);
        barVOs.setValue(value);
        return barVOs;
    }
}
