package cn.pbj.demo2020.springcloud.rpc.rmi;

import java.rmi.Naming;

/**
 * @ClassName: RmiConsumer
 * @Author: pbj
 * @Date: 2020/5/30 09:51
 * @Description: TODO
 */
public class RmiConsumer {
    public static void main(String[] args) {
        try {
            //发布远程服务的访问url
            String name = "rmi://localhost:8888/rmi";
            //通过发布远程服务的url, 获取远程服务的代理对象
            UserService userService = (UserService) Naming.lookup(name);
            System.out.println("获得的远程服务的代理对象:" + userService.getClass().getName());
            String result = userService.helloRmi("rmi");//拿到远程方法调用的结果
            System.out.println("result: " + result);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//最后输出
//    获得的远程服务的代理对象:com.sun.proxy.$Proxy0
//    result: hello rmi
}
