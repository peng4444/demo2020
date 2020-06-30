# Spring实战第四版阅读笔记
>> [参考书籍：Spring实战第四版](https://potoyang.gitbook.io/spring-in-action-v4/)

[TOC]


## 第一部分：Spring的核心
### 第1章 Spring之旅
>> 快速介绍一下Spring框架，包括Spring DI和AOP的概况，以及它们是如何帮助读者解耦应用组件的。
#### 1.1 Spring的bean容器
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
#### 1.2 Spring的核心模块(Spring通过应用上下文和Bean生命周期)
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
#### 1.3 Spring生态系统
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
#### 1.4 Spring 的新功能
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
#### 3.1 Spring profile配置与激活
```markdown
在 Java 配置中，可以使用 @Profile 注解指定某个 bean 属于哪一个 profile。
例如，在配置类中，嵌入式数据库的 DataSource 可能会配置成如下所示：
    @Profile("dev") 会告诉 Spring 这个配置类中的 bean 只有在 dev profile 激活时才会创建。
在Spring3.1中，只能在类级别上使用@Profile注解。从Spring3.2开始，可以在方法级别上使用@Profile注解，与@Bean注解一同使用。
在XML中配置profile:
    <jdbc:embedded-database id="dataSource" >
        <jdbc:script location="classpath:schema.sql" />
        <jdbc:script location="classpath:test-data.sql" />
    </jdbc:embedded-database>
profile激活：
    命令行参数，通过在虚拟机参数位置指定-Dspring.profiles.active=test 来指定运行环境，标注了该环境的bean会被配置进容器中
    @ActiveProfiles("dev") 注解，我们可以使用它来指定运行测试时要激活哪个 profile。
```
#### 3.2 条件化的bean声明
```markdown
使用 @Conditional 注解条件化地配置了Bean,满足条件Spring才会实例化这个类。
```
#### 3.3 自动装配与歧义性
```markdown
在本例中，Dessert是一个接口，并且有三个类实现了这个接口，分别为Cake、Cookies 和IceCream：
因为这三个实现均使用了@Component注解，在组件扫描的时候，能够发现它们并将其创建为Spring应用上下文里面的bean。
当Spring试图自动装配setDessert()中的Dessert参数时，它并没有唯一、无歧义的可选值。Spring会抛出NoUniqueBeanDefinitionException：
解决方案：
    1.告诉Spring在遇到歧义性的时候要选择首选的bean。如果标示了两个或更多的首选 bean，那么它就无法正常工作了。
        @Primary将其中一个可选的bean设置为首选（primary）bean 能够避免自动装配时的歧义性。当遇到歧义性的时候，Spring将会使用首选的bean，而不是其他可选的bean。
        <bean id="iceCream" class="com.desserteater.IceCream" primary="true" />
    2.使用更多的限定符来缩小选择范围。
        @Qualifier注解是使用限定符的主要方式。与@Autowired和@Inject协同使用，在注入的时候指定想要注入进去的是哪个bean。
        @Qualifier("iceCream") 为 @Qualifier 注解所设置的参数就是想要注入的 bean 的 ID。
        方法上所指定的限定符与要注入的 bean 的名称是紧耦合的。对类名称的任意改动都会导致限定符失效。
        创建自定义的限定符，使用自定义的限定符注解。
```
#### 3.4 bean的作用域
```markdown
Spring 定义了多种作用域，可以基于这些作用域创建 bean，包括：
    单例（Singleton）：在整个应用中，只创建 bean 的一个实例。
    原型（Prototype）：每次注入或者通过 Spring 应用上下文获取的时候，都会创建一个新的 bean 实例。
    会话（Session）：在 Web 应用中，为每个会话创建一个 bean 实例。
    请求（Rquest）：在 Web 应用中，为每个请求创建一个 bean 实例。
1.在bean的类上使用@Scope注解，将其声明为原型bean：@Scop(ConfigurableBeanFactory.SCOPE_PROTOTYPE)或者@Scope("prototype")
2.Java配置中将方法声明为原型bean:可以组合使用@Scope和@Bean来指定所需的作用域：
3.使用XML来配置bean的话，可以使用元素的scope属性来设置作用域：使用XML来配置bean的话，可以使用元素的scope属性来设置作用域：
```
#### 3.5 Spring表达式语言
```markdown
Spring提供了两种在运行时求值的方式：
    1.属性占位符（Property placeholder）。
    2.Spring 表达式语言（SpEL）。
```
### 第4章 面向切面的Spring
>> 展示如何使用Spring的AOP特性把系统级的服务（例如安全和审计）从它们所服务的对象中解耦出来。
>> 本章也为后面的第9章、第13章和第14章做了铺垫，这几章将会分别介绍如何将Spring AOP用于声明式安全以及缓存。
#### 4.1 面向切面编程的基本原理
```markdown
通知（Advice）Spring 切面可以应用 5 种类型的通知：
    前置通知（Before）：在目标方法被调用之前调用通知功能；
    后置通知（After）：在目标方法完成之后调用通知，此时不会关心方法的输出是什么；
    返回通知（After-returning）：在目标方法成功执行之后调用通 知；
    异常通知（After-throwing）：在目标方法抛出异常后调用通知；
    环绕通知（Around）：通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为。
连接点（Join point）
    连接点是在应用执行过程中能够插入切面的一个点。这个点可以是调用方法时、抛出异常时、甚至修改一个字段时。
    切面代码可以利用这些点插入到应用的正常流程之中，并添加新的行为。
切点（Poincut）
    切点的定义会匹配通知所要织入的一个或多个连接点。
切面（Aspect）
    切面是通知和切点的结合。通知和切点共同定义了切面的全部内容 —— 它是什么，在何时和何处完成其功能。
引入（Introduction）
    引入允许我们向现有的类添加新方法或属性。
织入（Weaving）
    织入是把切面应用到目标对象并创建新的代理对象的过程。
Spring提供了4种类型的AOP支持：
    基于代理的经典Spring AOP；
    纯POJO切面；
    @AspectJ注解驱动的切面；
    注入式AspectJ切面（适用于Spring各版本）。
前三种都是Spring AOP实现的变体，Spring AOP构建在动态代理基础之上，因此，Spring对AOP的支持局限于方法拦截。
```
#### 4.2 通过切点来选择切点
```markdown
bean() 使用 bean ID 或 bean 名称作为参数来限制切点只匹配特定的 bean:
    execution(* concert.Performance.perform()) and bean('woodstock')
非操作为除了特定 ID 以外的其他 bean 应用通知：
    execution(* concert.Performance.perform()) and !bean('woodstock')
```
#### 4.3 通过POJO注解创建切面
```markdown
@Aspect注解进行了标注。该注解表明类不仅仅是一个 POJO，还是一个切面。
类中的方法都使用注解来定义切面的具体行为。
    @After 通知方法会在目标方法返回或抛出异常后调用
    @AfterReturning 通知方法会在目标方法返回后调用 @AfterReturning("execution(** concert.Performance.perform(..))")
    @AfterThrowing 通知方法会在目标方法抛出异常后调用 @AfterThrowing("execution(** concert.Performance.perform(..))")
    @Around 通知方法会将目标方法封装起来
    @Before 通知方法会在目标方法调用之前执行 @Before("execution(** concert.Performance.perform(..))")
    @Pointcut注解能够在一个@AspectJ切面内定义可重用的切点。
    为@Pointcut注解设置的值是一个切点表达式，就像之前在通知注解上所设置的那样。@Pointcut("execution(** concert.Performance.perform(..))")
在Spring中要使用XML来装配bean的话： <aop:aspectj-autoproxy> 
创建环绕通知：@Around("performce()")表明watchPerformance()方法会作为performance()切点的环绕通知。
```
#### 4.4 使用 @AspectJ注解为AspectJ切面注入依赖
```markdown

```

## 第二部分：Web中的Spring
### 第5章 构建Spring Web应用
>> 学习到Spring MVC的基本用法，它是构建在Spring理念之上的一个Web框架。
>> 编写处理Web请求的控制器以及如何透明地绑定请求参数和负载到业务对象上，同时它还提供了数据检验和错误处理的功能。
#### 5.1 SpringMVC
```markdown
Spring MVC基于模型-视图-控制器（Model-View-Controller，MVC）模式实现，能够构建像Spring框架那样灵活和松耦合的Web应用程序。
将会介绍Spring MVC Web框架，并使用新的Spring MVC注解来构建处理各种Web请求、参数和表单输入的控制器。
1.在请求离开浏览器时，会带有用户所请求内容的信息，至少会包含请求的URL。还可能带有其他的信息，例如用户提交的表单信息。
2.Spring MVC所有的请求都会通过一个前端控制器（front controller）Servlet。DispatcherServlet就是前端控制器。
    前端控制器是常用的Web应用程序模式，在这里一个单实例的Servlet将请求委托给应用程序的其他组件来执行实际的处理。
    DispatcherServlet的任务是将请求发送给Spring MVC控制器（controller）。控制器是一个用于处理请求的Spring组件。
3.处理器映射会根据请求所携带的URL信息来进行决策。将请求发送给选中的控制器。
4.控制器在完成逻辑处理后，通常会产生一些信息，这些信息需要返回给用户并在浏览器上显示。这些信息被称为模型（model）。
5.控制器所做的最后一件事就是将模型数据打包，并且标示出用于渲染输出的视图名。
创建的最简单的Spring MVC配置就是一个带有@EnableWebMvc注解的类：
```
#### 5.2 基本的控制器
```markdown
类上带有@Controller注解用来声明控制器的，但实际上这个注解对Spring MVC本身的影响并不大。
@RequestMapping(value="/", method = GET)
在Spring MVC中，控制器只是方法上添加了@RequestMapping注解的类，这个注解声明了它们所要处理的请求。
value属性指定了这个方法所要处理的请求路径，method属性细化了它所处理的HTTP方法。
```
#### 5.3 接受请求的输入
```markdown
Spring MVC 允许以多种方式将客户端中的数据传送到控制器的处理器方法中，包括：
    查询参数（Query Parameter）。
    表单参数（Form Parameter）。
    路径变量（Path Variable）。
@RequestParam("spittle_id") long spittleId
```
#### 5.4 处理表单
```markdown

```
### 第6章 渲染Web视图 
>> 如何得到 Spring MVC 控制器所生成的模型数据，并将其渲染为用户浏览器中的 HTML。
#### 6.1 理解视图解析
```markdown

```
#### 6.2 创建JSP视图
```markdown
Spring 提供了两种支持 JSP 视图的方式：
    1.InternalResourceViewResolver会将视图名解析为JSP文件。另外，如果在你的JSP页面中使用了JSP标准标签库（JavaServer Pages Standard Tag Library，JSTL）的话，
    InternalResourceViewResolver能够将视图名解析为JstlView 形式的JSP文件，从而将JSTL本地化和资源bundle变量暴露给JSTL的格式化（formatting）和信息（message）标签。
    2.Spring 提供了两个 JSP 标签库，一个用于表单到模型的绑定，另一个提供了通用的工具类特性。
```
#### 6.3 使用Apache Tiles视图定义布局
```markdown
Spring MVC 以视图解析器的形式为 Apache Tiles 提供了支持，这个视图解析器能够将逻辑视图名解析为 Tile 定义。
```
#### 6.4 使用Thymeleaf
```markdown
Thymeleaf是一项很有吸引力的技术，因为它能创建原始的模板，这些模板是纯HTML，能像静态HTML 那样以原始的方式编写和预览，
并且能够在运行时渲染动态模型数据。除此之外，Thymeleaf是与Servlet 没有耦合关系的，这样它就能够用在JSP所不能使用的领域中。
```
### 第7章 Spring MVC的高级技术 
>> 自定义Spring MVC配置、处理multipart文件上传、处理异常以及使用flash属性跨请求传递数据。
#### 7.1 Spring MVC配置的替代方案
```markdown

```
#### 7.2 处理文件上传
#### 7.3 在控制器中处理异常
```markdown
Spring 提供了多种方式将异常转换为响应：
    特定的 Spring 异常将会自动映射为指定的 HTTP 状态码；
    异常上可以添加 @ResponseStatus 注解，从而将其映射为某一个 HTTP 状态码；
    在方法上可以添加 @ExceptionHandler 注解，使其用来处理异常。
```
#### 7.4 为控制器添加通知和重定向请求场地参数
```markdown
控制器通知（controller advice）是任意带有 @ControllerAdvice 注解的类，这个类会包含一个或多个如下类型的方法：
    @ExceptionHandler 注解标注的方法；
    @InitBinder 注解标注的方法；
    @ModelAttribute 注解标注的方法。
return "redirect:/spitter/{username}";
Spring 提供了通过 RedirectAttributes 设置 flash 属性的方法。
```
### 第8章 使用Spring Web Flow 
>> 展示如何使用 Spring Web Flow来构建会话式、基于流程的 Web 应用程序。
#### 8.1 创建会话式的Web应用程序
```markdown
当用户进入一个流程时，流程执行器会为用户创建并启动一个流程执行实例。当流程暂停的时候（如为用户展示视图时），流程执行器会在用户执行操作后恢复流程。
在Spring中，<flow:flow-executor> 元素会创建一个流程执行器：<flow:flow-executor id="flowExecutor"/>
流程注册表（flow registry）的工作是加载流程定义并让流程执行器能够使用它们。
可以在Spring中使用 <flow:flow-registry> 配置流程注册表，如下所示：
    <flow:flow-registry id="flowRegistry" base-path="/WEB-INF/flows">
      <flow:flow-location-pattern value="*-flow.xml" />
    </flow:flow-registry>
```
#### 8.2 定义流程状态和行为
```markdown
在 Spring Web Flow 中，流程是由三个主要元素定义的：状态、转移和流程数据。
```
#### 8.3 保护Web流程
```markdown
Spring Web Flow是如何结合Spring Security支持流程级别的安全性的。
Spring Web Flow中的状态、转移甚至整个流程都可以借助<secured>元素实现安全性，该元素会作为这些元素的子元素。
```
### 第9章 保护Web应用
>> 如何使用Spring Security来为Web应用程序提供安全性，保护应用中的信息。
#### 9.1 Spring Security介绍
```markdown
Spring Security是为基于Spring的应用程序提供声明式安全保护的安全性框架。提供了完整的安全性解决方案，它能够在Web请求级别和方法调用级别处理身份认证和授权。
因为基于Spring框架，所以Spring Security充分利用了依赖注入（dependency injection，DI）和面向切面的技术。
Spring Security从两个角度来解决安全性问题。它使用Servlet规范中的Filter保护Web请求并限制URL级别的访问。
Spring Security还能够使用Spring AOP保护方法调用——借助于对象代理和使用通知，能够确保只有具备适当权限的用户才能访问安全保护的方法。
    @EnableWebSecurity注解将会启用Web安全功能。
Spring Security必须配置在一个实现了WebSecurityConfigurer的bean中，或者（简单起见）扩展WebSecurityConfigurerAdapter。
为了让Spring Security满足我们应用的需求，还需要再添加一点配置。具体来讲，我们需要：
    配置用户存储；
    指定哪些请求需要认证，哪些请求不需要认证，以及所需要的权限；
    提供一个自定义的登录页面，替代原来简单的默认登录页。
```
#### 9.2 使用Servlet规范中的Filter保护Web应用
>> 拦截请求,认证用户,保护视图。
```markdown
拦截请求
认证用户
    添加自定义的登录页
    启用HTTP Basic认证
    启用Remember-me功能:rememberMe()你只要登录过一次，应用就会记住你，当再次回到应用的时候你就不需要登录了。
    退出:logout()用户会退出应用，所有的Remember-me token都会被清除掉。
        logoutSuccessUrl("/")//用户被重定向到其他的页面。
保护视图
```
#### 9.3 基于数据库和LDAP进行认证
```markdown
Spring Security非常灵活，能够基于各种数据存储来认证用户。内置了多种常见的用户存储场景，如内存、关系型数据库以及LDAP。我们也可以编写并插入自定义的用户存储实现。
1.使用基于内存的用户存储:
    安全配置类扩展WebSecurityConfigurerAdapter，因此配置用户存储的最简单方式就是重载configure()方法，并以AuthenticationManagerBuilder作为传入参数。
    AuthenticationManagerBuilder有多个方法可以用来配置Spring Security对认证的支持。通过inMemoryAuthentication()方法，可以启用、配置并任意填充基于内存的用户存储。
2.基于数据库表进行认证:
    使用以JDBC为支撑的用户存储，可以使用jdbc-Authentication()方法。
3.基于 LDAP 进行认证
```

## 第三部分：后端中的 Spring
### 第10章 通过Spring和JDBC征服数据库 
>> 如何使用 Spring 的 JDBC 抽象来查询关系型数据库，这要比原生的 JDBC 简单得多。
#### 10.1 定义 Spring 对数据访问的支持
```markdown
Spring JDBC提供的数据访问异常体系解决了以上的两个问题。不同于JDBC，Spring 提供了多个数据访问异常，分别描述了它们抛出时所对应的问题。
Spring将数据访问过程中固定的和可变的部分明确划分为两个不同的类：模板（template）和回调（callback）。模板管理过程中固定的部分，而回调处理自定义的数据访问代码。
```
#### 10.2 配置数据库资源
```markdown
Spring提供了在Spring上下文中配置数据源bean的多种方式，包括：
    通过JDBC驱动程序定义的数据源；
        <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource"
              p:driverClassName="org.h2.Driver"
              p:url="jdbc:h2:tcp://localhost/~/spitter"
              p:username="sa"
              p:password="" />
        @Bean
        public DataSource dataSource() {
          DriverManagerDataSource ds = new DriverManagerDataSource();
          ds.setDriverClassName("org.h2.Driver");
          ds.setUrl("jdbc:h2:tcp://localhost/~/spitter");
          ds.setUsername("sa");
          ds.setPassword("");
          return ds;
        }
    通过JNDI查找的数据源；
        <jee:jndi-lookup id="dataSource" jndi-name="/jdbc/SpitterDS" resource-ref="true" />
    连接池的数据源。
        <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
              p:driverClassName="org.h2.Driver"
              p:url="jdbc:h2:tcp://localhost/~/spitter"
              p:username="sa"
              p:password=""
              p:initialSize="5"
              p:maxActive="10" />
        @Bean
        public BasicDataSource dataSource() {
          BasicDataSource ds = new BasicDataSource();
          ds.setDriverClassName("org.h2.Driver");
          ds.setUrl("jdbc:h2:tcp://localhost/~/spitter");
          ds.setUsername("sa");
          ds.setPassword("");
          ds.setInitialSize(5);
          ds.setMaxActive(10);
          return ds;
        }
```
#### 10.3 使用Spring的JDBC模版
```markdown
Spring为JDBC提供了三个模板类供选择：
    JdbcTemplate：最基本的Spring JDBC模板，这个模板支持简单的JDBC数据库访问功能以及基于索引参数的查询；
    NamedParameterJdbcTemplate：使用该模板类执行查询时可以将值以命名参数的形式绑定到SQL中，而不是使用简单的索引参数；
    SimpleJdbcTemplate：该模板类利用Java 5的一些特性如自动装箱、泛型以及可变参数列表来简化JDBC模板的使用。
```
###  第11章 通过对象-关系映射持久化数据 
>> 如何与ORM框架进行集成，这些框架包括Hibernate以及其他的Java持久化API（Java Persistence API，JPA）实现。
>> 除此之外，还将会看到如何发挥Spring Data JPA的魔力，在运行时自动生成Repository实现。
#### 11.1 使用Spring和Hibernate
```markdown
使用Hibernate所需的主要接口是org.hibernate.Session。
Session接口提供了基本的数据访问功能，如保存、更新、删除以及从数据库加载对象的功能。
通过Hibernate的Session接口，应用程序的Repository能够满足所有的持久化需求。
```
#### 11.2 通过 Spring 使用 JPA
```markdown
基于JPA的应用程序需要使用EntityManagerFactory的实现类来获取EntityManager实例。
```
#### 11.3借助Spring Data实现自动化的JPA Repository
```markdown
声明自定义查询
@Query("select s from Spitter s where s.email like '%gmail.com'")
List<Spitter> findAllGmailSpitters();
```
### 第12章 使用NoSQL数据库
>> 将会研究其他的Spring Data项目，它们能够持久化各种非关系型数据库中的数据，包括MongoDB、Neo4j和Redis。
#### 12.1 为MongoDB和Neo4j编写Repository
```markdown
Spring Data MongoDB提供了三种方式在Spring应用中使用MongoDB：
    通过注解实现对象-文档映射；
    使用MongoTemplate实现基于模板的数据库访问；
    自动化的运行时Repository生成功能。
@EnableMongoRepositories 注解启用了 Spring Data MongoDB 的 Repository 功能
第一个是带有@Document注解的对象类型，也就是该Repository要处理的类型。第二个参数是带有@Id注解的属性类型。
```
```markdown
Spring Data Neo4j提供了很多与Spring Data JPA和Spring Data MongoDB相同的功能，当然所针对的是Neo4j图数据库。
它提供了将Java对象映射到节点和关联关系的注解、面向模板的Neo4j访问方式以及Repository实现的自动化生成功能。
Spring Data MongoDB提供了MongoTemplate实现基于模板的MongoDB持久化，
与之类似，Spring Data Neo4j提供了Neo4jTemplate来操作Neo4j图数据库中的节点和关联关系。
```
#### 12.2 组合使用Spring和Redis
```markdown
Redis是一种特殊类型的数据库，它被称之为key-value存储。
Spring Data Redis为四种Redis客户端实现提供了连接工厂：
    JedisConnectionFactory
    JredisConnectionFactory
    LettuceConnectionFactory
    SrpConnectionFactory
    @Bean
    public RedisConnectionFactory redisCF() {
      JedisConnectionFactory cf = new JedisConnectionFactory();
      cf.setHostName("redis-server");
      cf.setPort(7379);
      cf.setPassword("foobared");
      return cf;
    }
Spring Data Redis 提供了两个模板：
    RedisTemplate
    StringRedisTemplate
@Bean
public RedisTemplate<String, Product> redisTemplate(RedisConnectionFactory cf) {
  RedisTemplate<String, Product> redis = new RedisTemplate<String, Product>();
  redis.setConnectionFactory(cf);
  redis.setKeySerializer(new StringRedisSerializer());
  redis.setValueSerializer(new Jackson2JsonRedisSerializer<Product>(Product.class));
  return redis;
}
```
### 第13章 缓存数据
>> 为上述的持久化章提供了一个缓存层，如果数据已经可用的话，它会避免数据库操作，从而提升应用的性能。
#### 13.1 启用声明式缓存
```markdown
Spring 对缓存的支持有两种方式：
    注解驱动的缓存
        使用Spring的缓存抽象时，最为通用的方式就是在方法上添加@Cacheable和@CacheEvict注解。
        在往bean上添加缓存注解之前，必须要启用Spring对注解驱动缓存的支持。@EnableCaching
    XML 声明的缓存
        <cache:annotation-driven /> 启用注解驱动的缓存。
@Cacheable 和 @CachePut 注解都可以填充缓存
 @CacheEvict 注解移除缓存条目
```
#### 13.2 使用 Ehcache、Redis 和 GemFire 实现缓存功能
#### 13.3 注解驱动的缓存
### 第14章 保护方法应用 
>> 将会把Spring Security应用于后端，它会拦截方法的调用并确保调用者被授予了适当的权限。
>> 使用Spring Security保护bean方法。通过这种方式，就能声明安全规则，保证如果用户没有执行方法的权限，就不会执行相应的方法。
#### 14.1 使用注解保护方法
```markdown
Spring Security 提供了三种不同的安全注解：
    Spring Security 自带的 @Secured 注解；
    JSR-250 的 @RolesAllowed 注解；
    表达式驱动的注解，包括 @PreAuthorize、@PostAuthorize、@PreFilter 和 @PostFilter。
@Secured 和 @RolesAllowed 方案非常类似，能够基于用户所授予的权限限制对方法的访问。
Spring Security 提供了 @PreAuthorize 和 @PostAuthorize在方法上定义更灵活的安全规则
@PreFilter/@PostFilter 能够过滤方法返回的以及传入方法的集合。
@EnableGlobalMethodSecurity注解启用基于注解的方法安全性
```
#### 14.2 使用表达式实现方法级别的安全性
```markdown
@Secured 和 @RolesAllowed 能够限制只有用户具备所需的权限才能触发方法的执行。
但是，这两个注解的不足在于它们只能基于用户授予的权限来做出决策。
Spring Security还提供了两个注解，@PreAuthorize和@PostAuthorize，它们能够基于表达式的计算结果来限制方法的访问。
@PreAuthorize 和 @PostAuthorize 之间的关键区别在于表达式执行的时机。
    @PreAuthorize 的表达式会在方法调用之前执行，如果表达式的计算结果不为 true 的话，将会阻止方法执行。
    与之相反，@PostAuthorize 的表达式直到方法返回才会执行，然后决定是否抛出安全性的异常。
@PreFilter 和 @PostFilter 提供 SpEL 表达式，过滤方法的输入和输出。
```
## 第四部分　Spring集成
### 第15章 使用远程服务
>> 如何将应用程序中的对象导出为远程服务，还会学习如何透明地访问远程服务，这些服务就像是应用程序中的其他对象一样。
>> 将会介绍各种远程技术，包括 RMI、Hessian/Burlap 以及使用 JAX-WS 的 SOAP Web 服务。
#### 15.1 Spring远程调用
```markdown
有多种可以使用的远程调用技术，包括：
    远程方法调用（Remote Method Invocation，RMI）；不考虑网络限制时（例如防火墙），访问/发布基于Java的服务
    Caucho的Hessian和Burlap；考虑网络限制时，通过HTTP访问/发布基于Java的服务。Hessian是二进制协议，而Burlap是基于XML的
    Spring基于HTTP的远程服务；考虑网络限制，并希望使用基于XML或专有的序列化机制实现Jav 序列化时，访问/发布基于Spring的服务
    使用JAX-RPC和JAX-WS的Web Service。访问/发布平台独立的、基于SOAP的Web服务
```
#### 15.2 访问和发布RMI服务
```markdown
1.编写一个服务实现类，类中的方法必须抛出 java.rmi.RemoteException 异常；
2.创建一个继承于 java.rmi.Remote 的服务接口；
3.运行 RMI 编译器（rmic），创建客户端 stub 类和服务端 skeleton 类；
4.启动一个 RMI 注册表，以便持有这些服务；
5.在 RMI 注册表中注册服务。
```
#### 15.3 使用 Hessian 和 Burlap 服务
```markdown
Hessian 和 Burlap 是 Caucho Technology 提供的两种基于 HTTP 的轻量级远程服务解决方案。
借助于尽可能简单的 API 和通信协议，它们都致力于简化 Web 服务。
```
#### 15.4 使用 Spring 的 HTTP invoker
```markdown
HTTP invoker是一个新的远程调用模型，作为Spring框架的一部分，能够执行基于HTTP的远程调用（让防火墙不为难），并使用Java的序列化机制（让开发者也乐观其变）。
```
#### 15.5 使用 Spring 开发 Web 服务
```markdown
@Bean
public JaxWsProxyFactoryBean spitterService() {
  JaxWsProxyFactoryBean proxy = new JaxWsProxyFactoryBean();
  proxy.setWsdlDocument("http://localhost:8080/services/SpitterService?wsdl");
  proxy.setServiceName("spitterService");
  proxy.setPortName("spitterServiceHttpPort");
  proxy.setServiceInterface(SpitterService.class);
  proxy.setNamespaceUrl("http://spitter.com");
  return proxy;
}
```
### 第16章 使用Spring MVC创建REST API 
>> 将会探讨如何使用Spring MVC构建RESTful服务，它关注于应用程序中的资源。
>> REST就是将资源的状态以最适合客户端或服务端的形式从服务器端转移到客户端
#### 16.1 编写处理 REST 资源的控制器
#### 16.2 以 XML、JSON 及其他格式来表述资源
#### 16.3 使用 REST 资源数据为王。
```markdown
@ResponseBody提供了一种很有用的方式，能够将控制器返回的Java对象转换为发送到客户端的资源表述。
Spring 提供了多种方式来处理这样的场景：
    使用 @ResponseStatus 注解可以指定状态码；
    控制器方法可以返回 ResponseEntity 对象，该对象能够包含 更多响应相关的元数据；
    异常处理器能够应对错误场景，这样处理器方法就能关注于正常的状况。
```
### 第17章 Spring消息 
>> 将会探索一种不同的应用集成方式，Spring如何用于Java消息服务（JMS）和高级消息队列协议（AMQP），从而实现应用程序之间的异步通信。
#### 17.1 异步消息简介
```markdown
消息则是异步发送的,客户端不需要等待服务处理消息，甚至不需要等待消息投递完成。
客户端发送消息，然后继续执行，这是因为客户端假定服务最终可以收到并处理这条消息。
```
#### 17.2 基于JMS的消息功能
```markdown
Spring通过基于模板的抽象为JMS功能提供了支持，这个模板也就是JmsTemplate。
使用JmsTemplate能够非常容易地在消息生产方发送队列和主题消息，在消费消息的那一方，也能够非常容易地接收这些消息
ActiveMQ 是一个伟大的开源消息代理产品，也是使用 JMS 进行异步消息传递的最佳选择。
ActiveMQConnectionFactory是ActiveMQ自带的连接工厂，在Spring中可以使用如下方式进行配置：
<bean id="connectionFactory"
      class="org.apache.activemq.spring.ActiveMQConnectionFactory" />
```
#### 17.3 使用Spring和AMQP发送消息
```markdown
RabbitMQ是一个流行的开源消息代理，它实现了AMQP。Spring AMQP为RabbitMQ提供了支持，包括RabbitMQ连接工厂、模板以及Spring配置命名空间。
```
### 第18章 使用WebSocket和STOMP实现消息功能
>> 将会展现Spring的一项新功能，它支持在服务器和Web客户端之间实现异步通信。
#### 18.1 使用WebSocket和STOMP实现消息功能
```markdown
在浏览器和服务器之间发送消息
在 Spring MVC 控制器中处理消息
为目标用户发送消息
WebSocket协议提供了通过一个套接字实现全双工通信的功能。除了其他的功能之外，它能够实现Web浏览器和服务器之间的异步通信。
全双工意味着服务器可以发送消息给浏览器，浏览器也可以发送消息给服务器。
Spring 4.0为WebSocket通信提供了支持，包括：
    - 发送和接收消息的低层级 API；
    - 发送和接收消息的高级 API；
    - 用来发送消息的模板；
    - 支持SockJS，用来解决浏览器端、服务器以及代理不支持WebSocket的问题。
在Spring的Java配置中，这需要在一个配置类上使用@EnableWebSocket，并实现 WebSocketConfigurer 接口
Spring为STOMP消息提供了基于Spring MVC的编程模型。
Spring 提供了两种发送数据给客户端的方法：
    作为处理消息或处理订阅的附带结果；
    使用消息模板。
在使用 Spring 和 STOMP 消息功能的时候，我们有三种方式利用认证用户：
    @MessageMapping 和 @SubscribeMapping 标注的方法能够使用 Principal 来获取认证用户；
    @MessageMapping、@SubscribeMapping 和 @MessageException 方法返回的值能够以消息的形式发送给认证用户；
    SimpMessagingTemplate 能够发送消息给特定用户。
```
### 第19章 使用Spring发送Email
>> 将会展现如何借助Spring以Email的形式发送异步消息给目标人群。
#### 19.1 配置Spring的Email抽象功能
#### 19.2 发送丰富内容的Email消息
#### 19.3 使用模板构建Email消息
### 第20章 使用JMX管理SpringBean
>> 会学到如何把配置在 Spring 中 bean 自动导出为 JMX MBean。
#### 20.1 将 Spring bean 暴露为 MBean
#### 20.2 远程管理 Spring Bean
#### 20.3 处理 JMX 通知
### 第21章 借助SpringBoot简化Spring开发
>> 在典型的Spring应用中，会有很多繁杂的样板式配置，在这一章将会看到SpringBoot如何移除这些配置，能够让我们关注于业务功能的实现。
#### 21.1 使用 Spring Boot Starter 添加项目依赖
```markdown
Spring Boot Starter:它将常用的依赖分组进行了整合，将其合并到一个依赖中，这样就可以一次性添加到项目的Maven或Gradle构建中；
```
#### 21.2 自动化的 bean 配置
```markdown
自动配置：Spring Boot的自动配置特性利用了Spring4对条件化配置的支持，合理地推测应用所需的bean并自动化配置它们；
```
#### 21.3 Groovy 与 Spring Boot CLI
```markdown
命令行接口（Command-line interface，CLI）：SpringBoot的CLI发挥了Groovy编程语言的优势，并结合自动配置进一步简化Spring应用的开发；
```
#### 21.4 Spring Boot Actuator
```markdown
Actuator：它为 Spring Boot 应用添加了一定的管理特性。
```