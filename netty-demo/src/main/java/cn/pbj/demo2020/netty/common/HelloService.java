package cn.pbj.demo2020.netty.common;

/**
 * @ClassName: HelloService
 * @Author: pbj
 * @Date: 2020/5/25 14:29
 * @Description: TODO 服务消费者和服务提供者的公共接口和类
 */
public interface HelloService {
    ResultBody hello(String s);

    String str();
}
