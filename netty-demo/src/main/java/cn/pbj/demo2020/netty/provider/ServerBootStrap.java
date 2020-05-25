package cn.pbj.demo2020.netty.provider;

import cn.pbj.demo2020.netty.netty.NettyServer;
import cn.pbj.demo2020.netty.netty.NettyServerHandler;

/**
 * @ClassName: ServerBootStrap
 * @Author: pbj
 * @Date: 2020/5/25 14:33
 * @Description: TODO 启动netty框架的服务端
 */
public class ServerBootStrap {
    public static void main(String[] args) {
        NettyServerHandler.setClassNameMapping(new HelloServiceImpl());
        NettyServer.start(9999);
    }
}
