# SpringBoot博客代码

[TOC]


【SpringBoot博客】[随笔分类 - Spring Boot](https://www.cnblogs.com/niaobulashi/category/1425468.html)  【****】
【SpringBoot集合】[springboot集合]([https://www.cnblogs.com/viyoung/tag/Spring%20Boot/](https://www.cnblogs.com/viyoung/tag/Spring Boot/))  
 [springboot](https://www.cnblogs.com/okong/tag/springboot/)
【SpringBoot系列】[SpringBoot系列](https://www.cnblogs.com/huanzi-qch/category/1355280.html) 
[Spring Boot学习之旅](https://blog.csdn.net/zhaokejin521/article/details/80942545)
[[曹工说Spring Boot源码（6）-- Spring怎么从xml文件里解析bean的](https://www.cnblogs.com/grey-wolf/p/12114604.html)]     
[SpringBoot源码专题]( [https://www.cnblogs.com/ymbj/tag/SpringBoot%E6%BA%90%E7%A0%81%E4%B8%93%E9%A2%98/](https://www.cnblogs.com/ymbj/tag/SpringBoot源码专题/) )

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
#### [SpringBoot切面Aop的demo简单讲解](https://www.cnblogs.com/xuwujing/p/12927081.html)
```markdown
AOP（Aspect OrientedProgramming）：面向切面编程，面向切面编程（也叫面向方面编程），是目前软件开发中的一个热点，也是Spring框架中的一个重要内容。
利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
* 用途
    日志记录，性能统计，安全控制，权限管理，事务处理，异常处理，资源池管理。
```
[SpringBoot切面Aop的demo简单讲解](https://www.cnblogs.com/xuwujing/p/12927081.html)

[[基于SpringBoot实现AOP+jdk/CGlib动态代理详解](https://www.cnblogs.com/godoforange/p/11587321.html)]

[Spring Boot 2.x基础教程：构建RESTful API与单元测试](https://www.cnblogs.com/didispace/p/11606136.html)

[如何优雅地停止 Spring Boot 应用？](https://www.cnblogs.com/wupeixuan/p/13065986.html)
[[Springboot 优雅停止服务的几种方法](https://www.cnblogs.com/huangqingshi/p/11370291.html)]

### 2.SpringBoot注解
[接近8000字的Spring/SpringBoot常用注解总结！安排！](https://www.cnblogs.com/javaguide/p/spring-annotations.html)
[[Spring Boot 注解大全，真是太全了！](https://www.cnblogs.com/qwlscn/p/11495378.html)]

### 3.SpringBoot源码
[随笔分类 - spring/boot源码解析](https://www.cnblogs.com/grey-wolf/category/1676533.html)

### 4.SpringBoot与其他框架应用
[SpringBoot集成Shiro实现动态加载权限](https://www.cnblogs.com/zhengqing/p/11603824.html)

[Spring Boot项目中使用Mockito](https://www.cnblogs.com/javaadu/p/11748415.html)

[SpringBoot2.0+Nacos+Sentinel流控规则集中存储](https://www.cnblogs.com/smallSevens/p/11553695.html)
				
[[SpringBoot2.0+InfluxDB+Sentinel实时监控数据存储](https://www.cnblogs.com/smallSevens/p/11576263.html)]

[[SpringBoot2.0+阿里巴巴Sentinel动态限流实战](https://www.cnblogs.com/smallSevens/p/11531534.html)]

[[springboot数据库主从方案](https://www.cnblogs.com/wangrudong003/p/11535540.html)]

[SpringBoot中使用RSocket](https://juejin.im/post/5cf733a5e51d4510835e0260)

[springboot超级详细的日志配置(基于logback)](https://www.cnblogs.com/wuyoucao/p/10983241.html)

[Spring Boot使用MyBatis Generator、Swagger](https://www.cnblogs.com/FireworksEasyCool/p/11133827.html)

[springboot+dubbo简单分布式RPC调用demo](https://www.cnblogs.com/kuangdw/p/12783281.html)

[Spring Boot 整合 Apache Dubbo](https://www.cnblogs.com/vandusty/p/12853973.html)

[实例讲解Springboot整合MongoDB进行CRUD操作的两种方式](https://www.cnblogs.com/larrydpk/p/12735620.html)

[SpringBoot 整合 Zookeeper 接入Starring微服务平台](https://www.cnblogs.com/laramia/p/11978271.html)

[基于Netty和SpringBoot实现一个轻量级RPC框架-Server篇](https://www.cnblogs.com/throwable/p/12194713.html)
[聊聊Spring Boot Actuator](https://www.cnblogs.com/dongxishaonian/p/12795744.html)
[读写分离很难吗？springboot结合aop简单就实现了](https://www.cnblogs.com/yeya/p/11936239.html)
[现在Java 桌面应用程序能做到什么程度（Spring Boot+JavaFX2开发）](https://www.cnblogs.com/pengdai/p/11769194.html)
[Spring Boot 开发微信公众号后台](https://www.cnblogs.com/lenve/p/11763295.html)
### 
[【Springboot可执行jar和普通可引用jar】](https://www.cnblogs.com/lenve/p/11156340.html)
[SpringBoot开发案例Nacos配置管理中心](https://www.cnblogs.com/smallSevens/p/11223830.html)
[基于SpringBoot的开源微信开发平台，Jeewx-Boot 1.0 版本发布](https://blog.csdn.net/zhangdaiscott/article/details/95994816)
[springboot利用consul实现分布式锁](https://www.cnblogs.com/wenwuxianren/p/11181786.html)
[SpringBoot2.x 整合Spring-Session实现Session共享](https://www.cnblogs.com/lanxuan826/p/11221603.html)
[对于springboot的几种注入方法的个人看法](https://www.cnblogs.com/XSdao/p/11208437.html)
[[SpringBoot处理全局统一异常](https://www.cnblogs.com/lgjlife/p/10988439.html)]
[[【Springboot】用Prometheus+Grafana监控Springboot应用](https://www.cnblogs.com/larrydpk/p/12563497.html)]【****】
[SpringBoot集成MyBatis的分页插件PageHelper(回头草)](https://www.cnblogs.com/1315925303zxz/p/7364552.html)
[[Spring Boot接口如何设计防篡改、防重放攻击](https://www.cnblogs.com/tqlin/p/11251321.html)]	
[Springboot问题](https://www.cnblogs.com/ityouknow/p/11281643.html)
[[开发一个Spring Boot Starter!](https://www.cnblogs.com/NinWoo/p/11305650.html)]
[[基于SpringBoot从零构建博客网站 - 集成editor.md开发发布文章功能](https://www.cnblogs.com/atcloud/p/11302520.html)]
[[SpringBoot开发案例之打造十万博文Web篇](https://www.cnblogs.com/smallSevens/p/11301025.html)]
[[基于Spring Boot自建分布式基础应用](https://www.cnblogs.com/HuaiyinMarquis/p/11382145.html)]
[[Spring Boot MyBatis 数据库集群访问实现](https://www.cnblogs.com/tqlin/p/11430702.html)]
[[SpringBoot:如何优雅地处理全局异常？](https://www.cnblogs.com/coderxx/p/11331855.html)]
[【优雅写代码系统】springboot+mybatis+pagehelper+mybatisplus+druid教你如何优雅写代码](https://www.cnblogs.com/zhangxinhua/p/13091116.html)
[**SpringBoot+Eureka注册中心+Feign进行微服务之间调用**](https://blog.csdn.net/weixin_43928997/article/details/90668007?utm_source=app)

###

##




