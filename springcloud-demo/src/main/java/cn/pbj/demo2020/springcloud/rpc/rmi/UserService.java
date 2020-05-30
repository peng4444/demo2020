package cn.pbj.demo2020.springcloud.rpc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

//将要发布的服务的接口
public interface UserService extends Remote {
    public String helloRmi(String name) throws RemoteException;
}
