package cn.pbj.demo2020.netty.consumer;

import cn.pbj.demo2020.netty.common.HelloService;
import cn.pbj.demo2020.netty.common.ResultBody;
import cn.pbj.demo2020.netty.netty.NettyClient;
/**
 * @ClassName: ClientBootStrap
 * @Author: pbj
 * @Date: 2020/5/25 14:34
 * @Description: TODO 创建服务消费者，启动netty框架的客户端，然后获取调用结果
 */

/**
 * 消费者
 */
public class ClientBootStrap {
    private static String host = "127.0.0.1";
    private static int port = 9999;

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }

    public static void main(String[] args) {
        //连接netty，并获得一个代理对象
        HelloService bean = NettyClient.getBean(HelloService.class);
        //测试返回结果为java bean
        ResultBody res = bean.hello("ffafa");
        System.out.println("res=====" + res.getMessage());
        //测试返回结果为 String
        String str = bean.str();
        System.out.println("str=====" + str);
    }
}
