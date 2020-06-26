# Spring实战第四版阅读笔记
>> [参考书籍：Spring实战第四版](https://potoyang.gitbook.io/spring-in-action-v4/)

[TOC]


## 第一部分：Spring的核心
### 第1章 Spring之旅
>> 快速介绍一下Spring框架，包括Spring DI和AOP的概况，以及它们是如何帮助读者解耦应用组件的。
#### 1.Spring的bean容器
```markdown
Spring是为了解决企业级应用开发的复杂性而创建的，使用Spring可以让简单的JavaBean实现之前只有EJB才能完成的事情。
但Spring不仅仅局限于服务器端开发，任何Java应用都能在简单性、可测试性和松耦合等方面从Spring中获益。
为了降低Java开发的复杂性，Spring采取了以下4种关键策略：
    1.基于POJO的轻量级和最小侵入性编程；
    2.通过依赖注入(DI)和面向接口实现松耦合；
    3.基于切面(AOP)和惯例进行声明式编程；
    4.通过切面(AOP)和模板减少样板式代码。
DI是组装应用对象的一种方式，借助这种方式对象无需知道依赖来自何处或者依赖的实现方式。
不同于自己获取依赖对象，对象会在运行期赋予它们所依赖的对象。依赖对象通常会通过接口了解所注入的对象，这样的话就能确保低耦合。
DI能够让相互协作的软件组件保持松散耦合，而面向切面编程（AOP）允许你把遍布应用各处的功能分离出来形成可重用的组件。
当Spring装配bean的时候，这些切面能够在运行期编织起来，这样就能非常有效地赋予bean新的行为。
```    
#### 2.Spring的核心模块(Spring通过应用上下文和Bean生命周期)
```markdown
Spring通过应用上下文（Application Context）装载bean的定义并把它们组装起来。Spring应用上下文全权负责对象的创建和组装。
Spring自带了多种应用上下文的实现，它们之间主要的区别仅仅在于如何加载配置。(1.使用XML文件进行配置,2.基于Java注解的配置)
Spring自带了多种类型的应用上下文。下面罗列的几个是你最有可能遇到的。
    1.AnnotationConfigApplicationContext：从一个或多个基于Java的配置类中加载Spring应用上下文。
        ApplicationContext context = new AnnotationConfigApplicationContext(com.springinaction.knights.config.KnightConfig.class);
    2.AnnotationConfigWebApplicationContext：从一个或多个基于Java的配置类中加载SpringWeb应用上下文。
    3.ClassPathXmlApplicationContext：从类路径下的一个或多个XML配置文件中加载上下文定义，把应用上下文的定义文件作为类资源。
        ApplicationContext context = new ClassPathXmlApplicationContext("knight.xml");
    4.FileSystemXmlapplicationcontext：从文件系统下的一个或多个XML配置文件中加载上下文定义。
        ApplicationContext context = new FileSystemXmlApplicationContext("c:/knight.xml");
    5.XmlWebApplicationContext：从Web应用下的一个或多个XML配置文件中加载上下文定义。
    使用FileSystemXmlApplicationContext和使用ClassPathXmlApplicationContext的区别在于：
        前者在指定的文件系统路径下查找knight.xml文件；而后者是在所有的类路径（包含JAR文件）下查找knight.xml文件。
``` 
![bean 的生命周期](https://gblobscdn.gitbook.com/assets%2F-LmcjU5gG__lBrRbUxBO%2F-Lme8iF1koFiHj8Du-99%2F-Lme9ie_nmHZYay0eou_%2F1.5%20spring%20bean%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F.jpg?alt=media&token=8d4e3b10-fe7e-464b-beba-6e371e2f972b)   
```markdown
1.Spring对bean进行实例化；
2.Spring将值和bean的引用注入到bean对应的属性中；
3.如果bean实现了BeanNameAware接口，Spring将bean的ID传递给setBeanName()方法；
4.如果bean实现了BeanFactoryAware接口，Spring将调用setBeanFactory()方法，将BeanFactory容器实例传入；
5.如果bean实现了ApplicationContextAware接口，Spring将调用 setApplicationContext()方法，将bean所在的应用上下文的引用传入进来；
6.如果bean实现了BeanPostProcessor接口，Spring将调用它们的postProcessBefore-Initialization()方法；
7.如果bean实现了 InitializingBean 接口，Spring将调用它们的 afterPropertiesSet()方法。类似地，如果bean使用initmethod声明了初始化方法，该方法也会被调用；
8.如果bean实现了 BeanPostProcessor 接口，Spring将调用它们的 postProcessAfter-Initialization()方法；
9.此时，bean 已经准备就绪，可以被应用程序使用了，它们将一直驻留在应用上下文中，直到该应用上下文被销毁；
10.如果bean实现了DisposableBean接口，Spring将调用它的destroy()接口方法。同样，如果bean使用destroy-method声明了销毁方法，该方法也会被调用。
```
#### 3.Spring生态系统
![Spring生态系统](https://gblobscdn.gitbook.com/assets%2F-LmcjU5gG__lBrRbUxBO%2F-LmeBbFxrenF5-BmS08t%2F-LmeCAjmIq46UBxF-LiQ%2F1.7%20spring%20%E6%A1%86%E6%9E%B6%E6%A8%A1%E5%9D%97.jpg?alt=media&token=eb7f28c9-2bbb-4336-9ca7-42ba7c97bc23)
```markdown
Spring核心容器:管理着Spring应用中bean的创建、配置和管理。
Spring的AOP模块:对面向切面编程提供了丰富的支持。
数据访问与集成:JDBC,ORM,JMS.....
Web与远程调用:帮助用户将界面逻辑与应用逻辑分离,还提供了多种构建与其他应用交互的远程调用方案。
Spring的Instrumentation模块提供了为JVM添加代理（agent）的功能。
test:通过该模块，Spring为使用JNDI、Servlet 和 Portlet编写单元测试提供了一系列的mock对象实现。
    对于集成测试，该模块为加载Spring 应用上下文中的bean集合以及与Spring上下文中的bean进行交互提供了支持。
```   
```markdown
Spring Web Flow建立于Spring MVC框架之上，它为基于流程的会话式Web应用（可以想一下购物车或者向导功能）提供了支持。
Spring Web Service 提供了契约优先的 Web Service 模型，服务的实现都是为了满足服务的契约而编写的。
Spring Security 为 Spring 应用提供了声明式的安全机制。
Spring Integration 提供了多种通用应用集成模式的 Spring 声明式风格实现。
Spring Batch，使用 Spring 强大的面向 POJO 的编程模型。
Spring Data 使得在 Spring 中使用任何数据库都变得非常容易。
```
#### 4.Spring 的新功能
```markdown
Spring3.1,Spring3.2,Spring4.0新特性。
```
### 第2章 装配Bean
>> 深入探讨如何将应用中的各个组件拼装在一起，将会看到Spring所提供的自动配置bean、基于Java的配置以及XML配置。
#### 2.1 自动配置bean
```markdown
组件扫描（component scanning）：Spring 会自动发现应用上下文中所创建的bean。
自动装配（autowiring）：Spring自动满足bean之间的依赖。
为了阐述组件扫描和装配，我们需要创建几个bean，它们代表了一个音响系统中的组件。
    首先，要创建CompactDisc类，Spring会发现它并将其创建为一个bean。
    然后，会创建一个CDPlayer类，让Spring发现它，并将CompactDiscbean注入进来。
为组件扫描的bean命名:
    1.默认为类名的第一个字母变为小写,或者方法名。
    2.@Component注解配置 @Componet("lonelyHeartsClub")
    3.Java依赖注入规范中所提供的@Named注解来为bean设置ID： @Named("lonelyHeartsClub")
设置组件扫描的基础包：
     @ComponentScan的value属性中指明包的名称：@ComponentScan("cn.pbj.xxx.xxx")
     想更加清晰地表明你所设置的是基础包，可以通过basePackages属性进行配置：@ComponentScan(basePackages="soundsystem")
     设置多个基础包：@ComponentScan(basePackages={"soundsystem", "video"})
     将其指定为包中所包含的类或接口：@ComponentScan(basePackageClasses={CDPlayer.class, DVDPlayer.class})
通过为 bean 添加注解实现自动装配：
     自动装配就是让Spring自动满足bean依赖的一种方法，在满足依赖的过程中，会在Spring应用上下文中寻找匹配某个bean需求的其他bean。
     借助 Spring 的 @Autowired 注解，声明要进行自动装配。
     添加了@Autowired注解，表明当Spring创建CDPlayerbean的时候，会通过这个构造器来进行实例化并且会传入一个可设置给CompactDisc类型的bean。
     @Autowired 注解不仅能够用在构造器上，还能用在属性的 Setter 方法上。
     @Autowired 注解可以用在类的任何方法上。
     如果有多个bean都能满足依赖关系的话，Spring将会抛出一个异常，表明没有明确指定要选择哪个bean进行自动装配。
     @Autowired 是 Spring 特有的注解，可以替换为 @Inject，@Inject 注解来源于Java 依赖注入规范
```
#### 2.2 基于Java代码的配置bean
```markdown
@Bean注解会告诉 Spring 这个方法将会返回一个对象，该对象要注册为 Spring 应用上下文中的 bean。
```
#### 2.3 通过XML装配bean
#### 2.4 导入和混合配置
```markdown
在典型的Spring应用中，可能会同时使用自动化和显式配置。即便你更喜欢通过JavaConfig实现显式配置，但有的时候XML却是最佳的方案。
@Import 注解导入 将两个配置类组合在一起
 @ImportResource 注解让 Spring 同时加载XML配置和其他基于 Java 的配置
```
### 第3章 高级装配
>> 展现一些最大化Spring威力的技巧和技术，包括条件化装配、处理自动装配时的歧义性、作用域以及Spring表达式语言。
### 第4章 面向切面的Spring
>> 展示如何使用Spring的AOP特性把系统级的服务（例如安全和审计）从它们所服务的对象中解耦出来。
>> 本章也为后面的第9章、第13章和第14章做了铺垫，这几章将会分别介绍如何将Spring AOP用于声明式安全以及缓存。