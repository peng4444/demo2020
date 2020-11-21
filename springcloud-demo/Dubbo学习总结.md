# Dubbo学习总结

[TOC]


[参考视频1：深刻了解dubbo底层源码](https://www.bilibili.com/video/av58338686)

[参考视频2：Dubbo底层原理与面试经验](https://www.bilibili.com/video/av53428315/)

[从零开始认识Dubbo](https://www.cnblogs.com/alterem/p/11211728.html)

[dubbo](https://www.cnblogs.com/xxbiao/tag/dubbo/)

[手写RPC框架注释代码](https://www.cnblogs.com/mseddl/p/11531465.html)

[一文带你实现RPC框架](https://www.cnblogs.com/endless-code/p/11235624.html)

[微服务调用为啥用RPC框架，http不更简单吗？](https://zhuanlan.zhihu.com/p/61364466)

[Dubbo服务注册与发现](https://www.cnblogs.com/mzq123/p/11221570.html)

[springboot2.x纯注解整合dubbo](https://www.cnblogs.com/chywx/p/11180719.html)

[Dubbo 与 Spring Cloud 完美结合](https://www.cnblogs.com/babycomeon/p/11546737.html)

[[Dubbo面试八连问，这些你都能答上来吗？](https://www.cnblogs.com/javazhiyin/p/11966271.html)]

[[Zookeeper+Dubbo项目demo搭建](https://www.cnblogs.com/iUtopia/p/11653098.html)]

[Dubbo 入门-细说分布式与集群](https://www.cnblogs.com/yangyuanhu/p/12439106.html)
[享一个让我进入阿里中间件的个人项目](https://www.cnblogs.com/leiwei/p/11839018.html)
[吐血输出：2万字长文带你细细盘点Dubbo五种负载均衡策略。](https://www.cnblogs.com/thisiswhy/p/13020501.html)

>> 1.1 服务调用超时问题怎么解决？
    1.2 Dubbo支持哪些序列化方式？
    1.3 Dubbo和SpringCloud的关系？
    1.4 Dubbo的架构设计？一共划分了哪些层？
    1.5 Dubbo的默认集群容错方案？
    1.6 Dubbo使用的是什么通信框架?
    1.7 Dubbo的主要应用场景？
    1.8 Dubbo服务注册与发现的流程？流程说明。
    1.9 Dubbo的集群容错方案有哪些？
    1.10 Dubbo的四大组件
    1.11 Dubbo在安全机制方面是如何解决的
    1.12 Dubbo和SpringCloud的区别？
    1.13 Dubbo支持哪些协议，每种协议的应用场景，优缺点？
    1.14 Dubbo的核心功能有哪些？
    1.15 Dubbo的注册中心集群挂掉，发布者和订阅者之间还能通信么？
    1.16 Dubbo集群的负载均衡有哪些策略
    1.17 为什么需要服务治理？
    1.18 Dubbo超时时间怎样设置？
>> 6.dubbo是如何利用接口就可以通信的。
[《我想进大厂》之Dubbo普普通通9问](https://www.cnblogs.com/ilovejaney/p/13836719.html)
#### 1.Dubbo提供哪些功能和主要组成【5+】
```markdown
Dubbo的主要功能
    基于接口的远程调用
    日常和负载均衡
    自动服务注册和发现
Dubbo服务的关键节点
    Provider:暴露服务的服务提供方
    Consumer:调用远程服务的服务消费方
    Register:服务注册与发现的注册中心
    Monitor:统计服务的调用次数和调用时间的监控中心
    Container:服务运行容器
```
[Dubbo直连方式](https://www.cnblogs.com/mengd/p/13543836.html)
#### 2.Dubbo的服务注册流程【5+】
[Dubbo服务注册与发现的流程图](http://www.mianshigee.com/question/20621dou)
![](http://cdn.mianshigee.com/upload/article/20200625/20181002113850939.png)
![](http://cdn.mianshigee.com/upload/article/20200625/20190731230024_81309.jpg)
```markdown
Provider(提供者)绑定指定端口并启动服务
    指供者连接注册中心，并发本机IP、端口、应用信息和提供服务信息发送至注册中心存储
Consumer(消费者），连接注册中心 ，并发送应用信息、所求服务信息至注册中心
    注册中心根据 消费 者所求服务信息匹配对应的提供者列表发送至Consumer 应用缓存。
Consumer 在发起远程调用时基于缓存的消费者列表择其一发起调用。
Provider 状态变更会实时通知注册中心、在由注册中心实时推送至Consumer
```
#### 1.Dubbo的容错机制
```markdown
1.失败自动切换，当出现失败，重试其它服务器。通常用于读操作，但重试会带来更长延迟。可通过retries="2"来设置重试次数
2.快速失败，只发起一次调用，失败立即报错。通常用于非幂等性的写操作，比如新增记录。
3.失败安全，出现异常时，直接忽略。通常用于写入审计日志等操作。
4.失败自动恢复，后台记录失败请求，定时重发。通常用于消息通知操作。
5.并行调用多个服务器，只要一个成功即返回。通常用于实时性要求较高的读操作，但需要浪费更多服务资源。可通过forks="2"来设置最大并行数。
6.广播调用所有提供者，逐个调用，任意一台报错则报错。通常用于通知所有提供者更新缓存或日志等本地资源信息
```
#### 2.Dubbo注册中心挂了还可以继续通信么
```markdown
可以，因为刚开始初始化的时候，消费者会将提供者的地址等信息拉取到本地缓存，所以注册中心挂了可以继续通信。
```
#### 3.Dubbo提供的线程池
```markdown
1.fixed：固定大小线程池，启动时建立线程，不关闭，一直持有。 
2.cached：缓存线程池，空闲一分钟自动删除，需要时重建。 
3.limited：可伸缩线程池，但池中的线程数只会增长不会收缩。(为避免收缩时突然来了大流量引起的性能问题)。
```
#### 4.Dubbo框架设计结构
```markdown
服务接口层：该层是与实际业务逻辑相关的，根据服务提供方和服务消费方的业务设计对应的接口和实现。
配置层：对外配置接口，以ServiceConfig和ReferenceConfig为中心，可以直接new配置类，也可以通过spring解析配置生成配置类。
服务代理层：服务接口透明代理，生成服务的客户端Stub和服务器端Skeleton，以ServiceProxy为中心，扩展接口为ProxyFactory。
服务注册层：封装服务地址的注册与发现，以服务URL为中心，扩展接口为RegistryFactory、Registry和RegistryService。可能没有服务注册中心，此时服务提供方直接暴露服务。
集群层：封装多个提供者的路由及负载均衡，并桥接注册中心，以Invoker为中心，扩展接口为Cluster、Directory、Router和LoadBalance。将多个服务提供方组合为一个服务提供方，实现对服务消费方来透明，只需要与一个服务提供方进行交互。
监控层：RPC调用次数和调用时间监控，以Statistics为中心，扩展接口为MonitorFactory、Monitor和MonitorService。
远程调用层：封将RPC调用，以Invocation和Result为中心，扩展接口为Protocol、Invoker和Exporter。Protocol是服务域，它是Invoker暴露和引用的主功能入口，它负责Invoker的生命周期管理。Invoker是实体域，它是Dubbo的核心模型，其它模型都向它靠扰，或转换成它，它代表一个可执行体，可向它发起invoke调用，它有可能是一个本地的实现，也可能是一个远程的实现，也可能一个集群实现。
信息交换层：封装请求响应模式，同步转异步，以Request和Response为中心，扩展接口为Exchanger、ExchangeChannel、ExchangeClient和ExchangeServer。
网络传输层：抽象mina和netty为统一接口，以Message为中心，扩展接口为Channel、Transporter、Client、Server和Codec。
数据序列化层：可复用的一些工具，扩展接口为Serialization、 ObjectInput、ObjectOutput和ThreadPool。
```