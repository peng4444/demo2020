# Springcloud&微服务

## SpringCloud-Alibaba
【springcloud集合】[springcloud集合](https://www.cnblogs.com/babycomeon/category/1493460.html)

【随笔分类】[ [Spring cloud 一步步实现广告系统](https://www.cnblogs.com/zhangpan1244/category/1522751.html)

[随笔分类 - Spring Cloud Alibaba](https://www.cnblogs.com/fx-blog/category/1569361.html)

[SpringCloud-Alibaba-Nacos 服务注册中心&配置中心](https://www.cnblogs.com/songjilong/p/12796258.html)

[SpringCloud系列之集成Dubbo应用篇](https://www.cnblogs.com/chinaWu/p/12818661.html)

[Java开发架构篇：领域驱动设计架构基于SpringCloud搭建微服务](https://www.cnblogs.com/xiaofuge/p/12938567.html)

[快速搭建 SpringCloud 微服务开发环境的脚手架](https://www.cnblogs.com/xueweihan/p/11935291.html)

[SpringCloud 中集成Sentinel+Feign实现服务熔断降级](cnblogs.com/NathanYang/p/11819881.html)

[[Spring Cloud 负载均衡初体验](https://www.cnblogs.com/Sinte-Beuve/p/11569789.html)]

[Spring Cloud 系列之 Apollo 配置中心（一）](https://www.cnblogs.com/mrhelloworld/p/apollo1.html)

## RPC
[如何设计一个 RPC 系统](https://www.cnblogs.com/qcloud1001/p/10213274.html)
```markdown
RPC是一种方便的网络通信编程模型，由于和编程语言的高度结合，大大减少了处理网络数据的复杂度，让代码可读性也有可观的提高。
但是RPC本身的构成却比较复杂，由于受到编程语言、网络模型、使用习惯的约束，有大量的妥协和取舍之处。
```
[Java实现远程服务生产与消费(RPC)的4种方法-RMI,WebService,HttpClient,RestTemplate](https://www.cnblogs.com/tanshaoshenghao/p/10796319.html)
```markdown
rmi:    
    1.创建UserService接口.
    2.创建UserServiceImpl实现类,除了实现UserService接口外, 还要继承UnicastRemoteObject类。
    3.发布远程服务
    4.创建rmi-consumer项目
    5.远程服务消费者对远程服务发起调用.
    通过最后的输出我们看到获得的远程服务对象是动态代理产生的。
webService:
    1.首先创建远程服务接口UserService及其实现类UserServiceImpl.
    2.发布远程服务, 过程和rmi差不多, 需要提供远程服务的访问地址和具体的远程服务实现类, 使用Endpoint类的publish()方法进行发布, 这都是JDK封装好的.
        查看的方法是在浏览器中输入远程服务的访问地址加上?wdsl, 比如本案例中是http://localhost:9999/ws?wsdl
        注意, 在客户端调用远程方法时需要用工具对wdsl文档进行解析, 并获得调用远程方法的工具类. 具体操作见下一段.
        和rmi不同的是, WebService发布后, 调用者可以通过查看它的文档对远程服务发起调用.
    3.首先根据文档获得调用远程服务的工具类, JDK已经为我们封装好了获取的工具, 它在bin目录下, 名字是wsimport
    4.打开命令行, 在命令行中输入解析命令
    5. 可以看到命令执行完后, 指定的包中出现一堆相关的类, 最直接调用到的类是UserServiceImplService.   
HttpClient:
    在服务消费端使用HttpClient发送请求, 可以理解为模拟浏览器发送post/get请求.
     HttpClient为我们封装了拼接一个请求的细节, 使得发送一个请求变得容易.
RestTemplate:
    通过spring提供的RestTemplate实现远程服务的生产与消费。
```
## 微服务
[微服务、分库分表、分布式事务管理、APM链路跟踪性能分析演示项目](https://www.cnblogs.com/RicCC/p/12105120.html)

[我的微服务之路](https://www.cnblogs.com/xuanbg/p/12079024.html)

[微服务的数据库设计](https://www.cnblogs.com/code-craftsman/p/11702814.html)

##
