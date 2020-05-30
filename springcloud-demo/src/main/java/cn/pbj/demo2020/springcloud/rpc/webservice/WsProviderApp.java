package cn.pbj.demo2020.springcloud.rpc.webservice;

import javax.xml.ws.Endpoint;

/**
 * @ClassName: WsProviderApp
 * @Author: pbj
 * @Date: 2020/5/30 09:59
 * @Description: TODO
 */
public class WsProviderApp {
    public static void main(String[] args) {
        //发布的WebService的被访问地址
        String address = "http://localhost:9999/ws";
        //创建远程服务对象
        UserService userService = new UserServiceImpl();
        //发布服务
        Endpoint.publish(address, userService);
        System.out.println("远程服务已经发布...");
    }
}
