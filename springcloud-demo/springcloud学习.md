# Springcloud&微服务

[TOC]


## SpringCloud全家桶
[springcloud集合](https://www.cnblogs.com/babycomeon/category/1493460.html)
[Spring Cloud 从入门到精通](http://blog.didispace.com/spring-cloud-learning/)
[思维导图概览SpringCloud](https://www.cnblogs.com/three-fighter/p/13485459.html)
[随笔分类 - Spring Cloud](https://www.cnblogs.com/zhixie/category/1759831.html)
[Spring cloud 一步步实现广告系统](https://www.cnblogs.com/zhangpan1244/category/1522751.html)
[随笔分类 - Spring Cloud](https://www.cnblogs.com/mrhelloworld/category/1658315.html)
[一个项目的SpringCloud微服务改造过程](https://www.cnblogs.com/yixinjishu/p/11307121.html)
[maven聚合工程用spring boot搭建spring cloud微服务模块式开发项目](https://www.cnblogs.com/wxf-com/p/10731375.html)
[Eureka 缓存结构以及服务感知优化](https://www.cnblogs.com/xmzJava/p/11359636.html) 
[服务注册组件——Eureka高可用集群搭建](https://www.cnblogs.com/noneplus/p/11374883.html)
![](https://img2018.cnblogs.com/blog/273387/201903/273387-20190317154408417-983057661.png)
>> [用SpringCloud进行微服务架构演进](https://www.cnblogs.com/zhangs1986/p/10546973.html)
>> Spring Cloud是一个基于Spring Boot实现的云应用开发工具，它为基于JVM的云应用开发中涉及的配置管理、服务发现、断路器、
>> 智能路由、微代理、控制总线、全局锁、决策竞选、分布式会话和集群状态管理等操作提供了一种简单的开发框架。
![](https://img-blog.csdnimg.cn/20200810105241576.png?#pic_center)
### 0.什么是微服务
```markdown
架构演进:架构的发展历程是从单体式架构，到分布式架构，到SOA架构，再到微服务架构。
    单体架构：未做任何拆分的Java Web程序
    分布式架构:按照业务垂直划分，每个业务都是单体架构，通过API互相调用。
    SOA架构：SOA是一种面向服务的架构。其应用程序的不同组件通过网络上的通信协议向其它组件提供服务或消费服务，所以也是分布式架构的一种。
微服务架构是一种架构模式或者说是一种架构风格，它提倡将单一应用程序划分成一组小的服务，每个服务运行独立的自己的进程中，服务之间互相协调、互相配合，为用户提供最终价值。
    服务之间采用轻量级的通信机制互相沟通（通常是基于HTTP的RESTful API)。每个服务都围绕着具体业务进行构建，并且能够被独立地部署到生产环境、类生产环境等。
目前最流行的两种微服务解决方案是SpringCloud和Dubbo。
微服务之间如何通信？
    同步通信：dobbo通过 RPC 远程过程调用、springcloud通过 REST  接口json调用 等。
    异步：消息队列，如：RabbitMq、ActiveM、Kafka 等。
```
### 1.什么是SpringCloud
![](https://img-blog.csdnimg.cn/20200810112743519.png?#pic_center)
```markdown
Spring Cloud作为Java的微服务框架，它依赖于SpringBoot，有快速开发、持续交付和容易部署等特点。
    SpringCloud是微服务系统架构的一站式解决方案，在平时我们构建微服务的过程中需要做如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等操作，
    SpringCloud为最常见的分布式系统模式提供了一种简单且易于接受的编程模型，帮助开发人员构建有弹性的、可靠的、协调的应用程序。
SpringCloud的版本
    最早的Release版本Angel，第二个Release版本Brixton（英国地名），然后是Camden、Dalston、Edgware、Finchley、Greenwich、Hoxton。
Spring Cloud Config
集中配置管理工具，分布式系统中统一的外部配置管理，默认使用Git来存储配置，可以支持客户端配置的刷新及加密、解密操作。
Spring Cloud Netflix
    Netflix OSS 开源组件集成，包括Eureka、Hystrix、Ribbon、Feign、Zuul等核心组件。
    Eureka：服务治理组件，包括服务端的注册中心和客户端的服务发现机制；
    Ribbon：负载均衡的服务调用组件，具有多种负载均衡调用策略；
    Hystrix：服务容错组件，实现了断路器模式，为依赖服务的出错和延迟提供了容错能力；
    Feign：基于Ribbon和Hystrix的声明式服务调用组件；
    Zuul：API网关组件，对请求提供路由及过滤功能。
Spring Cloud Bus
    用于传播集群状态变化的消息总线，使用轻量级消息代理链接分布式系统中的节点，可以用来动态刷新集群中的服务配置。
Spring Cloud Consul
    基于Hashicorp Consul的服务治理组件。
Spring Cloud Security
    安全工具包，对Zuul代理中的负载均衡OAuth2客户端及登录认证进行支持。
Spring Cloud Sleuth
    Spring Cloud应用程序的分布式请求链路跟踪，支持使用Zipkin、HTrace和基于日志（例如ELK）的跟踪。
Spring Cloud Stream
    轻量级事件驱动微服务框架，可以使用简单的声明式模型来发送及接收消息，主要实现为Apache Kafka及RabbitMQ。
Spring Cloud Task
    用于快速构建短暂、有限数据处理任务的微服务框架，用于向应用中添加功能性和非功能性的特性。
Spring Cloud Zookeeper
    基于Apache Zookeeper的服务治理组件。
Spring Cloud Gateway
    API网关组件，对请求提供路由及过滤功能。
Spring Cloud OpenFeign
    基于Ribbon和Hystrix的声明式服务调用组件，可以动态创建基于Spring MVC注解的接口实现用于服务调用，在Spring Cloud 2.0中已经取代Feign成为了一等公民。
```
### Spring Cloud Eureka 核心服务治理的组件（服务注册与发现）
[SpringBoot+Eureka注册中心+Feign进行微服务之间调用](https://blog.csdn.net/weixin_43928997/article/details/90668007?utm_source=app)
>> Eureka是Netflix开源的一款提供服务注册和发现的产品，Eureka就是一个服务中心，将所有的可以提供的服务都注册到它这里来管理，
>> 其它各调用者需要的时候去注册中心获取，然后再进行调用，避免了服务之间的直接调用，方便后续的水平扩展、故障转移等。
![](https://img-blog.csdnimg.cn/20200810120101878.png?#pic_center)
 ```markdown
 Netflix Eureka是由Netflix开源的一款基于REST的服务发现组件，包括Eureka Server及Eureka Client。
     在Netflix，更复杂的负载均衡器将Eureka包装起来，以基于流量，资源使用，错误条件等多种因素提供加权负载均衡，以提供出色的弹性。
 服务注册Register：当Eureka客户端向Eureka Server注册时，它提供自身的元数据，比如IP地址、端口，运行状况指示符URL，主页等。
 服务续约Renew：Eureka 客户会每隔30秒(默认情况下)发送一次心跳来续约。通过续约来告知Eureka Server该Eureka客户仍然存在，没有出现问题。
     正常情况下，如果Eureka Server在90秒没有收到Eureka客户的续约，它会将实例从其注册表中删除。
 获取注册列表信息Fetch Registries：Eureka客户端从服务器获取注册表信息，并将其缓存在本地。客户端会使用该信息查找其他服务，从而进行远程调用。
     该注册列表信息定期（每30秒钟）更新一次。每次返回注册列表信息可能与Eureka客户端的缓存信息不同,Eureka客户端自动处理。
     如果由于某种原因导致注册列表信息不能及时匹配，Eureka客户端则会重新获取整个注册表信息。
     Eureka服务器缓存注册列表信息，整个注册表以及每个应用程序的信息进行了压缩，压缩内容和没有压缩的内容完全相同。
     Eureka客户端和Eureka服务器可以使用JSON/XML格式进行通讯。在默认的情况下Eureka客户端使用压缩JSON格式来获取注册列表的信息。
 服务下线Cancel：Eureka客户端在程序关闭时向Eureka服务器发送取消请求。发送请求后，该客户端实例信息将从服务器的实例注册表中删除。
     该下线请求不会自动完成，它需要调用以下内容：DiscoveryManager.getInstance().shutdownComponent();
 服务剔除Eviction：在默认的情况下，当Eureka客户端连续90秒(3个续约周期)没有向Eureka服务器发送服务续约，即心跳，Eureka服务器会将该服务实例从服务注册列表删除，即服务剔除。
 ```
### Spring Cloud Config 分布式系统的配置管理方案
>> 包含了Client和Server两个部分，其实就是Server端将所有的配置文件服务化，需要配置文件的服务实例去Config Server获取对应的数据。
>> 将所有的配置文件统一整理，避免了配置文件碎片化。如果服务运行期间改变配置文件，服务是不会得到最新的配置信息，需要解决这个问题就需要引入Refresh。
>> 可以在服务的运行期间重新加载配置文件，当所有的配置文件都存储在配置中心的时候，配置中心就成为了一个非常重要的组件。
[spring-cloud-config 多服务共享公共配置的解决方案](https://www.cnblogs.com/werewolfBoy/p/13973036.html)
![](https://img-blog.csdnimg.cn/20200810214123442.png?#pic_center)
```markdown
Spring Cloud中提供了分布式配置中Spring Cloud Config，为外部配置提供了客户端和服务器端的支持。
使用Config 服务器，可以在中心位置管理所有环境中应用程序的外部属性。
```
### Hystrix 故障隔离和熔断
>> Hystrix会在某个服务连续调用N次不响应的情况下，立即通知调用端调用失败，避免调用端持续等待而影响了整体服务。
>> Hystrix间隔时间会再次检查此服务，如果服务恢复将继续提供服务。
>> 熔断的监控现在有两款工具：Hystrix-dashboard和Turbine。
>> Hystrix是Netstflix公司开源的一个项目，它提供了熔断器功能，能够阻止分布式系统中出现联动故障。
![](https://img-blog.csdnimg.cn/20200810205126528.png?#pic_center)
```markdown
Hystrix是一个库，可通过添加等待时间容限和容错逻辑来帮助您控制这些分布式服务之间的交互。
Hystrix通过隔离服务之间的访问点，停止服务之间的级联故障并提供后备选项来实现此目的，所有这些都可以提高系统的整体弹性。
```
### Spring Cloud Bus消息总线
>> Spring Cloud Bus通过轻量消息代理连接各个分布的节点。这会用在广播状态的变化（例如配置变化）或者其它的消息指令中。
>> Spring Cloud Bus的一个核心思想是通过分布式的启动器对Spring Boot应用进行扩展，也可以用来建立一个或多个应用之间的通信频道。
>> 目前唯一实现的方式是用AMQP消息代理作为通道。
![](https://img-blog.csdnimg.cn/20200810223142844.png?0#pic_center)
```markdown
Spring Cloud Bus的作用就是管理和广播分布式系统中的消息 ，也就是消息引擎系统中的广播模式。
当然作为消息总线的Spring Cloud Bus可以做很多事而不仅仅是客户端的配置刷新功能。
```
### Zuul服务网关
>> 通常会引入API Gateway作为轻量级网关，同时API Gateway中也会实现相关的认证逻辑从而简化内部服务之间相互调用的复杂度。 
>> Spring Cloud体系中支持API Gateway落地的技术就是Zuul。Spring Cloud Zuul路由是微服务架构中不可或缺的一部分，提供动态路由，监控，弹性，安全等的边缘服务。
>> Zuul是Netflix出品的一个基于JVM路由和服务端的负载均衡器。它的具体作用就是服务转发，接收并转发所有内外部的客户端调用。
>> 使用Zuul可以作为资源的统一访问入口，同时也可以在网关做一些权限校验等类似的功能。
>> Zuul是由Netflix孵化的一个致力于“网关“解决方案的开源组件。
![](https://img-blog.csdnimg.cn/20200810211514805.png?#pic_center)
```markdown
ZUUL是从设备和web站点到Netflix流应用后端的所有请求的前门。作为边界服务应用，
ZUUL是为了实现动态路由、监视、弹性和安全性而构建的。它还具有根据情况将请求路由到多个Amazon Auto Scaling Groups（亚马逊自动缩放组，亚马逊的一种云计算方式）的能力。
    网关是系统唯一对外的入口，介于客户端与服务器端之间，用于对请求进行鉴权、限流、路由、监控等功能。
    网关相当于一个网络服务架构的入口，所有网络请求必须通过网关转发到具体的服务。
    网关的作用:统一管理微服务请求，权限控制、负载均衡、路由转发、监控、安全控制黑名单和白名单等。
Zuul是对SpringCloud提供的成熟对的路由方案，他会根据请求的路径不同，网关会定位到指定的微服务，并代理请求到不同的微服务接口，他对外隐蔽了微服务的真正接口地址。
三个重要概念：动态路由表，路由定位，反向代理：
    动态路由表：Zuul支持Eureka路由，手动配置路由，这俩种都支持自动更新
    路由定位：根据请求路径，Zuul有自己的一套定位服务规则以及路由表达式匹配
    反向代理：客户端请求到路由网关，网关受理之后，在对目标发送请求，拿到响应之后在 给客户端
它可以和Eureka,Ribbon,Hystrix等组件配合使用，
Zuul的应用场景：
    对外暴露，权限校验，服务聚合，日志审计等
```
### 链路跟踪 Spring Cloud Sleuth和Zipkin
>> Spring Cloud Sleuth为服务之间调用提供链路追踪。通过Sleuth可以很清楚的了解到一个服务请求经过了哪些服务，每个服务处理花费了多长时间。从而让我们可以很方便的理清各微服务间的调用关系。
>> 分布式链路跟踪需要Sleuth+Zipkin结合来实现，当然实现链路追踪的还有三方开源方案，如果zipkin实现的功能非常简单，图形化能力也不强，所以可以试试其它的方案，如pinpoint较成熟的框架等。
>> Spring Cloud Sleuth是Spring Cloud个组件，它的主要功能是在分布式系统中提供服务链路追踪的解决方案。
![](https://img-blog.csdnimg.cn/20200811212137615.png?#pic_center)
### Feign 声明式远程调度组件
[微服务通信之feign的注册、发现过程](https://www.cnblogs.com/enjoyall/p/13755252.html)
>> Feign是一个声明式的Web Service客户端。
![](https://img-blog.csdnimg.cn/20200810182853299.png?0#pic_center)
```markdown
OpenFeign也是运行在消费者端的，使用Ribbon进行负载均衡，所以OpenFeign直接内置了Ribbon。
// 使用 @FeignClient 注解来指定提供者的名字
@FeignClient(value = "eureka-client-provider")
public interface TestClient {
    // 这里一定要注意需要使用的是提供者那端的请求相对路径，这里就相当于映射了
    @RequestMapping(value = "/provider/xxx",
    method = RequestMethod.POST)
    CommonResponse<List<Plan>> getPlans(@RequestBody planGetRequest request);
}
@RestController
public class TestController {
    // 这里就相当于原来自动注入的 Service
    @Autowired
    private TestClient testClient;
    // controller 调用 service 层代码
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public CommonResponse<List<Plan>> get(@RequestBody planGetRequest request) {
        return testClient.getPlans(request);
    }
}
```
### Ribbon 负载均衡组件
>> Ribbon Netflix 公司开源的一个负载均衡的组件。
![](https://img-blog.csdnimg.cn/20200810174308572.png?#pic_center)
#### RestTemplate
```markdown
RestTemplate是Spring提供的一个访问Http服务的客户端类 
```
#### Ribbon的几种负载均衡算法
```markdown
Ribbon是Netflix公司的一个开源的负载均衡 项目，是一个客户端/进程内负载均衡器，运行在消费者端 。
RoundRobinRule ：轮询策略。Ribbon默认采用的策略。若经过一轮轮询没有找到可用的provider，其最多轮询10轮。若最终还没有找到，则返回null。
RandomRule : 随机策略，从所有可用的provider中随机选择一个。
RetryRule : 重试策略。先按照RoundRobinRule策略获取provider，若获取失败，则在指定的时限内重试。默认的时限为500毫秒。
配置文件配置：
    providerName:
      ribbon:
        NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
```
### Spring Cloud Data Flow 
>> 大数据操作组件，它是Spring XD的替代品，也是一个混合计算模型，可以通过命令行的方式操作数据流
### Spring Cloud Task
>> 组件基于Spring Tsak，提供任务调度和任务管理的功能。
### 9.Gateway
![](https://img-blog.csdnimg.cn/20200810212808923.png?#pic_center)
```markdown
Spring Cloud Gateway是Spring官方基于Spring5.0、Spring Boot2.0 和Project Reactor等技术开发的网关，
Spring Cloud Gateway旨在为微服务架构提供简单、 有效且统一的API路由管理方式。
```
### 10.OAuth2
>> Sprin Cloud构建的微服务系统中可以使用Spring Cloud OAuth2来保护微服务系统。
![](https://img-blog.csdnimg.cn/20200811205313656.png?#pic_center)
```markdown

```
### SpringBoot和SpringCloud的区别？
```markdown
SpringBoot专注于快速方便的开发单个个体微服务。
SpringCloud是关注全局的微服务协调整理治理框架，它将SpringBoot开发的一个个单体微服务整合并管理起来，
    为各个微服务之间提供，配置管理、服务发现、断路器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等集成服务
SpringBoot可以离开SpringCloud独立使用开发项目， 但是SpringCloud离不开SpringBoot ，属于依赖的关系
SpringBoot专注于快速、方便的开发单个微服务个体，SpringCloud关注全局的服务治理框架。
```
### 使用Spring Boot开发分布式微服务时，我们面临以下问题
```markdown
（1）与分布式系统相关的复杂性-这种开销包括网络问题，延迟开销，带宽问题，安全问题。
（2）服务发现-服务发现工具管理群集中的流程和服务如何查找和互相交谈。它涉及一个服务目录，在该目录中注册服务，然后能够查找并连接到该目录中的服务。
（3）冗余-分布式系统中的冗余问题。
（4）负载平衡 --负载平衡改善跨多个计算资源的工作负荷，诸如计算机，计算机集群，网络链路，中央处理单元，或磁盘驱动器的分布。
（5）性能-问题 由于各种运营开销导致的性能问题。
（6）部署复杂性-Devops 技能的要求。
```
### SpringCloud的优缺点
```markdown
优点：
      1.耦合度比较低。不会影响其他模块的开发。
      2.减轻团队的成本，可以并行开发，不用关注其他人怎么开发，先关注自己的开发。
      3.配置比较简单，基本用注解就能实现，不用使用过多的配置文件。
      4.微服务跨平台的，可以用任何一种语言开发。
      5.每个微服务可以有自己的独立的数据库也有用公共的数据库。
      6.直接写后端的代码，不用关注前端怎么开发，直接写自己的后端代码即可，然后暴露接口，通过组件进行服务通信。
缺点：
      1.部署比较麻烦，给运维工程师带来一定的麻烦。
      2.针对数据的管理比麻烦，因为微服务可以每个微服务使用一个数据库。
      3.系统集成测试比较麻烦
      4.性能的监控比较麻烦。【最好开发一个大屏监控系统】
总的来说优点大过于缺点，目前看来SpringCloud是一套非常完善的分布式框架，目前很多企业开始用微服务、SpringCloud的优势是显而易见的。
因此对于想研究微服务架构的同学来说，学习SpringCloud是一个不错的选择。
```
### SpringCloud和Dubbo区别?
```markdown
（1）服务调用方式：dubbo是RPC springcloud Restful Api
（2）注册中心：dubbo是zookeeper springcloud是eureka，也可以是zookeeper
（3）服务网关，dubbo本身没有实现，只能通过其他第三方技术整合，springcloud有Zuul路由网关，作为路由服务器，进行消费者的请求分发,springcloud支持断路器，
    与git完美集成配置文件支持版本控制，事物总线实现配置文件的更新与服务自动装配等等一系列的微服务架构要素。
```
### Eureka和ZooKeeper都可以提供服务注册与发现的功能,请说说两个的区别
```markdown
1.ZooKeeper中的节点服务挂了就要选举
    在选举期间注册服务瘫痪,虽然服务最终会恢复,但是选举期间不可用的，
    选举就是改微服务做了集群，必须有一台主其他的都是从
2.Eureka各个节点是平等关系,服务器挂了没关系，只要有一台Eureka就可以保证服务可用，数据都是最新的。
    如果查询到的数据并不是最新的，就是因为Eureka的自我保护模式导致的
3.Eureka本质上是一个工程,而ZooKeeper只是一个进程
4.Eureka可以很好的应对因网络故障导致部分节点失去联系的情况,而不会像ZooKeeper 一样使得整个注册系统瘫痪
5.ZooKeeper保证的是CP，Eureka保证的是AP
CAP：
    C：一致性>Consistency;
        取舍：(强一致性、单调一致性、会话一致性、最终一致性、弱一致性)
    A：可用性>Availability;
    P：分区容错性>Partition tolerance;
```
### gRPC-微服务间通信实践
[gRPC-微服务间通信实践](https://www.cnblogs.com/jiangyihz/p/13754406.html)
### RPC调用和HTTP调用的区别
[RPC调用和HTTP调用的区别](https://blog.csdn.net/m0_38110132/article/details/81481454)
```markdown
最本质的区别:就是RPC主要是基于TCP/IP协议的，而HTTP服务主要是基于HTTP协议的，HTTP协议是在传输层协议TCP之上的，所以效率来看RPC要更胜一筹啦！
RPC服务：从三个角度来介绍RPC服务：分别是RPC架构，同步异步调用以及流行的RPC框架。
    RPC架构
        客户端（Client），服务的调用方。
        服务端（Server），真正的服务提供者。
        客户端存根，存放服务端的地址消息，再将客户端的请求参数打包成网络消息，然后通过网络远程发送给服务方。
        服务端存根，接收客户端发送过来的消息，将消息解包，并调用本地的方法。
    同步调用与异步调用
        同步调用就是客户端等待调用执行完成并返回结果。
        异步调用就是客户端不等待调用执行完成返回结果，不过依然可以通过回调函数等接收到返回结果的通知。
    流行的RPC框架
        gRPC是Google最近公布的开源软件，基于最新的HTTP2.0协议，并支持常见的众多编程语言。
        Thrift是Facebook的一个开源项目，主要是一个跨语言的服务开发框架。
        Dubbo是阿里集团开源的一个极为出名的RPC框架，在很多互联网公司和企业应用中广泛使用。协议和序列化框架都可以插拔是及其鲜明的特色。
HTTP服务:RESTful风格的服务接口。
        优点就是简单、直接、开发方便。利用现成的http协议进行传输。
        适合对于在接口不多、系统与系统交互较少的情况下，解决信息孤岛初期常使用的一种通信手段；
```


##

