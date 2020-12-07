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
1.API网关的作用
    在客户端与服务端之间增加一个API网关，所有的请求都会先经过网关这一层。API网关层可以把后端的多个服务进行整合，然后提供唯一的业务接口。客户端只需要调用这个接口获取数据及展示。
    - 针对所有请求进行统一鉴权，限流，熔断，日志。
        客户端身份认证、访问权限控制。在网关层进行请求拦截，获取请求中附带的用户身份信息，调用统一认证中心对请求进行身份认证，之后才检查身份有资源的访问权限。
        灰度发布：将要发布的功能先开放给一小部分用户使用，把影响范围控制在一个非常小的范围。在网关层可以通过灰度规则进行部分流量的路由，从而实现灰度发布。
    - 协议转化。针对后端多种不同的协议，在网关层统一处理后，以HTTP对外提供服务。
    - 统一错误码处理。
    - 请求转发，并且可以基于网关实现内网、外网隔离。
2.网关的本质及技术选型
    网关的本质应该是对请求进行路由转发，以及对请求进行前置和后置的过滤。
    常见的开源API网关实现方案：OpenResty、Zuul、GateWay等等
    OpenResty:由Nginx与Lua集成的一个高性能Web应用服务器。
    Zuul是Netflix开源非微服务网关：主要功能是路由转发和过滤。包括前置过滤器、路由过滤器、后置过滤器、错误过滤器。
    Spring Cloud Gateway：是Spring官方研发的API网关技术。
