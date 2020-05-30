package cn.pbj.demo2020.springcloud.rpc.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @ClassName: UserServiceImpl
 * @Author: pbj
 * @Date: 2020/5/30 09:49
 * @Description: TODO
 */
//将要发布的服务的实现类
public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    public UserServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String helloRmi(String name) throws RemoteException {
        return "hello " + name;
    }
}
