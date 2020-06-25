# Spring学习
>> [Spring官网文档](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html)
>> [Spring 实战（第四版）](https://potoyang.gitbook.io/spring-in-action-v4/)
>> [Spring实战(第五版)](https://potoyang.gitbook.io/spring-in-action-v5/)

[TOC]


## 一、Spring 
>> Spring的核心是一个容器，通常称为Spring应用程序上下文，用于创建和管理应用程序组件。这些组件（或 bean）在Spring应用程序上下文中连接在一起以构成一个完整的应用程序
>> 将bean连接在一起的行为是基于一种称为 依赖注入（DI）的模式。依赖项注入的应用程序不是由组件自身创建和维护它们依赖的其他bean的生命周期，
>> 而是依赖于单独的实体（容器）来创建和维护所有组件，并将这些组件注入需要它们的bean。通常通过构造函数参数或属性访问器方法完成此操作。
>> 除了其核心容器之外，Spring和完整的相关库产品组合还提供Web框架、各种数据持久性选项、安全框架与其他系统的集成、运行时监视、微服务支持、响应式编程模型以及许多其他功能，应用于现代应用程序开发。
### 1.Spring基础知识
[随笔分类 - Java工程师之Spring Framework深度剖析专栏](https://www.cnblogs.com/jimisun/category/1359910.html)
#### 1.1.重新来认识你的老朋友Spring框架
```markdown
Spring是一个开源框架，最早由Rod Johnson创建，是为了解决企业级应用开发的复杂性而创建的。很多框架都宣称在某些方面针对Java开发做出了简化，
但是Spring的目标是致力于全方位简化Java开发，这也是Spring的根本使命 "简化Java开发"。
    1. 基于POJO的轻量级和最小侵入性编程
        Spring竭力避免因自身的API而弄乱你的代码。Spring不会强迫你实现Spring规范的接口或继承Spring规范的类。
        相反，在一个基于Spring构建的应用中，应用中的类没有任何痕迹表明你使用了Spring。最坏的情况就是你在应用的类中发现了Spring的相关注解，但它依然是POJO。
        Spring通过IOC（Inversion of Control）管理这个POJO，然后通过DI（Dependency Inject）来注入他们。
    2. 通过依赖注入和面向接口实现松耦合
        紧密耦合同时会造成代码的难以测试，难以服用，难以理解，并且典型地表现出"打地鼠“式的Bug特性（修复一个Bug，将会出现一个或多个新Bug），所以我们可以知道耦合是必须的，但必须谨慎管理耦合
        DI（依赖注入）会将所依赖关系自动交给目标对象，而不是让对象本身创建所依赖对象。通过DI依赖注入可以将对象之间依赖关系轻松解耦
        使用Spring xml配置文件或者Spring @Configuration注解为容器依赖注入。容器可以控制对象的实例化。
    3. 基于切面和惯例进行声明式编程
        DI能够将互相协作的软件保持松耦合，而面向切面编程（aspect-oriented-programming，AOP）可以将遍布应用各处的功能分离出来形成可从用的组件。
    4. 通过切面和模板减少样版式代码
```



[Spring IOC的核心机制：实例化与注入](https://www.cnblogs.com/zyjimmortalp/p/12828726.html)
[重新认识 Spring IOC](https://www.cnblogs.com/i-code/p/12832545.html)
[Spring系列之IOC的原理及手动实现](https://www.cnblogs.com/liyus/p/10112118.html)
[关于IOC容器的一些个人理解](https://www.cnblogs.com/HanJunJun-blog/p/10579712.html)

[关于Spring AOP，除了动态代理、CGLIB，你还知道什么？](https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650128800&idx=3&sn=3a3e9806348f3b280bdc5ee725238a20&chksm=f36bdc81c41c5597d0e206d637dd7bd986886212e38ae69fd506cc8488ab98bac5698204e9bc&mpshare=1&scene=23&srcid=&sharer_sharetime=1590070530809&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
[AOP 技术原理——代理模式全面总结](https://www.cnblogs.com/kubixuesheng/p/5183782.html)
[JAVA-Spring AOP五大通知类型](https://www.cnblogs.com/xiaoluohao/p/11286242.html)
[聊聊在AOP模式下的缓存方案](https://www.cnblogs.com/lori/p/10602746.html)

[spring注入bean的几种策略模式](https://www.cnblogs.com/zyjimmortalp/p/12833761.html)

[Spring中资源的加载原来是这么一回事啊！](https://www.cnblogs.com/i-code/p/12845329.html)

[Spring 循环引用(三)源码深入分析版](https://www.cnblogs.com/burg-xun/p/12865205.html)

[一文读懂Spring中的DI和AOP](https://www.cnblogs.com/xiaoyao2011/p/12866456.html)

[谈谈Spring中的BeanPostProcessor接口](https://www.cnblogs.com/tuyang1129/p/12866484.html)

[Spring之BeanFactory和FactoryBean接口的区别](https://www.cnblogs.com/dengpengbo/p/10493782.html)

[Spring 事务管理详解](https://www.cnblogs.com/liantdev/p/10149443.html)
#### 1.2.Spring框架之IOC
```markdown
IOC是控制反转（Inversion of Control），将原POJO内部管理其他对象的引用转换为IOC容器统一管理对象引用，在需要使用的时候从容器获取Bean即可。
    将对象交给IOC容器统一管理，是为了更好使用DI（Dependency Inject，依赖注入）进行POJO之间依赖关系的解耦。
    DI（Dependency Inject，依赖注入）即代码里对象之间的依赖关系转移到容器中进行装配，这样能很灵活地通过面向接口进行编程。
```
#### 1.3.Spring容器装配Bean的三种方式
```markdown
1.隐式的Bean发现机制和自动装配
    Spring从两个角度来实现自动化装配；组件扫描（Spring自动发现应用中上下文所需要的创建的Bean），自动装配（Spring自动满足Bean之间的依赖）
        1.使用@Component将普通Java类配置成SpringBean,除了这一注解还可以使用@Controller，@Service，@Repository等注解。
        2.使用@Autowired（自动装配）使Spring满足Bean的依赖
        3.配置组件扫描包（组件扫描）
            在Java类中配置组件扫描
                @ComponentScan("com.jimisun.controller")
            在XML配置文件配置组件扫描
                <context:component-scan base-package="com.jimisun.controller,com.jimisun.service"/>
2.在Java中进行装配
    1.使用@Bean注解将方法返回的实例对象添加到上下文中
    2.在@Bean返回的实例对象中可以通过构造器注入传入相关依赖
3.在XML中进行装配
    <bean class="com.jimisun.spring.example.User" id="user">
            <constructor-arg ref="myArticle"/>
    </bean>
    <bean class="com.jimisun.spring.example.MyArticle" id="myArticle"></bean>
值得一提的是，我们在项目中应该优先实用隐式的Bean发现机制和自动装配，其次使用在Java中进行装配，最后再使用在XML中进行装配。
```
#### 1.4.Spring核心概念之Bean生命周期管理
```markdown

```
### 2.Spring源码
[读Spring源码，我们可以从第一行读起](https://blog.csdn.net/qq_41907991/article/details/105667900)

[spring源码](https://www.cnblogs.com/youzhibing/category/958792.html)

[当前标签：品Spring](https://www.cnblogs.com/lixinjie/tag/%E5%93%81Spring/)
#### 2.1.SpringIOC框架容器核心源码逐步剖析
[Spring Framework框架容器核心源码逐步剖析](https://www.cnblogs.com/jimisun/p/10104002.html)
```markdown

```
#### 2.2Spring bean的实例化过程
[Spring 源码学习 - 单例bean的实例化过程](https://www.cnblogs.com/hackingForest/p/13054173.html)
[我该如何学习spring源码以及解析bean定义的注册](https://www.cnblogs.com/liyus/p/10983108.html)

#### 2.3.Spring源码分析笔记--AOP
[Spring源码分析笔记--AOP](https://www.cnblogs.com/little-sheep/p/10103797.html)

[Spring拓展接口之FactoryBean，我们来看看其源码实现](https://www.cnblogs.com/youzhibing/p/10528821.html)
#### 2.4.Spring源码分析笔记--事务管理
[Spring源码分析笔记--事务管理](https://www.cnblogs.com/little-sheep/p/10115173.html)
[【面试】足够应付面试的Spring事务源码阅读梳理（建议珍藏）](https://www.cnblogs.com/lixinjie/p/a-enough-source-read-of-spring-tx-for-interview.html)

### 3.Spring注解
#### [【Spring注解驱动开发】聊聊Spring注解驱动开发那些事儿！](https://www.cnblogs.com/binghe001/p/13047333.html)
![Spring注解驱动开发](https://img2020.cnblogs.com/blog/1729473/202006/1729473-20200605000243595-700419751.jpg)
```markdown

```
#### Spring IOC相关常用注解
```markdown
自动扫描装配Bean的相关注解
    @Component   　将java类标记成一个Spring Bean组件
    @Service       将业务层实现类标记成一个Spring Bean组件
    @Controller    将表现层类标记成一个Spring Bean组件
    @Repository    将一个持久层实现类标记成一个Spring Bean组件
作用域相关注解
    ＠scope        用来指定bean的作用域，组件默认是单例的作用域，不使用单例则声明此注解
自动装配Bean相关注解
    @Autowired    通过类型匹配，通过名称则需要Spring的@Qualifier注解配合
    @Resource     根据Bean的名称去匹配，获取不到再根据类型
```
#### [聊聊依赖注入注解@Resource和@Autowired](https://www.cnblogs.com/felordcn/p/13063802.html)
>> @Resource和@Autowired注解都可以在Spring Framework应用中进行声明式的依赖注入。
```markdown
@Resource使用在成员属性和setter方法上。
    默认情况下按照名称注入，如果没有显式声明名称则按照变量名称或者方法中对应的参数名称进行注入。Qualifier约束，装配失败后异常。
@Autowired通常适用于构造函数，成员变量以及方法上。
    默认情况下是按照类型注入，其次是Qualifier约束，然后按照名称，最后需要判断是否required。
两者总结：
    @Resource和@Autowired的优先级顺序不同（参见上图），另外@Resource属于Jakarta EE规范而@Autowired属于Spring范畴，
    @Resource无法使用在构造参数中，@Autowired支持required属性。
    从面向对象来说，@Resource更加适用于多态性的细粒度注入，而@Autowired更多专注于多态的单例注入。
```
[Spring框架使用@Autowired自动装配引发的讨论](https://www.cnblogs.com/ibigboy/p/11236729.html)

[Spring Boot @Condition 注解，组合条件你知道吗](https://www.cnblogs.com/FraserYu/p/11280420.html)

[Spring中重要的注解](https://www.cnblogs.com/rolandlee/p/11014923.html)

[精进Spring—Spring常用注解](https://blog.csdn.net/u010648555/article/details/76299467)
#### Java配置类 @Configuration
```markdown
@Configuration注释向Spring 表明这是一个配置类，它将为Spring 应用程序上下文提供beans。
配置的类方法带有@Bean注释，指示它们返回的对象应作为beans添加到应用程序上下文中（默认情况下，它们各自的bean IDs将与定义它们的方法的名称相同）。
与基于XML的配置相比，基于Java的配置具有多个优点，包括更高的类型安全性和改进的可重构性。即使这样，仅当Spring无法自动配置组件时，才需要使用Java或XML进行显式配置。
```
[spring下应用@Resource, @Autowired 和 @Inject注解进行依赖注入的差异](https://www.cnblogs.com/both-eyes/p/10096882.html)

### 4.Spring框架应用
[从spring框架中的事件驱动模型出发，优化实际应用开发代码](https://www.cnblogs.com/l3306/p/10757291.html)
[Spring4+Springmvc+quartz实现多线程动态定时调度](https://www.cnblogs.com/alterem/p/11301235.html)
###

##