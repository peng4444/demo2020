# Springcloud&微服务
## SpringCloud全家桶
![](https://img2018.cnblogs.com/blog/273387/201903/273387-20190317154408417-983057661.png)
>> [用SpringCloud进行微服务架构演进](https://www.cnblogs.com/zhangs1986/p/10546973.html)
>> Spring Cloud是一个基于Spring Boot实现的云应用开发工具，它为基于JVM的云应用开发中涉及的配置管理、服务发现、断路器、
>> 智能路由、微代理、控制总线、全局锁、决策竞选、分布式会话和集群状态管理等操作提供了一种简单的开发框架。
### Spring Cloud Eureka 核心服务治理的组件（服务注册与发现）
>> Eureka是Netflix开源的一款提供服务注册和发现的产品，Eureka就是一个服务中心，将所有的可以提供的服务都注册到它这里来管理，
>> 其它各调用者需要的时候去注册中心获取，然后再进行调用，避免了服务之间的直接调用，方便后续的水平扩展、故障转移等。
### Spring Cloud Config 分布式系统的配置管理方案
>> 包含了Client和Server两个部分，其实就是Server端将所有的配置文件服务化，需要配置文件的服务实例去Config Server获取对应的数据。
>> 将所有的配置文件统一整理，避免了配置文件碎片化。如果服务运行期间改变配置文件，服务是不会得到最新的配置信息，需要解决这个问题就需要引入Refresh。
>> 可以在服务的运行期间重新加载配置文件，当所有的配置文件都存储在配置中心的时候，配置中心就成为了一个非常重要的组件。
### Hystrix 故障隔离和熔断
>> Hystrix会在某个服务连续调用N次不响应的情况下，立即通知调用端调用失败，避免调用端持续等待而影响了整体服务。
>> Hystrix间隔时间会再次检查此服务，如果服务恢复将继续提供服务。
>> 熔断的监控现在有两款工具：Hystrix-dashboard和Turbine。
### Spring Cloud Bus消息总线
>> Spring Cloud Bus通过轻量消息代理连接各个分布的节点。这会用在广播状态的变化（例如配置变化）或者其它的消息指令中。
>> Spring Cloud Bus的一个核心思想是通过分布式的启动器对Spring Boot应用进行扩展，也可以用来建立一个或多个应用之间的通信频道。
>> 目前唯一实现的方式是用AMQP消息代理作为通道。
### 服务网关
>> 通常会引入API Gateway作为轻量级网关，同时API Gateway中也会实现相关的认证逻辑从而简化内部服务之间相互调用的复杂度。 
>> Spring Cloud体系中支持API Gateway落地的技术就是Zuul。Spring Cloud Zuul路由是微服务架构中不可或缺的一部分，提供动态路由，监控，弹性，安全等的边缘服务。
>> Zuul是Netflix出品的一个基于JVM路由和服务端的负载均衡器。它的具体作用就是服务转发，接收并转发所有内外部的客户端调用。
>> 使用Zuul可以作为资源的统一访问入口，同时也可以在网关做一些权限校验等类似的功能。
### 链路跟踪 Spring Cloud Sleuth和Zipkin
>> Spring Cloud Sleuth为服务之间调用提供链路追踪。通过Sleuth可以很清楚的了解到一个服务请求经过了哪些服务，每个服务处理花费了多长时间。从而让我们可以很方便的理清各微服务间的调用关系。
>> 分布式链路跟踪需要Sleuth+Zipkin结合来实现，当然实现链路追踪的还有三方开源方案，如果zipkin实现的功能非常简单，图形化能力也不强，所以可以试试其它的方案，如pinpoint较成熟的框架等。
### Feign 声明式远程调度组件。
### Ribbon 负载均衡组件
### Spring Cloud Data Flow 
>> 大数据操作组件，它是Spring XD的替代品，也是一个混合计算模型，可以通过命令行的方式操作数据流
### Spring Cloud Task
>> 组件基于Spring Tsak，提供任务调度和任务管理的功能。
[SpringCloud2.0](https://www.cnblogs.com/cailijia52o/p/10428620.html)
## SpringCloud-Alibaba
【springcloud集合】[springcloud集合](https://www.cnblogs.com/babycomeon/category/1493460.html)
[Spring Cloud 从入门到精通](http://blog.didispace.com/spring-cloud-learning/)
【随笔分类】[ [Spring cloud 一步步实现广告系统](https://www.cnblogs.com/zhangpan1244/category/1522751.html)

[随笔分类 - Spring Cloud Alibaba](https://www.cnblogs.com/fx-blog/category/1569361.html)

[SpringCloud-Alibaba-Nacos 服务注册中心&配置中心](https://www.cnblogs.com/songjilong/p/12796258.html)

[双剑合璧 Nacos 结合 Sentinel 实现流量安全控制](https://www.cnblogs.com/mrhelloworld/p/sentinel-nacos.html)

[SpringCloud系列之集成Dubbo应用篇](https://www.cnblogs.com/chinaWu/p/12818661.html)
[Dubbo Spring Cloud 之 HTTP 实战](https://www.cnblogs.com/babycomeon/p/11553757.html)

[Java开发架构篇：领域驱动设计架构基于SpringCloud搭建微服务](https://www.cnblogs.com/xiaofuge/p/12938567.html)

[快速搭建 SpringCloud 微服务开发环境的脚手架](https://www.cnblogs.com/xueweihan/p/11935291.html)

[SpringCloud 中集成Sentinel+Feign实现服务熔断降级](cnblogs.com/NathanYang/p/11819881.html)

[[Spring Cloud 负载均衡初体验](https://www.cnblogs.com/Sinte-Beuve/p/11569789.html)]

[Spring Cloud 系列之 Apollo 配置中心（一）](https://www.cnblogs.com/mrhelloworld/p/apollo1.html)
[Spring Cloud开发人员如何解决服务冲突和实例乱窜？](https://www.cnblogs.com/zlt2000/p/11459390.html)
[随笔分类 - Spring Cloud](https://www.cnblogs.com/mrhelloworld/category/1658315.html)
## RPC
### [如何设计一个RPC系统](https://www.cnblogs.com/qcloud1001/p/10213274.html)
```markdown
RPC是一种方便的网络通信编程模型，由于和编程语言的高度结合，大大减少了处理网络数据的复杂度，让代码可读性也有可观的提高。
但是RPC本身的构成却比较复杂，由于受到编程语言、网络模型、使用习惯的约束，有大量的妥协和取舍之处。
```
### [Java实现远程服务生产与消费(RPC)的4种方法-RMI,WebService,HttpClient,RestTemplate](https://www.cnblogs.com/tanshaoshenghao/p/10796319.html)
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
##
[[一个项目的SpringCloud微服务改造过程](https://www.cnblogs.com/yixinjishu/p/11307121.html)]
[[Eureka 缓存结构以及服务感知优化](https://www.cnblogs.com/xmzJava/p/11359636.html) ]
[[服务注册组件——Eureka高可用集群搭建](https://www.cnblogs.com/noneplus/p/11374883.html)]
[[Nacos高可用集群解决方案-Docker版本](https://www.cnblogs.com/hellxz/p/nacos-cluster-docker.html)]

## SpringCloud项目
[maven聚合工程用spring boot搭建spring cloud微服务模块式开发项目](https://www.cnblogs.com/wxf-com/p/10731375.html)