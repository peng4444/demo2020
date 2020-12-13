# SpringBoot博客代码

[TOC]


[随笔分类 - Spring Boot](https://www.cnblogs.com/niaobulashi/category/1425468.html)  
[springboot](https://www.cnblogs.com/okong/tag/springboot/)
[SpringBoot系列](https://www.cnblogs.com/huanzi-qch/category/1355280.html) 
[Spring Boot学习之旅](https://blog.csdn.net/zhaokejin521/article/details/80942545)
[当前标签：Spring Boot](https://www.cnblogs.com/lenve/tag/Spring%20Boot/)
## SpringBoot
### 1.SpringBoot基础知识
[参考资料：随笔分类 - Spring Boot](https://www.cnblogs.com/toutou/category/1327302.html)
[当前标签：Spring Boot](https://www.cnblogs.com/viyoung/tag/Spring%20Boot/)
[springboot2.0实战](https://blog.csdn.net/hxnlyw/category_9631147.html)
[当前标签：SpringBoot](https://www.cnblogs.com/yanfei1819/tag/SpringBoot/default.html?page=1)
[当前标签：SpringBoot](https://www.cnblogs.com/spec-dog/tag/SpringBoot/)
[随笔分类 - springboot入门实战](https://www.cnblogs.com/love-wzy/category/1388095.html)
[随笔分类 - 【框架】-- SpringBoot](https://www.cnblogs.com/qdhxhz/category/1169139.html)
[随笔分类 - Spring Boot](https://www.cnblogs.com/didispace/category/1392206.html)

[这一次搞懂SpringBoot核心原理（自动配置、事件驱动、Condition）](https://www.cnblogs.com/yewy/p/13194696.html)
[3种 Springboot 全局时间格式化方式，别再写重复代码了](https://www.cnblogs.com/chengxy-nds/p/13600799.html)
#### 1.springBoot简介
```markdown
SpringBoot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。
    该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。
    通过这种方式，SpringBoot致力于在蓬勃发展的快速应用开发领域(rapid application development)成为领导者。
SpringBoot各个组件
    
```
#### 2.SpringBoot哪些优点【5+】
```markdown
Spring Boot 主要有如下优点：
    1.容易上手，提升开发效率，为Spring开发提供一个更快、更广泛的入门体验。
    2.开箱即用，远离繁琐的配置。
    3.提供了一系列大型项目通用的非业务性功能，例如：内嵌服务器、安全管理、运行数据监控、运行状况检查和外部化配置等。
    4.没有代码生成，也不需要XML配置。
    5.避免大量的Maven导入和各种版本冲突。
1.创建独立的Spring应用程序
2.嵌入的Tomcat，无需部署WAR文件
3.简化Maven配置
4.自动配置Spring
5.提供生产就绪型功能，如指标，健康检查和外部配置
6.绝对没有代码生成并且对XML也没有配置要求
```
#### 3.SpringBoot的核心注解【2+】
[这类注解都不知道，还好意思说会Spring Boot ？](https://www.cnblogs.com/Chenjiabing/p/13812938.html)
[接近8000字的Spring/SpringBoot常用注解总结！安排！](https://www.cnblogs.com/javaguide/p/spring-annotations.html)
[Spring Boot 注解大全，真是太全了！](https://www.cnblogs.com/qwlscn/p/11495378.html)
[Spring Boot常用注解（绝对经典）](https://blog.csdn.net/guorui_java/article/details/107379648)
```markdown
启动类上面的注解是@SpringBootApplication，它也是SpringBoot的核心注解，主要组合包含了以下3个注解：
    @SpringBootConfiguration：组合了@Configuration注解，实现配置文件的功能。
    @EnableAutoConfiguration：打开自动配置的功能，也可以关闭某个自动配置的选项，
        如关闭数据源自动配置功能：@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})。
    @ComponentScan：Spring组件扫描。
核心注解是@SpringBootApplication 由以下三种组成
    - @SpringBootConfiguration：组合了@Configuration注解，实现配置文件的功能。
    - @EnableAutoConfiguration：打开自动配置的功能。
    - @ComponentScan：Spring组件扫描。
```
#### 4.SpringBoot实现自定义注解
[SpringBoot如何整合AOP自定义一个注解](https://www.cnblogs.com/Chenjiabing/p/13984651.html)
#### 5启动类注解：@SpringBootApplication
[Spring Boot 解决方案 - 配置](https://www.cnblogs.com/linzhehuang/p/10617116.html)
[Spring Boot 自动配置源码解析](https://www.cnblogs.com/Chenjiabing/p/14010257.html)
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
#### 6.什么是JavaConfig？
```markdown
Spring JavaConfig是Spring社区的产品，它提供了配置SpringIoC容器的纯Java方法。因此它有助于避免使用XML配置。使用JavaConfig的优点在于：
（1）面向对象的配置。由于配置被定义为JavaConfig中的类，因此用户可以充分利用Java中的面向对象功能。一个配置类可以继承另一个，重写它的@Bean方法等。
（2）减少或消除XML配置。基于依赖注入原则的外化配置的好处已被证明。但是，许多开发人员不希望在XML和Java之间来回切换。
    JavaConfig为开发人员提供了一种纯Java方法来配置与XML配置概念相似的Spring容器。
    从技术角度来讲，只使用JavaConfig配置类来配置容器是可行的，但实际上很多人认为将JavaConfig与XML混合匹配是理想的。
（3）类型安全和重构友好。JavaConfig提供了一种类型安全的方法来配置Spring容器。
    由于Java 5.0对泛型的支持，现在可以按类型而不是按名称检索bean，不需要任何强制转换或基于字符串的查找。
```
#### 7.SpringBoot核心配置文件，配置文件有几种格式
```markdown
bootstrap(.yml或者.properties):bootstrap由父ApplicationContext加载的比application邮箱加载，且bootstrap里面的属性不能被覆盖。
application(.yml或者.properties):用于SpringBoot项目的自动化配置
```
#### 8.SpringBoot的事务Transaction
[SpringBoot的事务Transaction使用的教程](https://www.cnblogs.com/xuwujing/p/11184162.html)
#### 1.[SpringBoot切面Aop的demo简单讲解](https://www.cnblogs.com/xuwujing/p/12927081.html)
```markdown
AOP（Aspect OrientedProgramming）：面向切面编程，面向切面编程（也叫面向方面编程），是目前软件开发中的一个热点，也是Spring框架中的一个重要内容。
利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
* 用途
    日志记录，性能统计，安全控制，权限管理，事务处理，异常处理，资源池管理。
```
#### 2.SpringBoot启动流程【10+】
[SpringBoot启动流程分析（一）：SpringApplication类初始化过程](https://www.cnblogs.com/hello-shf/p/10976646.html)
[SpringBoot启动原理及相关流程](https://www.cnblogs.com/l3306/p/10752907.html)
[SpringApplication生命周期的事件](https://www.cnblogs.com/yourbatman/p/13257999.html)
[SpringBoot启动流程总结](https://blog.csdn.net/mnicsm/article/details/93893669)
```markdown
- new springApplication对象，利用spi机制加载applicationContextInitializer,applicationLister接口实例（META-INF/spring.factories）；
- 调run方法准备Environment，加载应用上下文（applicationContext），发布事件 很多通过lister实现
- 创建spring容器，refreshContext()，实现starter自动化配置，spring.factories文件加载，bean实例化
SpringBoot自动配置的原理
    - @EnableAutoConfiguration找到META-INF/spring.factories（需要创建的bean在里面）配置文件
    - 读取每个starter中的spring.factories文件
@SpringBootApplication
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
1、new了一个SpringApplication对象，使用SPI技术加载加载 ApplicationContextInitializer、ApplicationListener接口实例
2、调用SpringApplication.run()方法
3、调用createApplicationContext()方法创建上下文对象，创建上下文对象同时会注册spring的核心组件类（ConfigurationClassPostProcessor 、AutowiredAnnotationBeanPostProcessor等）。
4、调用refreshContext()方法启动Spring容器和内置的Servlet容器
加载了@SpringBootApplication注解主配置类，这个@SpringBootApplication注解主配置类里边最主要的功能就是SpringBoot开启了一个@EnableAutoConfiguration注解的自动配置功能。
    @EnableAutoConfiguration作用：
    它主要利用了一个EnableAutoConfigurationImportSelector选择器给Spring容器中来导入一些组件。
        @Import(EnableAutoConfigurationImportSelector.class)
        public @interface EnableAutoConfiguration 
@SpringBootApplication注解等同于下面三个注解：
    @SpringBootConfiguration： 底层是Configuration注解，说白了就是支持JavaConfig的方式来进行配置
    @EnableAutoConfiguration：开启自动配置功能
    @ComponentScan：就是扫描注解，默认是扫描当前类下的package
其中@EnableAutoConfiguration是关键(启用自动配置)，内部实际上就去加载META-INF/spring.factories文件的信息，然后筛选出以EnableAutoConfiguration为key的数据，加载到IOC容器中，实现自动配置功能！
```
#### 3.SpringBoot常用starter都有哪些
```markdown
spring-boot-starter-web-Web和RESTful应用程序；spring-boot-starter-test-单元测试和集成测试；
spring-boot-starter-jdbc-传统的JDBC；spring-boot-starter-security-使用SpringSecurity进行身份验证和授权；
spring-boot-starter-data-jpa-带有Hibernate的Spring Data JPA；spring-boot-starter-data-rest-使用Spring Data REST公布简单的REST服务
```
#### 4.Spring Boot读取配置文件的几种方式
[Spring Boot读取配置文件的几种方式](https://www.cnblogs.com/zhixie/p/13264833.html)
[Spring Boot 配置特性解析](https://www.cnblogs.com/jiagoujishu/p/13728828.html)
```markdown
Spring Boot获取文件总的来说有三种方式，分别是@Value注解，@ConfigurationProperties注解和Environment接口。
    这三种注解可以配合着@PropertySource来使用，@PropertySource主要是用来指定具体的配置文件。
```
#### 5.SpringBoot Controller接收参数的几种方式
[SpringBoot Controller接收参数的几种方式](https://www.cnblogs.com/zhixie/p/13762519.html)
```markdown

```
#### 6.SpringBoot自动配置的原理是什么？介绍SpringBootApplication注解.
```markdown
启动类：
@SpringBootApplication
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
它主要加载了@SpringBootApplication注解主配置类，这个@SpringBootApplication注解主配置类里边
    最主要的功能就是SpringBoot开启了一个@EnableAutoConfiguration注解的自动配置功能。
@EnableAutoConfiguration作用：
它主要利用了一个EnableAutoConfigurationImportSelector选择器给Spring容器中来导入一些组件。
    @Import(EnableAutoConfigurationImportSelector.class)
    public @interface EnableAutoConfiguration
@SpringBootApplication注解等同于下面三个注解：
    @SpringBootConfiguration： 底层是Configuration注解，说白了就是支持JavaConfig的方式来进行配置
    @EnableAutoConfiguration：开启自动配置功能
    @ComponentScan：就是扫描注解，默认是扫描当前类下的package
    其中@EnableAutoConfiguration是关键(启用自动配置)，内部实际上就去加载META-INF/spring.factories文件的信息，
    然后筛选出以EnableAutoConfiguration为key的数据，加载到IOC容器中，实现自动配置功能！
注解@EnableAutoConfiguration,@Configuration,@ConditionalOnClass就是自动配置的核心，
    @EnableAutoConfiguration给容器导入META-INF/spring.factories里定义的自动配置类。
    筛选有效的自动配置类。
    每一个自动配置类结合对应的xxxProperties.java读取配置文件进行自动配置功能
```
#### 7.spring-boot全局异常处理
[SpringBoot优雅的全局异常处理](https://www.cnblogs.com/xuwujing/p/10933082.html)
[spring-boot-route（四）全局异常处理](https://www.cnblogs.com/zhixie/p/13768583.html)
[SpringBoot处理全局统一异常](https://www.cnblogs.com/lgjlife/p/10988439.html)
[SpringBoot:如何优雅地处理全局异常](https://www.cnblogs.com/coderxx/p/11331855.html)
```markdown
SpringBoot提供的的注解@ControllerAdvice表示开启全局异常捕获，在自定义的异常方法上使用ExceptionHandler来进行统一处理。
```
#### 9.测试启动类：@SpringBootTest和@ContextConfiguration 
[Spring Boot项目中使用Mockito](https://www.cnblogs.com/javaadu/p/11748415.html)
[给你项目加个Mock吧](https://www.cnblogs.com/renlywen/p/13549380.html)
```markdown
@RunWith(SpringRunner.class)：JUnit提供的@RunWith注解来指定运行环境，这里指定了SpringRunner即Spring的运行环境。
@SpringBootTest注解则用来加载所需Spring环境所需要的配置，这样就可以在测试环境中自动装载所需要的Bean。
然后使用@Autowired注入Spring中自动装载所需要的Bean。
当有些测试不需要加载整个Spring Boot环境时，可以将@SpringBootTest改用@ContextConfiguration注解只加载所需配置，这样可以加速测试，下面例子是只需要DemoConf类的配置。
当完全不需要Spring环境来测试时，还可以去掉@RunWith注解。
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
#### 11.Junit测试注解
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
#### 12.SpringBoot项目打包
[SpringBoot+Maven多模块项目（创建、依赖、打包可执行jar包部署测试）完整流程](https://blog.csdn.net/baidu_41885330/article/details/81875395)
[【Springboot可执行jar和普通可引用jar】](https://www.cnblogs.com/lenve/p/11156340.html)
[springboot的jar为何能独立运行](https://www.cnblogs.com/bolingcavalry/p/13195961.html)
[Spring Boot 项目瘦身指南](https://www.cnblogs.com/niceyoo/p/14083706.html)
```markdown

```
#### 13.Spring Boot Starter
[开发一个Spring Boot Starter!](https://www.cnblogs.com/NinWoo/p/11305650.html)
#### SpringBoot整合日志框架
[springboot超级详细的日志配置(基于logback)](https://www.cnblogs.com/wuyoucao/p/10983241.html)
#### SpringBoot多环境配置
[Spring Boot 多环境配置](https://www.cnblogs.com/Chenjiabing/p/13968781.html)
#### SpringBoot新版本
[Spring Boot 2.3.0正式发布：优雅停机、配置文件位置通配符新特性一览](https://www.cnblogs.com/yourbatman/p/13294338.html)
[Spring Boot 2.4.0正式发布：全新的配置文件加载机制（不向下兼容）](https://www.cnblogs.com/yourbatman/p/14061177.html)

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
#### SpringBoo整合MyBatis
[Spring Boot MyBatis 数据库集群访问实现](https://www.cnblogs.com/tqlin/p/11430702.html)
[SpringBoot+Mybatis增删改查实战](https://www.cnblogs.com/ljsh/p/10928106.html)
[SpringBoot+MyBatis中自动根据@Table注解和@Column注解生成增删改查逻辑](https://www.cnblogs.com/Leechg/p/10097777.html)
[【优雅写代码系统】springboot+mybatis+pagehelper+mybatisplus+druid教你如何优雅写代码](https://www.cnblogs.com/zhangxinhua/p/13091116.html)
[SpringBoot集成MyBatis的分页插件PageHelper(回头草)](https://www.cnblogs.com/1315925303zxz/p/7364552.html)
[springboot数据库主从方案](https://www.cnblogs.com/wangrudong003/p/11535540.html)
#### SpringBoot整合Shiro
[SpringBoot整合Shiro实现基于角色的权限访问控制(RBAC)系统简单设计从零搭建](https://www.cnblogs.com/ealenxie/p/10610741.html)
[SpringBoot集成Shiro实现动态加载权限](https://www.cnblogs.com/zhengqing/p/11603824.html)
[Spring Boot2(十二)：手摸手教你搭建Shiro安全框架](https://www.cnblogs.com/niaobulashi/p/springboot-shiro.html)
[Spring Boot2(十五)：Shiro记住我rememberMe、验证码Kaptcha](https://www.cnblogs.com/niaobulashi/p/shiro-rememberme-kaptcha.html)

#### SpringBoot整合Swagger2
[Springboot多模块项目+Swagger让你的API可视化](https://www.cnblogs.com/xpwi/p/10609104.html)
[SpringBoot整合Swagger2，再也不用维护接口文档了！](https://www.cnblogs.com/lenve/p/10619547.html)
[Spring Boot使用MyBatis Generator、Swagger](https://www.cnblogs.com/FireworksEasyCool/p/11133827.html)

#### SpringBoot整合定时任务
[Spring Boot2(十三)：整合定时任务发送邮件](https://www.cnblogs.com/niaobulashi/p/schedule-mail.html)
[SpringBoot中并发定时任务的实现、动态定时任务的实现（看这一篇就够了）](https://www.cnblogs.com/baixianlong/p/10659045.html)
[SpringBoot系列：Spring Boot定时任务Spring Schedule](https://www.cnblogs.com/imyanger/p/11826227.html)
[SpringBoot系列：Spring Boot集成定时任务Quartz](https://www.cnblogs.com/imyanger/p/11828301.html)
[定时任务最简单的3种实现方法（超好用）](https://mp.weixin.qq.com/s?__biz=MzU1NTkwODE4Mw==&mid=2247487964&idx=2&sn=dbcd21c4d57a62b8e2cf2aba18acb761&chksm=fbcc7ce4ccbbf5f2500afa479ed59650792b4f2b2ebbb9e9d8bb397de6dca64c7f2617e23625&mpshare=1&scene=23&srcid=0818JKb8atI4owQubDRTO08P&sharer_sharetime=1597712340498&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
[贼好用，冰河开源了这款精准定时任务和延时队列框架！！](https://www.cnblogs.com/binghe001/p/14028079.html)

#### SpringBoot文件上传下载
[Spring Boot2(十四)：单文件上传/下载，文件批量上传](https://www.cnblogs.com/niaobulashi/p/springboot-updownload.html)


#### SpringBoot整合本地缓存Guava cache
[Guava cache使用总结](https://www.cnblogs.com/rickiyang/p/11074159.html)
[Caffeine Cache-高性能Java本地缓存组件](https://www.cnblogs.com/rickiyang/p/11074158.html)


#### SpringBoot整合Dubbo
[springboot+dubbo简单分布式RPC调用demo](https://www.cnblogs.com/kuangdw/p/12783281.html)
[Spring Boot 整合 Apache Dubbo](https://www.cnblogs.com/vandusty/p/12853973.html)
[springboot+mybatis+dubbo+aop日志第一篇](https://www.cnblogs.com/lc-chenlong/p/10648523.html)
[springboot+dubbo+zookeeper微服务实践demo](https://www.cnblogs.com/miketwais/p/10216956.html)

#### SpringBoot
[聊聊Spring Boot Actuator](https://www.cnblogs.com/dongxishaonian/p/12795744.html)
[现在Java 桌面应用程序能做到什么程度（Spring Boot+JavaFX2开发）](https://www.cnblogs.com/pengdai/p/11769194.html)

#### SpringBoot微信开发 
[SpringBoot2.x 整合Spring-Session实现Session共享](https://www.cnblogs.com/lanxuan826/p/11221603.html)
[用Prometheus+Grafana监控Springboot应用](https://www.cnblogs.com/larrydpk/p/12563497.html)



[基于SpringBoot从零构建博客网站集成editor.md开发发布文章功能](https://www.cnblogs.com/atcloud/p/11302520.html)
[SpringBoot开发案例之打造十万博文Web篇](https://www.cnblogs.com/smallSevens/p/11301025.html)
[基于Spring Boot自建分布式基础应用](https://www.cnblogs.com/HuaiyinMarquis/p/11382145.html)



### SpringBoot实战
[SpringBoot整合Mail发送邮件&发送模板邮件](https://www.cnblogs.com/ruiyeclub/p/13394493.html)
[Spring Boot集成WebSocket实现服务端推送消息到客户端](https://www.cnblogs.com/wupeixuan/p/13389757.html)
[SpringBoot中使用RSocket](https://juejin.im/post/5cf733a5e51d4510835e0260)

[SpringBoot 2.0 多图片上传加回显](https://github.com/modouxiansheng/Doraemon/tree/master/springdemo)

[spring boot实现超轻量级网关（反向代理、转发）](https://www.cnblogs.com/xiaoqi/p/spring-boot-route.html)
##
[SpringBoot开发案例之打造私有云网盘](https://www.cnblogs.com/smallSevens/p/10712643.html)
[基于springboot搭建的web系统架构](https://www.cnblogs.com/Oven5217/p/10765052.html)


[从零打造自己的Spring Boot脚手架](https://gitee.com/felord/kono)