3.SpringCloudGateway网关实战
4.SpringCloudGateway原理分析
5.Route Predicate Factories
    SpringCloudGateway网关启动时基于Netty Server监听一个指定的端口（可以同过server.port属性自定义）。
    当客户端请求发送一个请求到网关时，网关会根据一系列predicate的匹配解决来决定访问那个Route路由，然后根据过滤器链进行请求的处理。
    1.指定时间规则匹配路由 # 希望在202年6月1号之前发生的请求都路由到http://www.example.com
        - id: before_route
          url: http://www.example.com
          predicates:
          - After=2020-06-01T24:00:00.000+08:00[Asia/Shanghai]   # 时间格式必须满足ZonedDateTime的格式
    2.Cookie匹配路由 判断请求中携带的cookie算法匹配配置的规则
        #当前请求需要携带一个name=chocolate，并且value需要通过正则表达式匹配mic，才能路由到http://www.example.com。
        - id: cookie_route
          url: http://www.example.com
          predicates:
          - Cookie=chocolate, mic
    3.Header匹配路由  判断请求中Header头消息对应的那么和value与Predicate配置的值是否匹配，value也是正则匹配形式。
        # 匹配请求中Header头中的name=X-Request-Id，并且value会根据正则表达式匹配\d+，也就是匹配1个以上的数字。
        - id: header_route
           url: http://www.example.com
           predicates:
           - Header=X-Request-Id, \d+ 
    4.Host匹配路由  匹配请求中Host字段进行路由
        # 当请求中Host的值符合时，才会将请求路由到http://www.example.com
        - id: host_route
          url: http://www.example.com
          predicates:
          - Host=**.somehost.com,**.anotherhost.com
    5.请求方法匹配路由  根据HTTP请求的Method属性来匹配以实现路由
        # 如果HTTP请求的方法是GET或POST，都会路由到http://www.example.com
        - id: method_route
          url: http://www.example.com
          predicates:
          - Method=GET,POST
    6.请求路径匹配路由  请求路径路由是比较常见的路由匹配规则，${segment}是一种比较特殊的占位符，/*表示单层路径匹配，/**表示多层路径匹配。
        # 匹配请求的URI为/red/*,/blue/*时，才会转发到http://www.example.com
        - id: path_route
          url: http://www.example.com
          predicates:
          - Path=/red/{segment},/blue/{segment}
6.Gateway Filter Factories
    Filter分为Pre类型的过滤器和Post类型的过滤器。
        Pre类型过滤器在请求转发到后端服务之前执行，在Pre类型过滤器链中可以做鉴权，限流等等操作。
        POST类型的过滤器在请求执行完成之后，将结果返回给客户端之前执行。
    Filter分两种实现，分别是GatewayFilter和GlobalFilter。前者会应用到所有的路由上，后者只会应用到单个路由后者一个分组的路由上。
    GatewayFilter
        - 1.AddRequestParameter GatewayFilter Factory 对所有匹配的请求添加一个查询参数。
            # 示例配置会对所有请求增加foo=bar这个参数
            - id: add_request_parameter_route
              url: http://www.example.com
              filters:
              - AddRequestParameter=foo,bar
        - 2.AddResponseHeader GatewayFilter Factory 对所有匹配的请求，在返回结果到客户端之前，在Header中添加相应的数据
                    # 示例配置会在Response中添加Header头，key=X-Response-Foo, Value=Bar
                    - id: add_request_header_route
                      url: http://www.example.com
                      filters:
                      - AddResponseParameter=X-Response-Foo, Bar
        - 3.RequestRateLimiter GatewayFilter Factory 该过滤器会对访问到当前网关的所有请求执行限流过滤。
                    # 示例配置使用Redis提供的令牌桶算法实现限流功能
                    - id: requestratelimiter_route
                      url: http://www.example.com
                      filters:
                      - name: RequestRateLimiter
                        args:
                            redis-rate-limiter.replenishRate: 10   # 令牌桶中令牌的填充速度代表允许每秒执行的请求数
                            redis-rate-limiter.burstCapacity: 20   # 令牌桶的容量，也就是令牌桶最多能够容纳的令牌数，表示每秒用户最大能够执行行的请求数量。
        - 4.Retry GatewayFilter Factory 为请求重试过滤器，当后端服务不可用时，网关会根据配置参数来发起重试请求
                    # 示例配置会对所有请求增加foo=bar这个参数
                    - id: retry_route
                      url: http://www.example.com
                      filters:
                      - name: Retry
                        args: 
                          retries: 3  # 请求重试次数，默认是3
                          status: 503 # HTTP请求返回的状态码，针对指定状态码进行重试。
    GlobalFilter：全局过滤链的执行顺序是，当Gateway接受到请求时，Filter Web Handler处理器会将所有的GlobalFilter实例及所有路由上配置的GatewayFilter实例添加到一条过滤器链中。
        该过滤器链里的所有过滤器都会安装@Order注解所指定的数字大写进行排序。
        - GatewayMetricsFilter，整合Ribbon针对下游服务实现负载均衡。
        - LoadBalancerClientFilter，整合Ribbon针对下游服务实现负载均衡
            # 该配置表示如果URI配置的是lb://example_service，那么这个过滤器会识别到lb://，并且使用SpringCloud LoadBalancerClient将example_service名称解析为时间访问的主机和端口地址。
                - id: loadbalance_route
                      uri: lb://example_service
                      predicates:
                      - Path:/service/**   
        - ForwardRoutingFilter，用于本地forward，请求不转发到下游服务器。
        - NettyRoutingFilter，使用Netty的HttpClient转发HTTP、HTTPS请求。
7.自定义过滤器
    1.自定义GatewayFilter
    2.自定义GlobalFilter
8.Gateway集成Nacos实现请求负载
9.Gateway集成Sentinel网关限流
```
### Spring Cloud Alibaba | 微服务分布式事务之Seata
[快速了解阿里微服务热门开源分布式事务框架——Seata](https://www.cnblogs.com/fengpinglangjingruma/p/13963902.html)
[阿里分布式事务seata入门（采坑）](https://www.cnblogs.com/sky-chen/p/11419942.html)
[Spring Cloud Alibaba | 微服务分布式事务之Seata](https://www.cnblogs.com/babycomeon/p/11504210.html)
[Spring Cloud同步场景分布式事务怎样做？试试Seata](https://www.cnblogs.com/zlt2000/p/11525417.html)
[SpringCloud系列之集成分布式事务Seata应用篇](https://www.cnblogs.com/chinaWu/p/13255200.html)

###
[快速搭建 SpringCloud 微服务开发环境的脚手架](https://www.cnblogs.com/xueweihan/p/11935291.html)
[[Spring Cloud 负载均衡初体验](https://www.cnblogs.com/Sinte-Beuve/p/11569789.html)]
[Spring Cloud 系列之 Apollo 配置中心（一）](https://www.cnblogs.com/mrhelloworld/p/apollo1.html)
[Spring Cloud开发人员如何解决服务冲突和实例乱窜？](https://www.cnblogs.com/zlt2000/p/11459390.html)

### Spring Cloud Alibaba 集成Dubbo
[SpringCloud系列之集成Dubbo应用篇](https://www.cnblogs.com/chinaWu/p/12818661.html)
[Dubbo Spring Cloud 之 HTTP 实战](https://www.cnblogs.com/babycomeon/p/11553757.html)
[SpringCloud微服务：基于Nacos组件，整合Dubbo框架](https://www.cnblogs.com/cicada-smile/p/13442936.html)

## 开源项目
[Cloud-Platform是国内首个基于Spring Cloud微服务化开发平台](https://gitee.com/geek_qi/cloud-platform)
