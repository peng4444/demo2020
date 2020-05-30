package cn.pbj.demo2020.springcloud.rpc.httpclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: OrderController
 * @Author: pbj
 * @Date: 2020/5/30 10:06
 * @Description: TODO
 */
@Controller
public class OrderController {
    /**
     * 接收http请求, 响应订单集合, 异步响应
     * 将list集合序列化为json串响应
     * @param uid
     * @return
     */
    @RequestMapping("/loadOrderList2")
    @ResponseBody
    public List<Order> loadOrderList2(String uid){
        System.out.println("uid: " + uid);

        //模拟订单数据
        Order o1 = new Order();
        o1.setId("111");
        o1.setTotal(333.33);
        o1.setDate("2019-4-29");

        Order o2 = new Order();
        o2.setId("222");
        o2.setTotal(444.44);
        o2.setDate("2019-5-29");

        Order o3 = new Order();
        o3.setId("333");
        o3.setTotal(555.55);
        o3.setDate("2019-6-29");

        List<Order> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);
        list.add(o3);

        return list;
    }
}
