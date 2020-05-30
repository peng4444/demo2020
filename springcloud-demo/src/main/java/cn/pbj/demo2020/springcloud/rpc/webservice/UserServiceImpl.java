package cn.pbj.demo2020.springcloud.rpc.webservice;

import javax.jws.WebService;

/**
 * @ClassName: UserServiceImpl
 * @Author: pbj
 * @Date: 2020/5/30 09:59
 * @Description: TODO
 */
@WebService
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "hello " + name + "~";
    }
}
