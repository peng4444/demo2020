package cn.pbj.demo2020.netty.provider;

import cn.pbj.demo2020.netty.common.HelloService;
import cn.pbj.demo2020.netty.common.ResultBody;

/**
 * @ClassName: HelloServiceImpl
 * @Author: pbj
 * @Date: 2020/5/25 14:32
 * @Description: TODO 服务提供者实现公共接口
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public ResultBody hello(String s) {
        System.out.println("收到消费者的请求。。" + s);
        ResultBody result=new ResultBody();
        result.setCode("1");
        result.setResult("返回成功");
        result.setMessage("你好,我已经收到了你的消费请求");
        return result;
    }

    @Override
    public String str() {
        return "我是一个字符串。。。";
    }
}
