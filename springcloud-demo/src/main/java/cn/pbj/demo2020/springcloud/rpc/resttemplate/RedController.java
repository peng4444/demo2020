package cn.pbj.demo2020.springcloud.rpc.resttemplate;

import cn.pbj.demo2020.springcloud.rpc.httpclient.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RedController
 * @Author: pbj
 * @Date: 2020/5/30 10:14
 * @Description: TODO
 * 通过一个红包系统和订单系统进行演示, 红包系统访问订单系统, 获得某个用户的订单信息, 派发红包.
 * 订单系统继续沿用HttpClient中的订单系统, 通过访问loadOrderList2方法能返回一个订单集合Json字符串.
 */
@Controller
public class RedController {
    //注入由spring提供的RestTemplate对象
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 发送远程的http请求, 消费http服务
     * 获得订单对象的集合
     */
    @RequestMapping("/loadOrderList3")
    @ResponseBody
    public List<ResponseEntity<Order[]>> loadOrderList3(String uid){
        //发送远程http请求的url
        String url = "http://localhost:7070/order/loadOrderList2";
        //发送到远程服务的参数
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("uid", uid);

        //通过RestTemplate对象发送post请求
        ResponseEntity<Order[]> entitys = restTemplate.postForEntity(url, params, Order[].class);

        //查看响应的状态码
        System.out.println(entitys.getStatusCodeValue());

        //查看响应头
        HttpHeaders headMap = entitys.getHeaders();
        for(Map.Entry<String, List<String>> m : headMap.entrySet()){
            System.out.println(m.getKey() + ": " + m.getValue());
        }

        return Arrays.asList(entitys);
    }
}
