# SpringCloudAlibaba微服务原理与实战阅读笔记

[书籍源码地址](https://github.com/2227324689/Spring-Cloud-Alibaba-)

## 第一章 微服务的发展史
```markdown
1.系统架构演变：单体-》SOA-》微服务
2.微服务架构的优点：
    - 复杂度可控
    - 技术选型更加灵活
    - 可扩展性更加强
    - 独立部署
    - 容错性
3.微服务面临的挑战：
    - 故障排查
    - 服务监控
    - 分布式架构的复杂性
    - 服务依赖
    - 运维成本
```
## 第二章 微服务解决方案之SpringCloud
```markdown
1.SpringCloud Netflix
    Eureka:服务注册与发现
    Zuul:服务网关
    Ribbon：负载均衡
    Feign：远程服务的客户端代理
    Hystrix: 断路器，提供服务熔断和限流功能
    Hystrix Dashboard: 监控面板
    Turbine: 将各个服务实例上的Hystrix监控信息进行统一聚合
2.SpringCloud Alibaba
    Nacos: 服务注册与发现
    Nacos: 分布式配置中心
    Sentinel：流量控制和服务降级
    RocketMQ：消息驱动
    Seate：分布式事物
    Dubbo：RPC通信
    OSS：阿里云对象存储
```
## 第三章 SpringCloud的核心之SpringBoot
```markdown
SpringCloud是基于SpringBoot提供的一套微服务解决方案，配置中心、服务注册和负载均衡。
SpringBoot是构建SpringCloud生态的基石。
1.Spring IOC/DI Bean的装配 
2.SpringBoot的价值
    - Starter组件，提供开箱即用的组件
    - 自动装配，自动根据上下文完成Bean的装配（重点） 
    - Actuator,SpringBoot应用的监控
    - SpringBoot CLI，基于命令行工具快速构建SpringBoot应用
3.SpringBoot自动装配
    - @SpringBootApplication注解中的@EnableAutoConfiguration注解开启自动装配
        自动装配的原理（源码）：
            - 通过@Import（AutoConfigurationImportSelector）实现配置类的导入，但是这里不是传统意义上的单个配置类装配
            - AutoConfigurationImportSelector类实现了ImportSelector接口，重写了方法selectImports，它用于实现选择性批量配置类的装配。
            - 通过Spring提供的SpringFactoriesLoader机制，扫描classpath路径下的META-INF/spring.factories，读取需要实现自动装配的配置类。
            - 通过条件筛选的方式，把不符合条件的配置类移除，最终完成自动装配。
    - @Enable注解，主要作用是吧相关的组件的Bean装配到IOC容器中。相当于@Configuration和@Bean注解的封装
    - @Conditional条件装配：Spring在解析@Configuration配置类时，如果该配置类增加了@Conditional注解，会根据该注解配置的条件来决定是否要实现Bean的装配。
4.手写实现一个Starter
    - SpringBoot的Starter组件的主要功能：
        - 涉及相关组件的Jar包依赖
        - 自动实现Bean的装配
        - 自动声明并且加载application。properties文件中的属性配置
```
## 第四章 微服务架构下的服务治理
```markdown
1.Dubbo
    Apache Dubbo是一个分布式服务框架，主要实现多个系统之间的高性能。透明化调用，简单来说就是一个RPC框架。与普通RPC框架不同的是，
    它提供了服务治理功能，比方说服务注册、监控、路由、容错等等。
    1.Dubbo与Spring的集成服务
    2.Dubbo与SpringBoot的集成服务
    3.Dubbo与ZooKeeper的集成服务
    4.Dubbo与SpringCloud的集成服务
    5.Dubbo的高级应用：集群容错、负载均衡、服务降级、主机绑定规则
    6.Dubbo核心源码：SPI机制、自适应扩展点、IOC和AOP、Dubbo如何与Spring集成。
```
## 第五章 服务注册与发现
```markdown
1.服务注册中心的主要功能：服务地址管理、服务注册、服务动态感知。
2.Alibaba Nacos致力于解决微服务中的统一配置、服务注册与发现等问题。
    它提供了一组简单易用的特性集，帮助开发者快速实现动态服务发现、服务配置、服务元数据集流量管理。
    Nacos的关键特性：
        服务发现和服务健康监测
        动态配置服务
        动态DNS服务
        服务及其元数据管理
    Nacos的基本使用
    Nacos的高可用部署
    Dubbo使用Nacos实现注册中心
    Spring Cloud Alibaba Nacos Discovery
    Nacos实现原理分析
    深入读取Nacos源码：服务注册、服务地址的获取、服务地址变化的感知。
```
## 第六章 Nacos实现统一配置管理
```markdown
1.Nacos配置中心
2.Nacos集成Spring Boot实现统一配置管理
3.Spring Cloud Alibaba Nacos Config
4.Nacos实现原理分析
5.Spring实现配置的加载
6.Nacos Config核心源码解析
```
## 第七章 基于Sentinel的微服务限流与熔断
```markdown
1.服务限流的作用和实现
    计数器算法、滑动窗口算法、令牌桶限流算法、漏桶限流算法
2.服务熔断与降级
3.分布式限流框架Sentinel
    Sentinel特性：丰富的应用场景、实时监控、开源生态支持、SPI扩展点支持。
4.Sentinel的基本应用
    1.定义资源，2、定义限流规则，3.检验规则算法生效。
5.SpringCloud集成Sentinel实践
6.Sentinel集成Nacos实现动态流控规则
7.Sentinel DashBoard集成Nacos实现规则同步
8.Dubbo集成Sentinel实现限流
9.Sentinel热点限流
10.Sentinel是工作原理
11.SpringCloud Sentinel工作原理分析
12.Sentinel核心源码分析
```
## 第八章 分布式事物
![两阶段提交完整的执行流程](https://github.com/peng4444/picgo/blob/main/img/%E6%8D%95%E8%8E%B7.PNG?raw=true)
![三阶段提交协议](https://github.com/peng4444/picgo/blob/main/img/20201117144951.png?raw=true)
```markdown
1.分布式事物问题的理论模型
    2PC：两阶段提交。第一阶段是事物的准备阶段，第二阶段是事物的提交或者回滚阶段。
        准备阶段：事物管理器（TM）通知资源管理器（RM）准备分支事物，记录事物日志，并告知事物管理器的准备结果。
        提交/回滚阶段：如果所有的的资源管理器（RM）在准备阶段都明确返回成功，则事物管理器（TM）向所有的资源管理器（RM）发起事物提交指令完成数据的变更。
            反之，如果任何一个资源管理器（RM）明确返回失败，则事物管理器（TM）会向所有的资源管理器（RM）发出事物回滚指令。
    两阶段提交的缺点：
        - 同步阻塞：任何一次指令都必须有明确指令才能继续下一步，否则会处于阻塞状态，占用的资源一直被锁定。
        - 过于保守：任何一个节点失败都会导致数据回滚。
        - 事物协调者的单点故障：如果协调者在第二阶段出现故障，其他的RM会一直处于锁定状态。
        - 脑裂导致数据不一致问题：在第二阶段一部分未收到commit请求的节点由于事物无法提交，导致数据出现不一致问题。
    3PC：三阶段提交协议是两阶段提交的改进，利用超时机制解决了同步阻塞的问题。
        CanCommit询问阶段：事物协调者向参与者发送事物执行请求，询问是否可以完成指令，会有超时终止机制。
        PreCommit准备阶段：事物协调者根据参与者的反馈决定是否继续执行。
        DoCommit提交或回滚阶段：返回成功事物协调者则向所有的参与者发起事物提交指令，反之中至指令来回滚事物。
2.分布式事物问题常见解决方案
    TCC补偿型方案：（两阶段提交的思想）第一阶段try，第二阶段Confirm/Cancel对Try阶段操作的确认和回滚。
        Try:这个阶段主要是对数据的校验或者资源的预留。
        confirm：确认真正指向的任务，只操作Try阶段预留的资源。
        Cancel：取消指向，释放Try阶段预留的资源。
    基于可靠性消息的最终一致性方案：利用消息中间件的可靠性机制来实现数据一致性。
    最大努力通知型：利用消息中间件不断的重试，直到消息确认回执达到最大重试次数。
3.分布式事务框架：Seata
    提供AT、TCC、Saga和XA事物模式。
    AT模式：
        - TM向TC注册全局事物，并生成全局唯一的XID。
        - RM向TC注册分支事物，并将其纳入该XID对应的全局事物范围。
        - RM向TC汇报资源的准备状态。
        - TC汇总所有事物参与者的执行状态，决定分布式事务是全部回滚还是提交。
        - TC通知所有RM提交/回滚事务。
    Saga模式：把一个业务流程中的长事务拆分为多个本地短事务，参与者提交给该本地短事务，当一个事物执行失败，通过补偿机制补偿已经成功的参与者。
    优势：一阶段直接提交本地事物；没有锁等待，性能较高；在事件驱动的模式下，短事务可以异步执行；补偿机制的实现比较简单。
    缺点：不提供原子性和隔离性支持。
4.AT模式Dubbo集成Seata实操
5.Spring Cloud Alibaba Seata实操
6.Seata AT模式的实现原理
```
## 第九章 RocketMQ分布式消息通信
```markdown
1.什么是RocketMQ
2.SpringCloudAlibaba RocketMQ
3.RocketMQ集群管理
4.如何实现顺序消息
5.如何实现事物消息
6.高性能设计
7.高可用设计
```
## 第十章 微服务网关之SpringCloud Gateway
```markdown
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
