package cn.pbj.demo2020.springcloud.rpc.webservice;

import javax.jws.WebService;

/**
 * @ClassName: UserService
 * @Author: pbj
 * @Date: 2020/5/30 09:58
 * @Description: TODO
 */
@WebService
public interface UserService {
    public String sayHello(String name);
}


