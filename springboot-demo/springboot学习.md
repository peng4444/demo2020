# SpringBoot博客代码
【随笔分类 - spring源码】[spring源码]()https://www.cnblogs.com/youzhibing/category/958792.html

【SpringBoot博客】[随笔分类 - Spring Boot](https://www.cnblogs.com/niaobulashi/category/1425468.html)  【****】

【SpringBoot集合】[springboot集合]([https://www.cnblogs.com/viyoung/tag/Spring%20Boot/](https://www.cnblogs.com/viyoung/tag/Spring Boot/))  [springboot](https://www.cnblogs.com/okong/tag/springboot/)

【SpringBoot系列】[SpringBoot系列](https://www.cnblogs.com/huanzi-qch/category/1355280.html) [Spring Boot学习之旅](https://blog.csdn.net/zhaokejin521/article/details/80942545)[[曹工说Spring Boot源码（6）-- Spring怎么从xml文件里解析bean的](https://www.cnblogs.com/grey-wolf/p/12114604.html)]     [SpringBoot源码专题]( [https://www.cnblogs.com/ymbj/tag/SpringBoot%E6%BA%90%E7%A0%81%E4%B8%93%E9%A2%98/](https://www.cnblogs.com/ymbj/tag/SpringBoot源码专题/) )
##
[SpringBoot切面Aop的demo简单讲解](https://www.cnblogs.com/xuwujing/p/12927081.html)

[[基于SpringBoot实现AOP+jdk/CGlib动态代理详解](https://www.cnblogs.com/godoforange/p/11587321.html)]

[Spring Boot 2.x基础教程：构建RESTful API与单元测试](https://www.cnblogs.com/didispace/p/11606136.html)

[SpringBoot集成Shiro 实现动态加载权限](https://www.cnblogs.com/zhengqing/p/11603824.html)

[Spring Boot项目中使用Mockito](https://www.cnblogs.com/javaadu/p/11748415.html)

[SpringBoot 2.0 + Nacos + Sentinel 流控规则集中存储](https://www.cnblogs.com/smallSevens/p/11553695.html)
				
[[SpringBoot 2.0 + InfluxDB+ Sentinel 实时监控数据存储](https://www.cnblogs.com/smallSevens/p/11576263.html)]

[[SpringBoot 2.0 + 阿里巴巴 Sentinel 动态限流实战](https://www.cnblogs.com/smallSevens/p/11531534.html)]

[[springboot数据库主从方案](https://www.cnblogs.com/wangrudong003/p/11535540.html)]

[Spring Boot中使用RSocket](https://juejin.im/post/5cf733a5e51d4510835e0260)

[SpringBoot自定义过滤器的两种方式及过滤器执行顺序](https://www.cnblogs.com/ibigboy/p/11528775.html)
## 
### Spring
[关于Spring AOP，除了动态代理、CGLIB，你还知道什么？](https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650128800&idx=3&sn=3a3e9806348f3b280bdc5ee725238a20&chksm=f36bdc81c41c5597d0e206d637dd7bd986886212e38ae69fd506cc8488ab98bac5698204e9bc&mpshare=1&scene=23&srcid=&sharer_sharetime=1590070530809&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)

[SpringBoot切面Aop的demo简单讲解](https://www.cnblogs.com/xuwujing/p/12927081.html)

[Spring IOC的核心机制：实例化与注入](https://www.cnblogs.com/zyjimmortalp/p/12828726.html)

[重新认识 Spring IOC](https://www.cnblogs.com/i-code/p/12832545.html)

[spring注入bean的几种策略模式](https://www.cnblogs.com/zyjimmortalp/p/12833761.html)

[Spring中资源的加载原来是这么一回事啊！](https://www.cnblogs.com/i-code/p/12845329.html)

[Spring 循环引用(三)源码深入分析版](https://www.cnblogs.com/burg-xun/p/12865205.html)

[一文读懂Spring中的DI和AOP](cnblogs.com/xiaoyao2011/p/12866456.html)

[谈谈Spring中的BeanPostProcessor接口](https://www.cnblogs.com/tuyang1129/p/12866484.html)
### SpringMVC
[手码两万余字，SpringMVC 包教包会](https://www.cnblogs.com/lenve/p/12100698.html)

[SpringMVC源码学习：容器初始化+MVC初始化+请求分发处理+参数解析+返回值解析+视图解析](https://www.cnblogs.com/summerday152/p/12856338.html)
#### 1. SpringMVC简介
```markdown
MVC即模型-视图-控制器（Model-View-Controller）
Spring Web MVC是一种基于Java的实现了Web MVC设计模式的请求驱动类型的轻量级Web框架，即使用了MVC架构模式的思想，
将web层进行职责解耦，基于请求驱动指的就是使用请求-响应模型，框架的目的就是帮助我们简化开发，SpringWebMVC也是要简化我们日常Web开发的。
```
#### 2. SpringMVC运行原理
![SpringMVC运行原理步骤](https://img2018.cnblogs.com/blog/1363940/201910/1363940-20191031231647804-398588825.png)
```markdown
1). 客户端请求提交到DispatcherServlet
2). 由DispatcherServlet控制器查询一个或多个HandlerMapping，找到处理请求的Controller
3). DispatcherServlet将请求提交到Controller
4). Controller调用业务逻辑处理后，返回ModelAndView
5). DispatcherServlet查询一个或多个ViewResoler视图解析器，找到ModelAndView指定的视图
6). 视图负责将结果显示到客户端
```
#### 3. 常用注解
```markdown
@Controller负责注册一个bean到spring上下文中
@RequestMapping 注解为控制器指定可以处理哪些URL请求
@RequestBody 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，
然后把相应的数据绑定到要返回的对象上 ,再把HttpMessageConverter返回的对象数据绑定到controller中方法的参数上
@ResponseBody 该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区
@ModelAttribute 在方法定义上使用 @ModelAttribute注解：Spring MVC在调用目标处理方法前，会先逐个调用在方法级上标注了@ModelAttribute的方法，
在方法的入参前使用 @ModelAttribute 注解：可以从隐含对象中获取隐含的模型数据中获取对象，再将请求参数 –绑定到对象中，再传入入参将方法入参对象添加到模型中
@RequestParam　在处理方法入参处使用@RequestParam可以把请求参 数传递给请求方法
@PathVariable 绑定URL占位符到入参
@ExceptionHandler 注解到方法上，出现异常时会执行该方法
@ControllerAdvice 使一个Controller成为全局的异常处理类，类中用@ExceptionHandler方法注解的方法可以处理所有Controller发生的异常
```
### SpringBoot
#### [SpringBoot切面Aop的demo简单讲解](https://www.cnblogs.com/xuwujing/p/12927081.html)
```markdown
AOP（Aspect OrientedProgramming）：面向切面编程，面向切面编程（也叫面向方面编程），是目前软件开发中的一个热点，也是Spring框架中的一个重要内容。
利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
* 用途
    日志记录，性能统计，安全控制，权限管理，事务处理，异常处理，资源池管理。
```

[springboot超级详细的日志配置(基于logback)](https://www.cnblogs.com/wuyoucao/p/10983241.html)

[Spring Boot使用MyBatis Generator、Swagger](https://www.cnblogs.com/FireworksEasyCool/p/11133827.html)

[接近8000字的Spring/SpringBoot常用注解总结！安排！](https://www.cnblogs.com/javaguide/p/spring-annotations.html)

[实例讲解Springboot整合MongoDB进行CRUD操作的两种方式](https://www.cnblogs.com/larrydpk/p/12735620.html)

[springboot+dubbo简单分布式RPC调用demo](https://www.cnblogs.com/kuangdw/p/12783281.html)

[Spring Boot 整合 Apache Dubbo](https://www.cnblogs.com/vandusty/p/12853973.html)

[SpringBoot使用拦截器、过滤器、监听器](https://www.cnblogs.com/haixiang/p/12000685.html)

[SpringBoot 整合 Zookeeper 接入Starring微服务平台](https://www.cnblogs.com/laramia/p/11978271.html)

[基于Netty和SpringBoot实现一个轻量级RPC框架-Server篇](https://www.cnblogs.com/throwable/p/12194713.html)
