# SpringCloud Alibaba 学习

[TOC]


## SpringCloud-Alibaba
[随笔分类 - Spring Cloud Alibaba](https://www.cnblogs.com/fx-blog/category/1569361.html)
[随笔分类 - Spring Cloud Alibaba](https://www.cnblogs.com/zhixie/category/1759831.html)

[Spring Cloud Alibaba微服务生态的基础实践](https://www.cnblogs.com/zhuhuix/p/13679153.html)
[Spring Cloud Alibaba生态探索：Dubbo、Nacos及Sentinel的完美结合](https://www.cnblogs.com/zhuhuix/p/13710177.html)
[SpringCloud系列之Nacos+Dubbo+Seata应用篇](https://www.cnblogs.com/chinaWu/p/13671620.html)
### SpringCloud-Alibaba-Nacos 服务注册中心&配置中心
[Nacos官网](https://nacos.io/zh-cn/index.html)
[搞微服务用阿里开源的 Nacos 真香啊！](https://www.cnblogs.com/xueweihan/p/13961778.html)
[Spring Cloud Alibaba系列（一）nacos作为服务注册中心](https://www.cnblogs.com/zhixie/p/12848317.html)
[SpringCloud-Alibaba-Nacos 服务注册中心&配置中心](https://www.cnblogs.com/songjilong/p/12796258.html)
[Nacos高可用集群解决方案-Docker版本](https://www.cnblogs.com/hellxz/p/nacos-cluster-docker.html)
[Linux下使用Docker部署nacos-server:1.4.0（单机模式）](https://www.cnblogs.com/haoxianrui/p/14059009.html)
[SpringBoot2.0+Nacos+Sentinel流控规则集中存储](https://www.cnblogs.com/smallSevens/p/11553695.html)
[SpringBoot开发案例Nacos配置管理中心](https://www.cnblogs.com/smallSevens/p/11223830.html)

```markdown
在spring cloud版本中我们使用eureka、consul等做为服务注册中心，使用spring cloud config做为配置中心。
而在spring cloud alibaba中，使用nacos组件即可完成服务注册发现与服务配置两大功能。
```
### Spring-cloud-Alibaba-feign 服务调用
[Spring Cloud Alibaba学习笔记（4） - Feign配置与使用](https://www.cnblogs.com/fx-blog/p/11714109.html)
[Spring Cloud Alibaba系列（三）使用feign进行服务调用](https://www.cnblogs.com/zhixie/p/12880245.html)
```markdown
Feign是一个声明式Web Service客户端。
使用Feign能让编写Web Service客户端更加简单，它的使用方法是定义一个接口，然后在上面添加注解，同时也支持JAX-RS标准的注解。Feign也支持可拔插式的编码器和解码器。
Spring Cloud对Feign进行了封装，使其支持了Spring MVC标准注解和HttpMessageConverters。Feign可以与Eureka和Ribbon组合使用以支持负载均衡。
```
### Spring-cloud-Alibaba-Sentinel
[参考视频：全方位掌握阿里巴巴分布式服务架构流量控件Sentinel](https://www.bilibili.com/video/BV12A411E7aX)
[视频笔记：Sentinel从入门到精通](https://blog.csdn.net/m_awdawdw/article/details/109023535)
[Spring Cloud Alibaba学习笔记（5） - 整合Sentinel及Sentinel规则](https://www.cnblogs.com/fx-blog/p/11720220.html)
[Spring Cloud Alibaba学习笔记（6） - Sentinel使用](https://www.cnblogs.com/fx-blog/p/11725989.html)
[Spring Cloud Alibaba学习笔记（7） - Sentinel规则持久化及生产环境使用](https://www.cnblogs.com/fx-blog/p/11726189.html)

[双剑合璧 Nacos 结合 Sentinel 实现流量安全控制](https://www.cnblogs.com/mrhelloworld/p/sentinel-nacos.html)
[SpringBoot2.0+阿里巴巴Sentinel动态限流实战](https://www.cnblogs.com/smallSevens/p/11531534.html)			
[SpringBoot2.0+InfluxDB+Sentinel实时监控数据存储](https://www.cnblogs.com/smallSevens/p/11576263.html)
[SpringCloud 中集成Sentinel+Feign实现服务熔断降级](cnblogs.com/NathanYang/p/11819881.html)
[[扛住阿里双十一高并发流量，Sentinel是怎么做到的？](https://www.cnblogs.com/caison/p/11673047.html)]

```markdown

```
### Spring Cloud Gateway
```markdown
Spring Cloud Gateway是Spring官方基于Spring 5.0，Spring Boot2.0 和Project Reactor等技术开发的网关，
Spring Cloud Gateway旨在为微服务架构提供一种简单而有效的统一的API路由管理方式。
Spring Cloud Gateway作为Spring Cloud生态系中的网关，目标是替代Netflix ZUUL，
其不仅提供统一的路由方式，并且基于Filter链的方式提供了网关基本的功能，例如：安全，监控/埋点，和限流等。
Spring Cloud Gateway功能特征
    基于 Spring Framework 5，Project Reactor 和 Spring Boot 2.0
    动态路由
    Predicates 和 Filters 作用于特定路由
    集成 Hystrix 断路器
    集成 Spring Cloud DiscoveryClient
    易于编写的 Predicates 和 Filters
    限流
    路径重写
Spring Cloud Gateway工作流程：
    客户端向Spring Cloud Gateway发出请求。然后在Gateway Handler Mapping中找到与请求相匹配的路由，将其发送到Gateway Web Handler。
    Handler再通过指定的过滤器链来将请求发送到我们实际的服务执行业务逻辑，然后返回。
    过滤器之间用虚线分开是因为过滤器可能会在发送代理请求之前（pre）或之后（post）执行业务逻辑。
```

###
[快速搭建 SpringCloud 微服务开发环境的脚手架](https://www.cnblogs.com/xueweihan/p/11935291.html)
[[Spring Cloud 负载均衡初体验](https://www.cnblogs.com/Sinte-Beuve/p/11569789.html)]
[Spring Cloud 系列之 Apollo 配置中心（一）](https://www.cnblogs.com/mrhelloworld/p/apollo1.html)
[Spring Cloud开发人员如何解决服务冲突和实例乱窜？](https://www.cnblogs.com/zlt2000/p/11459390.html)

### Spring Cloud Alibaba 集成Dubbo
[SpringCloud系列之集成Dubbo应用篇](https://www.cnblogs.com/chinaWu/p/12818661.html)
[Dubbo Spring Cloud 之 HTTP 实战](https://www.cnblogs.com/babycomeon/p/11553757.html)
[SpringCloud微服务：基于Nacos组件，整合Dubbo框架](https://www.cnblogs.com/cicada-smile/p/13442936.html
