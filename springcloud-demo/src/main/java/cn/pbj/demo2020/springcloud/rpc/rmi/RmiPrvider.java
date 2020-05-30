package cn.pbj.demo2020.springcloud.rpc.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @ClassName: RmiPrvider
 * @Author: pbj
 * @Date: 2020/5/30 09:50
 * @Description: TODO
 */
public class RmiPrvider {
    public static void main(String[] args) {
        try {
            //完成远程服务的发布
            LocateRegistry.createRegistry(8888);//将远程服务发布在本地的8888端口
            String name = "rmi://localhost:8888/rmi";//发布的远程服务被访问的url
            UserService userService = new UserServiceImpl();//创建一个提供具体服务的远程对象
            Naming.bind(name, userService);//给远程服务绑定一个url
            System.out.println("--- 已发布rmi远程服务 ---");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
