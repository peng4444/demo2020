package cn.pbj.demo2020.manage.controller;

import cn.pbj.demo2020.manage.common.DataResult;
import cn.pbj.demo2020.manage.entity.Order;
import cn.pbj.demo2020.manage.service.OrderService;
import cn.pbj.demo2020.manage.vo.BarVOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @pClassName: OrderController
 * @author: pengbingjiang
 * @create: 2020/7/23 18:50
 * @description: TODO
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/selectAll")
    public DataResult<Order> selectALL() {
        return orderService.selectAll();
    }

    @RequestMapping("/selectOrderEcharts")
    @ResponseBody
    public BarVOs selectOrderEcharts() {
        return orderService.selectOrderEcharts();
    }

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }
}
