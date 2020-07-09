# SpringBoot博客代码

[TOC]


[随笔分类 - Spring Boot](https://www.cnblogs.com/niaobulashi/category/1425468.html)  
[springboot](https://www.cnblogs.com/okong/tag/springboot/)
[SpringBoot系列](https://www.cnblogs.com/huanzi-qch/category/1355280.html) 
[Spring Boot学习之旅](https://blog.csdn.net/zhaokejin521/article/details/80942545)
[当前标签：Spring Boot](https://www.cnblogs.com/lenve/tag/Spring%20Boot/)

## 一、过滤器、拦截器和监听器
### [过滤器 和 拦截器 6个区别，别再傻傻分不清了](https://www.cnblogs.com/chengxy-nds/p/13042013.html)
```markdown
1、过滤器 (Filter)
过滤器的配置比较简单，直接实现Filter接口即可，也可以通过@WebFilter注解实现对特定URL拦截，看到Filter接口中定义了三个方法。
init()：该方法在容器启动初始化过滤器时被调用，它在Filter的整个生命周期只会被调用一次。注意：这个方法必须执行成功，否则过滤器会不起作用。
doFilter()：容器中的每一次请求都会调用该方法，FilterChain用来调用下一个过滤器Filter。
destroy()：当容器销毁 过滤器实例时调用该方法，一般在方法中销毁或关闭资源，在过滤器Filter的整个生命周期也只会被调用一次。
2、拦截器 (Interceptor)
拦截器它是链式调用，一个应用中可以同时存在多个拦截器Interceptor，一个请求也可以触发多个拦截器 ，而每个拦截器的调用会依据它的声明顺序依次执行。
首先编写一个简单的拦截器处理类，请求的拦截是通过HandlerInterceptor来实现，看到HandlerInterceptor接口中也定义了三个方法。
preHandle()：这个方法将在请求处理之前进行调用。注意：如果该方法的返回值为false ，将视为当前请求结束，不仅自身的拦截器会失效，还会导致其他的拦截器也不再执行。
postHandle()：只有在 preHandle()方法返回值为true 时才会执行。会在Controller中的方法调用之后，DispatcherServlet返回渲染视图之前被调用。 
有意思的是：postHandle()方法被调用的顺序跟 preHandle()是相反的，先声明的拦截器 preHandle()方法先执行，而postHandle()方法反而会后执行。
afterCompletion()：只有在 preHandle() 方法返回值为true 时才会执行。在整个请求结束之后， DispatcherServlet渲染了对应的视图之后执行。
过滤器和拦截器均体现了AOP的编程思想，都可以实现诸如日志记录、登录鉴权等功能，但二者的不同点也是比较多的，接下来一一说明。
1、实现原理不同：过滤器是基于函数回调的，拦截器则是基于Java的反射机制（动态代理）实现的。
2、使用范围不同：过滤器Filter的使用要依赖于Tomcat等容器，导致它只能在web程序中使用。而拦截器(Interceptor)它是一个Spring组件，
    并由Spring容器管理，并不依赖Tomcat等容器，是可以单独使用的。不仅能应用在web程序中，也可以用于Application、Swing等程序中。
3、触发时机不同：过滤器Filter是在请求进入容器后，但在进入servlet之前进行预处理，请求结束是在servlet处理完以后。
    拦截器Interceptor是在请求进入servlet后，在进入Controller之前进行预处理的，Controller中渲染了对应的视图之后请求结束。
4、拦截的请求范围不同：过滤器Filter执行了两次，拦截器Interceptor只执行了一次。这是因为过滤器几乎可以对所有进入容器的请求起作用，
    而拦截器只会对Controller中请求或访问static目录下的资源请求起作用。
5、注入Bean情况不同：拦截器加载的时间点在springcontext之前，而Bean又是由spring进行管理。
6、控制执行顺序不同：过滤器用@Order注解控制执行顺序，通过@Order控制过滤器的级别，值越小级别越高越先执行。
    拦截器默认的执行顺序，就是它的注册顺序，也可以通过Order手动设置控制，值越小越先执行。
```
### [SpringBoot使用拦截器、过滤器、监听器](https://www.cnblogs.com/haixiang/p/12000685.html)
```markdown
过滤器:是处于客户端和服务器资源文件之间的一道过滤网，帮助我们过滤掉一些不符合要求的请求，通常用作 Session 校验，判断用户权限，
    如果不符合设定条件，则会被拦截到特殊的地址或者基于特殊的响应。
拦截器是动态拦截action调用的对象，然后提供了可以在action执行前后增加一些操作，也可以在action执行前停止操作，功能与过滤器类似，但是标准和实现方式不同。
    - 登录认证：在一些应用中，可能会通过拦截器来验证用户的登录状态，如果没有登录或者登录失败，就会给用户一个友好的提示或者返回登录页面，
        当然大型项目中都不采用这种方式，都是调单点登录系统接口来验证用户。
    - 记录系统日志：我们在常见应用中，通常要记录用户的请求信息，比如请求 ip，方法执行时间等，通过这些记录可以监控系统的状况，
        以便于对系统进行信息监控、信息统计、计算 PV、性能调优等。
    - 通用处理：在应用程序中可能存在所有方法都要返回的信息，这是可以利用拦截器来实现，省去每个方法冗余重复的代码实现。
监听器通常用于监听Web应用程序中对象的创建、销毁等动作的发送，同时对监听的情况作出相应的处理，最常用于统计网站的在线人数、访问量等。
    监听器大概分为以下几种：
        ServletContextListener：用来监听 ServletContext 属性的操作，比如新增、修改、删除。
        HttpSessionListener：用来监听 Web 应用种的 Session 对象，通常用于统计在线情况。
        ServletRequestListener：用来监听 Request 对象的属性操作。
```
### [SpringBoot自定义过滤器的两种方式及过滤器执行顺序](https://www.cnblogs.com/ibigboy/p/11528775.html)
```markdown
第一种 @WebFilter + @ServletComponentScan 注解
    1、首先自定义过滤器
    2、添加 @ServletComponentScan 注解
    3、多个过滤器如何指定执行顺序？
第二种 自定义配置类配置过滤器
    1、单个过滤器时
    2、多个过滤器时如何配置
    3、SpringBoot注册第三方过滤器
```

## 四、SpringBoot
### 1.SpringBoot基础知识
[随笔分类 - springBoot](https://www.cnblogs.com/xuwujing/category/1145997.html)
[springboot集合]([https://www.cnblogs.com/viyoung/tag/Spring%20Boot/](https://www.cnblogs.com/viyoung/tag/Spring Boot/))  
[随笔分类 - Spring Boot](https://www.cnblogs.com/toutou/category/1327302.html)
[当前标签：SpringBoot](https://www.cnblogs.com/yanfei1819/tag/SpringBoot/default.html?page=1)
[当前标签：SpringBoot](https://www.cnblogs.com/spec-dog/tag/SpringBoot/)
[随笔分类 - springboot入门实战](https://www.cnblogs.com/love-wzy/category/1388095.html)
[随笔分类 - 【框架】-- SpringBoot](https://www.cnblogs.com/qdhxhz/category/1169139.html)
[随笔分类 - Spring Boot](https://www.cnblogs.com/didispace/category/1392206.html)

[随笔分类 - Spring Boot](https://www.cnblogs.com/didispace/category/1392206.html)
[这一次搞懂SpringBoot核心原理（自动配置、事件驱动、Condition）](https://www.cnblogs.com/yewy/p/13194696.html)
#### 1.[SpringBoot切面Aop的demo简单讲解](https://www.cnblogs.com/xuwujing/p/12927081.html)
```markdown
AOP（Aspect OrientedProgramming）：面向切面编程，面向切面编程（也叫面向方面编程），是目前软件开发中的一个热点，也是Spring框架中的一个重要内容。
利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
* 用途
    日志记录，性能统计，安全控制，权限管理，事务处理，异常处理，资源池管理。
```
#### 2.SpringBoot启动流程
[SpringBoot启动流程分析（一）：SpringApplication类初始化过程](https://www.cnblogs.com/hello-shf/p/10976646.html)
[SpringBoot启动原理及相关流程](https://www.cnblogs.com/l3306/p/10752907.html)
[SpringApplication生命周期的事件](https://www.cnblogs.com/yourbatman/p/13257999.html)
```markdown
- new springApplication对象，利用spi机制加载applicationContextInitializer,applicationLister接口实例（META-INF/spring.factories）；
- 调run方法准备Environment，加载应用上下文（applicationContext），发布事件 很多通过lister实现
- 创建spring容器，refreshContext()，实现starter自动化配置，spring.factories文件加载，bean实例化
SpringBoot自动配置的原理
    - @EnableAutoConfiguration找到META-INF/spring.factories（需要创建的bean在里面）配置文件
    - 读取每个starter中的spring.factories文件
```
#### 3.SpringBoot常用starter都有哪些
```markdown
spring-boot-starter-web-Web和RESTful应用程序；spring-boot-starter-test-单元测试和集成测试；
spring-boot-starter-jdbc-传统的JDBC；spring-boot-starter-security-使用SpringSecurity进行身份验证和授权；
spring-boot-starter-data-jpa-带有Hibernate的Spring Data JPA；spring-boot-starter-data-rest-使用Spring Data REST公布简单的REST服务
```
#### 4.Spring Boot读取配置文件的几种方式
[Spring Boot读取配置文件的几种方式](https://www.cnblogs.com/zhixie/p/13264833.html)
```markdown
Spring Boot获取文件总的来说有三种方式，分别是@Value注解，@ConfigurationProperties注解和Environment接口。
    这三种注解可以配合着@PropertySource来使用，@PropertySource主要是用来指定具体的配置文件。
```
#### 10.SpringBoot构建RESTAPI与单元测试
[Spring Boot 2.x基础教程：构建RESTful API与单元测试](https://www.cnblogs.com/didispace/p/11606136.html)
```markdown
通过引入web模块（没有做其他的任何配置），就可以轻松利用Spring MVC的功能，以非常简洁的代码完成了对User对象的RESTful API的创建以及单元测试的编写。
其中同时介绍了Spring MVC中最为常用的几个核心注解：@RestController,RequestMapping以及一些参数绑定的注解：@PathVariable,@RequestBody等。
```
[[基于SpringBoot实现AOP+jdk/CGlib动态代理详解](https://www.cnblogs.com/godoforange/p/11587321.html)]
[如何优雅地停止 Spring Boot 应用？](https://www.cnblogs.com/wupeixuan/p/13065986.html)
[[Springboot 优雅停止服务的几种方法](https://www.cnblogs.com/huangqingshi/p/11370291.html)]
[对于springboot的几种注入方法的个人看法](https://www.cnblogs.com/XSdao/p/11208437.html)
[读写分离很难吗？springboot结合aop简单就实现了](https://www.cnblogs.com/yeya/p/11936239.html)

### 2.SpringBoot注解
[接近8000字的Spring/SpringBoot常用注解总结！安排！](https://www.cnblogs.com/javaguide/p/spring-annotations.html)
[[Spring Boot 注解大全，真是太全了！](https://www.cnblogs.com/qwlscn/p/11495378.html)]
#### 2.1. Spring Boot 的核心注解
```markdown
核心注解是@SpringBootApplication 由以下三种组成
    - @SpringBootConfiguration：组合了@Configuration注解，实现配置文件的功能。
    - @EnableAutoConfiguration：打开自动配置的功能。
    - @ComponentScan：Spring组件扫描。
```
#### 2.1.启动类：@SpringBootApplication
[Spring Boot 解决方案 - 配置](https://www.cnblogs.com/linzhehuang/p/10617116.html)
```markdown
@SpringBootApplication注释清楚地表明这是一个Spring引导应用程序。
@SpringBootApplication是一个组合了其他三个注释的复合应用程序：
    @SpringBootConfiguration——指定这个类为配置类。尽管这个类中还没有太多配置，但是如果需要，可以将Javabased Spring Framework配置添加到这个类中。
        实际上，这个注释是@Configuration注释的一种特殊形式。
    @EnableAutoConfiguration——启用Spring自动配置。现在，要知道这个注释告诉Spring Boot自动配置它认为需要的任何组件。
        若标记了@EnableAutoConfiguration注解，Spring Boot会根据ClassPath中的Jar包依赖来自动配置程序。
        由于自动配置是非侵入性的，因此可以自定义配置来覆盖原有的自动配置，而且还可以禁止某些自动配置类
        例如下面例子禁止了数据源的自动配置：@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
    @ComponentScan——启用组件扫描。这允许你声明其他带有@Component、@Controller、@Service 等注释的类，以便让Spring自动发现它们并将它们注册为Spring应用程序上下文中的组件。
main()方法调用SpringApplication类上的静态run()方法，该方法执行应用程序的实际引导，创建Spring 应用程序上下文。
传递给run()方法的两个参数是一个配置类和命令行参数。虽然传递给run()的配置类不必与引导类相同，但这是最方便、最典型的选择。
```
#### 2.10.测试启动类：@SpringBootTest和@ContextConfiguration 
```markdown
@RunWith(SpringRunner.class)：JUnit提供的@RunWith注解来指定运行环境，这里指定了SpringRunner即Spring的运行环境。
@SpringBootTest注解则用来加载所需Spring环境所需要的配置，这样就可以在测试环境中自动装载所需要的Bean。
然后使用@Autowired注入Spring中自动装载所需要的Bean。
当有些测试不需要加载整个Spring Boot环境时，可以将@SpringBootTest改用@ContextConfiguration注解只加载所需配置，这样可以加速测试，下面例子是只需要DemoConf类的配置。
当完全不需要Spring环境来测试时，还可以去掉@RunWith注解。
```
#### 2.11.Junit测试注解
[Spring Boot 解决方案 - JUnit 测试](https://www.cnblogs.com/linzhehuang/p/10603250.html)
```markdown
@BeforeClass 运行测试类前执行且只执行一次
@Before 每个测试方法执行前执行
@Test 测试方法，每个测试方法的顺序也可以指定
@After每个测试方法执行后执行
@AfterClass 运行测试类后执行且只执行一次
@FixMethodOrder(MethodSorters.DEFAULT) 测试方法也可以指定顺序，利用 @FixMethodOrder 标注测试类。
    MethodSorters 有三种取值，分别代表不同的执行顺序。
        DEFAULT 由方法名的 hashcode 值大小决定执行顺序，若值一样则根据方法名的字典顺序
        NAME_ASCENDING 由方法名的字典顺序决定执行顺序
        JVM 由 JVM 返回的方法名的顺序决定
```

### 3.SpringBoot源码
[随笔分类 - spring/boot源码解析](https://www.cnblogs.com/grey-wolf/category/1676533.html)
[SpringBoot源码专题]( [https://www.cnblogs.com/ymbj/tag/SpringBoot%E6%BA%90%E7%A0%81%E4%B8%93%E9%A2%98/](https://www.cnblogs.com/ymbj/tag/SpringBoot源码专题/) )

#### [SpringBoot启动tomcat源码解读](https://www.cnblogs.com/darendu/p/10559366.html)
```markdown
从SpringBoot启动类SpringBootApplication.class进入
1、@SpringBootApplication是SpringBoot扫描第三方依赖的重要注解，进入查看该注解的源码。
2、其中重点注解是@EnableAutoConfiguration,进入该注解。
3、其中使用@Import注解对AutoConfigurationImportSelector类进行了引入。
    AutoConfigurationImportSelector类首先调用selectImport()方法，在该方法中调用了getAutoConfigurationEntry()方法，
    在之中又调用了getCandidateConfigurations()方法，这方法就去META-INF/spring.factory配置文件中加载相关配置类。
4.spring.factories配置文件是加载的spring-boot-autoconfigure的配置文件，由此可以推测如果自定义starter插件的话，spring也是采用这种方式去加载的
5.继续打开spring.factories配置文件，找到tomcat所在的类，tomcat加载在ServletWebServerFactoryAutoConfiguration配置类中，
6.进入该类，里面也通过@Import注解将EmbeddedTomcat、EmbeddedJetty、EmbeddedUndertow等嵌入式容器类加载进来了，
    springboot默认是启动嵌入式tomcat容器，如果要改变启动jetty或者undertow容器，需在pom文件中去设置。
7.继续进入EmbeddedTomcat类中，嵌入式tomcat构造方法，实例化了一个工厂类，并交给Spring管理。
8.进入TomcatServletWebServerFactory类，里面的getWebServer()是关键方法，实例化一个tomcat对象，设置tomcat相关信息。
9.继续进入getTomcatWebServer()等方法，一直往下跟到tomcat初始化方法，调用tomcat.start()方法，tomcat就正式开启运行，
10.走到这里tomcat在springboot中的配置以及最终启动的流程就走完了。
```

### 4.SpringBoot与其他框架应用
[SpringBoot整合Shiro实现基于角色的权限访问控制(RBAC)系统简单设计从零搭建](https://www.cnblogs.com/ealenxie/p/10610741.html)
[SpringBoot集成Shiro实现动态加载权限](https://www.cnblogs.com/zhengqing/p/11603824.html)
[]
[Spring Boot项目中使用Mockito](https://www.cnblogs.com/javaadu/p/11748415.html)

[SpringBoot2.0+Nacos+Sentinel流控规则集中存储](https://www.cnblogs.com/smallSevens/p/11553695.html)
[[SpringBoot2.0+阿里巴巴Sentinel动态限流实战](https://www.cnblogs.com/smallSevens/p/11531534.html)]				
[[SpringBoot2.0+InfluxDB+Sentinel实时监控数据存储](https://www.cnblogs.com/smallSevens/p/11576263.html)]
[SpringBoot开发案例Nacos配置管理中心](https://www.cnblogs.com/smallSevens/p/11223830.html)
[SpringBoot+Eureka注册中心+Feign进行微服务之间调用](https://blog.csdn.net/weixin_43928997/article/details/90668007?utm_source=app)


[[springboot数据库主从方案](https://www.cnblogs.com/wangrudong003/p/11535540.html)]

[SpringBoot中使用RSocket](https://juejin.im/post/5cf733a5e51d4510835e0260)

[springboot超级详细的日志配置(基于logback)](https://www.cnblogs.com/wuyoucao/p/10983241.html)

[Spring Boot使用MyBatis Generator、Swagger](https://www.cnblogs.com/FireworksEasyCool/p/11133827.html)

[springboot+dubbo简单分布式RPC调用demo](https://www.cnblogs.com/kuangdw/p/12783281.html)
[Spring Boot 整合 Apache Dubbo](https://www.cnblogs.com/vandusty/p/12853973.html)
[springboot+mybatis+dubbo+aop日志第一篇](https://www.cnblogs.com/lc-chenlong/p/10648523.html)
[springboot+dubbo+zookeeper微服务实践demo](https://www.cnblogs.com/miketwais/p/10216956.html)
[SpringBoot 整合 Zookeeper 接入Starring微服务平台](https://www.cnblogs.com/laramia/p/11978271.html)
[实例讲解Springboot整合MongoDB进行CRUD操作的两种方式](https://www.cnblogs.com/larrydpk/p/12735620.html)

[基于Netty和SpringBoot实现一个轻量级RPC框架-Server篇](https://www.cnblogs.com/throwable/p/12194713.html)
[聊聊Spring Boot Actuator](https://www.cnblogs.com/dongxishaonian/p/12795744.html)
[现在Java 桌面应用程序能做到什么程度（Spring Boot+JavaFX2开发）](https://www.cnblogs.com/pengdai/p/11769194.html)
[Spring Boot 开发微信公众号后台](https://www.cnblogs.com/lenve/p/11763295.html)
### 
[【Springboot可执行jar和普通可引用jar】](https://www.cnblogs.com/lenve/p/11156340.html)
[springboot的jar为何能独立运行](https://www.cnblogs.com/bolingcavalry/p/13195961.html)
[基于SpringBoot的开源微信开发平台，Jeewx-Boot 1.0 版本发布](https://blog.csdn.net/zhangdaiscott/article/details/95994816)

[springboot利用consul实现分布式锁](https://www.cnblogs.com/wenwuxianren/p/11181786.html)
[springboot+redis分布式锁-模拟抢单](https://www.cnblogs.com/wangrudong003/p/10627539.html)

[SpringBoot2.x 整合Spring-Session实现Session共享](https://www.cnblogs.com/lanxuan826/p/11221603.html)
[[SpringBoot处理全局统一异常](https://www.cnblogs.com/lgjlife/p/10988439.html)]
[[SpringBoot:如何优雅地处理全局异常？](https://www.cnblogs.com/coderxx/p/11331855.html)]

[[【Springboot】用Prometheus+Grafana监控Springboot应用](https://www.cnblogs.com/larrydpk/p/12563497.html)]【****】
[SpringBoot集成MyBatis的分页插件PageHelper(回头草)](https://www.cnblogs.com/1315925303zxz/p/7364552.html)
[[Spring Boot接口如何设计防篡改、防重放攻击](https://www.cnblogs.com/tqlin/p/11251321.html)]	
[Springboot问题](https://www.cnblogs.com/ityouknow/p/11281643.html)
[[开发一个Spring Boot Starter!](https://www.cnblogs.com/NinWoo/p/11305650.html)]
[[基于SpringBoot从零构建博客网站 - 集成editor.md开发发布文章功能](https://www.cnblogs.com/atcloud/p/11302520.html)]
[[SpringBoot开发案例之打造十万博文Web篇](https://www.cnblogs.com/smallSevens/p/11301025.html)]
[[基于Spring Boot自建分布式基础应用](https://www.cnblogs.com/HuaiyinMarquis/p/11382145.html)]
[[Spring Boot MyBatis 数据库集群访问实现](https://www.cnblogs.com/tqlin/p/11430702.html)]
[SpringBoot+Mybatis增删改查实战](https://www.cnblogs.com/ljsh/p/10928106.html)
[SpringBoot+MyBatis中自动根据@Table注解和@Column注解生成增删改查逻辑](https://www.cnblogs.com/Leechg/p/10097777.html)
[【优雅写代码系统】springboot+mybatis+pagehelper+mybatisplus+druid教你如何优雅写代码](https://www.cnblogs.com/zhangxinhua/p/13091116.html)



[Spring boot 多模块项目 + Swagger 让你的API可视化](https://www.cnblogs.com/xpwi/p/10609104.html)
[SpringBoot整合Swagger2，再也不用维护接口文档了！](https://www.cnblogs.com/lenve/p/10619547.html)
###

##
[SpringBoot开发案例之打造私有云网盘](https://www.cnblogs.com/smallSevens/p/10712643.html)
[基于springboot搭建的web系统架构](https://www.cnblogs.com/Oven5217/p/10765052.html)
[SpringBoot中并发定时任务的实现、动态定时任务的实现（看这一篇就够了）](https://www.cnblogs.com/baixianlong/p/10659045.html)
[SpringBoot 整合 RabbitMQ（包含三种消息确认机制以及消费端限流）](https://www.cnblogs.com/haixiang/p/10959551.html)
[SpringBoot+Maven多模块项目（创建、依赖、打包可执行jar包部署测试）完整流程](https://blog.csdn.net/baidu_41885330/article/details/81875395)