# Spring学习
>> [Spring官网文档](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html)
>> [Spring 实战（第四版）](https://potoyang.gitbook.io/spring-in-action-v4/)
>> [Spring实战(第五版)](https://potoyang.gitbook.io/spring-in-action-v5/)

[TOC]


## Spring基础
[随笔分类 - Java工程师之Spring Framework深度剖析专栏](https://www.cnblogs.com/jimisun/category/1359910.html)
![可能需要知道的Spring](https://img2020.cnblogs.com/blog/1489208/202006/1489208-20200610141802146-901108964.png)
### 1.什么是spring?
```markdown
Spring是一个轻量级Java开发框架，最早有Rod Johnson创建，目的是为了解决企业级应用开发的业务逻辑层和其他各层的耦合问题。
    它是一个分层的JavaSE/JavaEE full-stack（一站式）轻量级开源框架，为开发Java应用程序提供全面的基础架构支持。
Spring是一个开源框架，处于MVC模式中的控制层，它能应对需求快速的变化，其主要原因它有一种面向切面编程（AOP）的优势，其次它提升了系统性能，
    通过依赖倒置机制（IOC），系统中用到的对象不是在系统加载时就全部实例化，而是在调用到这个类时才会实例化该类的对象，从而提升了系统性能。
Spring负责基础架构，因此Java开发者可以专注于应用程序的开发。Spring最根本的使命是解决企业级应用开发的复杂性，即简化Java开发。
Spring可以做很多事情，它为企业级开发提供给了丰富的功能，但是这些功能的底层都依赖于它的两个核心特性，也就是依赖注入（DI）和面向切面编程（AOP）。
Spring的优点：(为什么要用Spring)
    1、降低了组件之间的耦合性，实现了软件各层之间的解耦。
    2、可以使用容易提供的众多服务，如事务管理，消息服务，日志记录等。
    3、容器提供了AOP技术，利用它很容易实现如权限拦截、运行期监控等功能。
    4、更方便的框架集成，Spring可以很方便的集成其他框架比如MyBatis,Hibernate。
Spring中AOP技术是设计模式中的动态代理模式。只需实现jdk提供的动态代理接口InvocationHandler，所有被代理对象的方法都由InvocationHandler接管实际的处理任务。
    面向切面编程中还要理解切入点、切面、通知、织入等概念。
Spring中IOC则利用了Java强大的反射机制来实现。所谓依赖注入即组件之间的依赖关系由容器在运行期决定。
    其中依赖注入的方法有两种，通过构造函数注入，通过set方法进行注入。
为了降低Java开发的复杂性，Spring采取了以下4种关键策略
    - 基于POJO的轻量级和最小侵入性编程；
    - 通过依赖注入和面向接口实现松耦合；
    - 基于切面和惯例进行声明式编程；
    - 通过切面和模板减少样板式代码。
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
### 2.Spring框架的设计目标，设计理念，核心，主要模块【5+】
```markdown
Spring设计目标：Spring为开发者提供一个一站式轻量级应用开发平台；
Spring设计理念：在JavaEE开发中，支持POJO和JavaBean开发方式，使应用面向接口开发，充分支持OO（面向对象）设计方法；
Spring通过IoC容器实现对象耦合关系的管理，并实现依赖反转，将对象之间的依赖关系交给IoC容器，实现解耦；
Spring框架的核心：IoC容器和AOP模块。通过IoC容器管理POJO对象以及他们之间的耦合关系；通过AOP以动态非侵入的方式增强服务。
IoC让相互协作的组件保持松散的耦合，而AOP编程允许你把遍布于应用各层的功能分离出来形成可重用的功能组件。
Spring的七大模块：
    Spring Core：框架的最基础部分，提供IoC容器，对bean进行管理。
    Spring Context：继承BeanFactory，提供上下文信息，扩展出JNDI、EJB、电子邮件、国际化等功能。
    Spring DAO：提供了JDBC的抽象层，还提供了声明性事务管理方法。
    Spring ORM：提供了JPA、JDO、Hibernate、MyBatis 等ORM映射层。
    Spring AOP：集成了所有AOP功能
    Spring Web：提供了基础的Web开发的上下文信息，现有的Web框架，如JSF、Tapestry、Structs等，提供了集成
    Spring Web MVC：提供了Web应用的Model-View-Controller全功能实现。
```
### 3.Spring的优缺点【3+】
```markdown
优点
    方便解耦，简化开发。Spring就是一个大工厂，可以将所有对象的创建和依赖关系的维护，交给Spring管理。
    AOP编程的支持。Spring提供面向切面编程，可以方便的实现对程序进行权限拦截、运行监控等功能。
    声明式事务的支持。只需要通过配置就可以完成对事务的管理，而无需手动编程。
    方便程序的测试。Spring对Junit4支持，可以通过注解方便的测试Spring程序。
    方便集成各种优秀框架。Spring不排斥各种优秀的开源框架，其内部提供了对各种优秀框架的直接支持（如：Struts、Hibernate、MyBatis等）。
    降低JavaEE API的使用难度。Spring对JavaEE开发中非常难用的一些API（JDBC、JavaMail、远程调用等），都提供了封装，使这些API应用难度大大降低。
缺点
    - Spring明明一个很轻量级的框架，却给人感觉大而全
    - Spring依赖反射，反射影响性能
    - 使用门槛升高，入门Spring需要较长时间
```
### 4.控制反转（IOC）&依赖注入（DI）【10+】
[Spring IOC的核心机制：实例化与注入](https://www.cnblogs.com/zyjimmortalp/p/12828726.html)
[重新认识 Spring IOC](https://www.cnblogs.com/i-code/p/12832545.html)
[Spring系列之IOC的原理及手动实现](https://www.cnblogs.com/liyus/p/10112118.html)
[手写IOC实现过程](https://www.cnblogs.com/tc971121/p/13458742.html)
[关于IOC容器的一些个人理解](https://www.cnblogs.com/HanJunJun-blog/p/10579712.html)
```markdown
IOC（Inverse of Control:控制反转）是一种设计思想：就是将原本在程序中手动创建对象的控制权，交由Spring框架来管理。
    IoC容器是Spring用来实现IoC的载体，IoC容器实际上就是个Map（key，value）,Map中存放的是各种对象。
    BeanFactory接口是Spring Ioc容器的核心接口。
IoC最常见以及最合理的实现方式叫做依赖注入（Dependency Injection，简称 DI）。
    将对象之间的相互依赖关系交给IO容器来管理，并由IoC容器完成对象的注入。
    这样可以很大程度上简化应用的开发，把应用从复杂的依赖关系中解放出来。 
    IoC容器就像是一个工厂一样，当我们需要创建一个对象的时候，只需要配置好配置文件/注解即可，完全不用考虑对象是如何被创建出来的。
    【对于Spring框架来说就是由Spring来负责控制对象的生命周期和对象间的关系。】
IOC是实现方式优点：
    1. 对象之间的耦合度或者说依赖程度降低；
    2. 资源变的容易管理；比如你用Spring容器提供的话很容易就可以实现一个单例。
DI：我们在使用Spring容器的时候，容器通过调用set方法或者是构造器来建立对象之间的依赖关系。
控制反转是目标，依赖注入是我们实现控制反转的一种手段。
Java中实现依赖注入的三种方式？
    - 1.构造器注入:IoC Service Provider会检查被注入对象的构造方法，取得它所需要的依赖对象列表，进而为其注入相应的对象。
        这种方法的优点是在对象构造完成后就处于就绪状态，可以马上使用。缺点是当依赖对象较多时，构造方法的参数列表会比较长，构造方法无法被继承，无法设置默认值。
        对于非必需的依赖处理可能需要引入多个构造方法，参数数量的变动可能会造成维护的困难。
    - 2.set方法注入:当前对象只需要为其依赖对象对应的属性添加setter方法，就可以通过setter方法将依赖对象注入到被依赖对象中。
        setter方法注入在描述性上要比构造方法注入强，并且可以被继承，允许设置默认值。缺点是无法在对象构造完成后马上进入就绪状态。
    - 3.接口注入: 必须实现某个接口，接口提供方法来为其注入依赖对象。使用少，因为它强制要求被注入对象实现不必要接口，侵入性强。
依赖注入的相关注解？
    @Autowired：自动按类型注入，如果有多个匹配则按照指定Bean的id查找，查找不到会报错。
    @Qualifier：在自动按照类型注入的基础上再按照Bean的id注入，给变量注入时必须搭配@Autowired，给方法注入时可单独使用。
    @Resource ：直接按照Bean的id注入，只能注入Bean类型。
    @Value ：用于注入基本数据类型和String类型。
依赖注入的过程？
    getBean方法获取Bean实例，该方法会调用doGetBean ，doGetBean真正实现从IoC容器获取Bean的功能，也是触发依赖注入的地方。
    具体创建Bean对象的过程由ObjectFactory的createBean完成，该方法主要通过createBeanInstance方法生成Bean包含的Java对象实例和populateBean方法对Bean属性的依赖注入进行处理。
```
### 4.Spring的依赖注入(DI)
[SpringDI四种依赖注入方式详解](https://www.cnblogs.com/ziph/p/13337025.html)
```markdown
Spring利用反射创建对象，并将创建好的对象放入一个大工厂，实现了对象创建和使用的解耦。需要使用的时候可以方便的通过BeanFactory.getBean()获取。
在此之上还扩展了对注解的支持，使用注解就可以注入对象。
依赖注入(DI):在Spring创建对象的同时，为其属性赋值，称之为依赖注入。
组件之间依赖关系由容器在运行期决定的，即由容器动态的将某个依赖关系注入到组件之中。
DI依赖注入流程? （实例化，处理Bean之间的依赖关系）过程在Ioc初始化后，依赖注入的过程是用户第一次向IoC容器索要Bean时触发
    - 如果设置lazy-init=true，会在第一次getBean的时候才初始化bean，lazy-init=false，会容器启动的时候直接初始化（singleton bean）；
    - 调用BeanFactory.getBean()生成bean的；
    - 生成bean过程运用装饰器模式产生的bean都是beanWrapper（bean的增强）；
依赖注入怎么处理bean之间的依赖关系?
    通过在beanDefinition载入时，如果bean有依赖关系，通过占位符来代替，在调用getbean时候，如果遇到占位符，从ioc里获取bean注入到本实例来。
依赖注入的三种方式：
    - 使用构造方法注入
        创建对象时，Spring工厂会通过构造方法为对象的属性赋值。由于某些框架或者项目中并没有为JavaBean提供Setter方法，就可以利用其构造方法来注入。
             <!--构造注入-->
            <bean id="u3" class="com.mylifes1110.bean.Student">
                <!-- 除标签名称有变化,其他均和Set注入一致 -->
                <constructor-arg name="id" value="1234" /> 
                <constructor-arg name="name" value="tom" />
                <constructor-arg name="age" value="20" />
                <constructor-arg name="sex" value="male" />
            </bean>
    - 使用set方法注入
        Setter方法注入，只需要提供对应的Setter方法接口实现注入，由于JavaBean一般都实现了Setter方法，所以Setter方法注入也成为常用的注入方法之一。
            <bean id="User" class="com.xxx.xxx.User">
                <property name="id" value="xxx"></property>
                <property name="name" value="xxx"></property>
                <property name="age" value="18"></property>
            </bean>
    - 自动注入
        不用在配置中指定为哪个属性赋值，及赋什么值。由spring自动根据某个 "原则" ，在工厂中查找一个bean，为属性注入属性值。
        - 基于名称自动注入值
            <bean id="UserDao" class="com.xxx.dao.impl.UserDaoImpl"/>
                <!--为UserServiceImpl中的属性基于名称自动注入值-->
                <bean id="userService" class="com.xxx.service.impl.userServiceImpl" autowire="byName"/>
            </beans>
        - 基于类型自动注入值，根据实现的接口来判断并自动注入值，如果实现此接口的实现类太多，它会在很多实现此接口的实现类中选择名字相同的实现类进行注入。（现根据判断，如果不成功，则根据名称注入）
            <bean id="userDao" class="com.xxx.dao.UserDaoImpl" />
                <!--为UserServiceImpl中的属性基于类型自动注入值-->
                <bean id="userService" class="com.xxx.service.impl.UserServiceImpl" autowire="byType"/>
            </beans>
    - 使用注解自动注入
        @Autowired	基于类型自动注入
        @Resource	基于名称自动注入
        @Qualifier("userDAO")	限定要自动注入的bean的id，一般和@Autowired联用
        @Value	注入简单类型数据 （jdk8种基本数据类型+String类型）
            @Autowired //注入类型为UserDao的bean
            @Qualifier("userDao") //如果有多个类型为UserDao的bean,可以用此注解从中指定一个
            private UserDao userDao;
                @Resource("userDao") //注入id=“userDao”的bean
                private UserDao userDao;
            @Value("1")    //注入数字
            private Integer id;
            @Value("Ziph") //注入String
            private String name;
```
### 5.SpringIoC容器初始化方式&流程
```markdown
1.基于XML文件的配置:这种配置文件的格式常用<beans>开头，然后运用一系列的bean定义和专门的应用配置选项组成。
    Spring XML配置方式是使用被Spring命名空间所支持的一些列XML的标签来实现的。
2.基于注解的配置:可以使用注解的方式来代替XML方式的bean元素的配置。这就是组件扫描，常用依赖注入的一些注解有：
    @Controller @Service @Autowired @RequestMapping @RequestParam @ModelAttribute @Cacheable @CacheFlush @Resource 
    @PostConstruct @PreDestroy @Repository @Scope @SessionAttributes @InitBinder @Required @Qualifier
3.组件扫描：容器会扫描base-package指定的包及其子包下面的所有类，如果该类有一些特定的注解，则纳入容器进行管理。
4.在类前面添加的一些特定的注解：@Component 通用注解 @Repository 持久层注解 @Service 业务层注解、 @Controller 控制层注解
5.基于Java的配置
Spring IOC初始化流程?
    resource定位即寻找用户定义的bean资源，
    由ResourceLoader通过统一的接口Resource接口来完成beanDefinition载入BeanDefinitionReader读取、
    解析Resource定位的资源 成BeanDefinition载入到ioc中（通过HashMap进行维护BD）BeanDefinition注册
    即向IOC容器注册这些BeanDefinition，通过BeanDefinitionRegistery实现
BeanDefinition加载流程?
    定义BeanDefinitionReader解析xml的document BeanDefinitionDocumentReader解析document成beanDefinition
```
### 6.Spring什么是三级缓存【5+】
[根据 Spring 源码写一个带有三级缓存的 IOC](https://zhuanlan.zhihu.com/p/144627581)
```markdown
1.第一级缓存：单例缓存池singletonObjects。
2.第二级缓存：早期提前暴露的对象缓存earlySingletonObjects。（属性还没有值对象也没有被初始化）
3.第三级缓存：singletonFactories单例对象工厂缓存。
```
### 7.spring如何解决循环依赖【5+】
[面试必杀技，讲一讲Spring中的循环依赖](https://www.cnblogs.com/daimzh/p/13256413.html)
[Spring 循环引用(三)源码深入分析版](https://www.cnblogs.com/burg-xun/p/12865205.html)
[帮助你更好的理解Spring循环依赖](https://www.cnblogs.com/CodeBear/p/13327899.html)
```markdown
什么是循环依赖？
    - A中注入了B，B中注入了A。（自己依赖自己）
怎么检测是否存在循环依赖?
    - Bean在创建的时候可以给该Bean打标，如果递归调用回来发现正在创建中的话，即说明了循环依赖了。
Spring如解决Bean循环依赖问题?
    Spring解决循环依赖是有前置条件的：
        1.出现循环依赖的Bean必须要是单例
        2.依赖注入的方式不能全是构造器注入的方式（很多博客上说，只能解决setter方法的循环依赖，这是错误的）
Spring使用了三级缓存解决了循环依赖的问题。
    在populateBean()给属性赋值阶段里面Spring会解析你的属性，并且赋值，当发现，A对象里面依赖了B，此时又会走getBean方法，但这个时候，你去缓存中是可以拿的到的。
    因为我们在对createBeanInstance对象创建完成以后已经放入了缓存当中，所以创建B的时候发现依赖A，直接就从缓存中去拿，此时B创建完，A也创建完，一共执行了4次。
    至此Bean的创建完成，最后将创建好的Bean放入单例缓存池中。
Spring中循环依赖场景有：
    - 构造器的循环依赖
    - 属性的循环依赖
    - singletonObjects：第一级缓存，里面放置的是实例化好的单例对象；earlySingletonObjects：第二级缓存，里面存放的是提前曝光的单例对象；
        singletonFactories：第三级缓存，里面存放的是要被实例化的对象的对象工厂
    - 创建bean的时候Spring首先从一级缓存singletonObjects中获取。如果获取不到，并且对象正在创建中，就再从二级缓存earlySingletonObjects中获取，
        如果还是获取不到就从三级缓存singletonFactories中取（Bean调用构造函数进行实例化后，即使属性还未填充，就可以通过三级缓存向外提前暴露依赖的引用值（提前曝光），
        根据对象引用能定位到堆中的对象，其原理是基于Java的引用传递），取到后从三级缓存移动到了二级缓存完全初始化之后将自己放入到一级缓存中供其他使用，
    - 因为加入singletonFactories三级缓存的前提是执行了构造器，所以构造器的循环依赖没法解决。
    - 构造器循环依赖解决办法：在构造函数中使用@Lazy注解延迟加载。在注入依赖时，先注入代理对象，当首次使用时再创建对象说明：
        一种互斥的关系而非层次递进的关系，故称为三个Map而非三级缓存的缘由 完成注入；
Spring是如何解决的循环依赖？（关于循环依赖的解决方式应该要分两种情况来讨论）
    - 简单的循环依赖（没有AOP）
    - 结合了AOP的循环依赖
面试官：”Spring是如何解决的循环依赖？“
    - 答：Spring通过三级缓存解决了循环依赖，其中一级缓存为单例池（singletonObjects）,二级缓存为早期曝光对象earlySingletonObjects，三级缓存为早期曝光对象工厂（singletonFactories）。
    当A、B两个类发生循环引用时，在A完成实例化后，就使用实例化后的对象去创建一个对象工厂，并添加到三级缓存中，如果A被AOP代理，那么通过这个工厂获取到的就是A代理后的对象，
    如果A没有被AOP代理，那么这个工厂获取到的就是A实例化的对象。当A进行属性注入时，会去创建B，同时B又依赖了A，所以创建B的同时又会去调用getBean(a)来获取需要的依赖，
    此时的getBean(a)会从缓存中获取，第一步，先获取到三级缓存中的工厂；第二步，调用对象工工厂的getObject方法来获取到对应的对象，得到这个对象后将其注入到B中。
    紧接着B会走完它的生命周期流程，包括初始化、后置处理器等。当B创建完后，会将B再注入到A中，此时A再完成它的整个生命周期。至此，循环依赖结束！
面试官：”为什么要使用三级缓存呢？二级缓存能解决循环依赖吗？“
    - 答：如果要使用二级缓存解决循环依赖，意味着所有Bean在实例化后就要完成AOP代理，这样违背了Spring设计的原则，
    Spring在设计之初就是通过AnnotationAwareAspectJAutoProxyCreator这个后置处理器来在Bean生命周期的最后一步来完成AOP代理，而不是在实例化后就立马进行AOP代理。
```
### 8.spring AOP的原理及动态代理【10+】
[Spring中AOP相关的API及源码解析](https://www.cnblogs.com/daimzh/p/13226649.html)
[仔细想想SpringAOP也不难嘛，面试没有必要慌](https://www.cnblogs.com/ziph/p/13339503.html)
[springAOP的三种实现方式](https://www.cnblogs.com/liuyj-top/p/13346206.html)
[静态代理和动态代理（jdk/cglib）详解](https://www.cnblogs.com/tc971121/p/13474638.html)
[Java中的静态代理和动态代理](https://www.cnblogs.com/csh24/p/13590338.html)
[关于Spring AOP，除了动态代理、CGLIB，你还知道什么？](https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650128800&idx=3&sn=3a3e9806348f3b280bdc5ee725238a20&chksm=f36bdc81c41c5597d0e206d637dd7bd986886212e38ae69fd506cc8488ab98bac5698204e9bc&mpshare=1&scene=23&srcid=&sharer_sharetime=1590070530809&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
[AOP 技术原理——代理模式全面总结](https://www.cnblogs.com/kubixuesheng/p/5183782.html)
[JAVA-Spring AOP五大通知类型](https://www.cnblogs.com/xiaoluohao/p/11286242.html)
[聊聊在AOP模式下的缓存方案](https://www.cnblogs.com/lori/p/10602746.html)
[手写AOP实现过程](https://www.cnblogs.com/tc971121/p/13490708.html)
```markdown
AOP(Aspect-Oriented Programming:⾯向切⾯编程)：能够将那些与业务⽆关，却为业务模块所共同调⽤的逻辑或责任（例如事务处理、⽇志管理、权限控制等）封装起来，
    便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。
​Spring AOP就是基于动态代理的，如果要代理的对象，实现了某个接⼝，那么Spring AOP会使⽤JDKProxy，去创建代理对象，⽽对于没有实现接⼝的对象，
    就⽆法使⽤JDK Proxy去进⾏代理了，这时候Spring AOP会使⽤Cglib，这时候Spring AOP会使⽤Cglib⽣成⼀个被代理对象的⼦类来作为代理，
AOP主要用来解决：在不改变原有业务逻辑的情况下，增强横切逻辑代码，根本上解耦合，避免横切逻辑代码重复
    切 ：指的是横切逻辑，原有业务逻辑代码不动，只能操作横切逻辑代码，所以面向横切逻辑
    面 ：横切逻辑代码往往要影响的是很多个方法，每个方法如同一个点，多个点构成一个面。这里有一个面的概念。
    Aspect：切面，一个关注点的模块化，这个关注点可能会横切多个对象。以注解@Aspect的形式放在类上方，声明一个切面。
    Joinpoint：连接点，程序执行过程中的某一行为，即业务层中的所有方法。在程序执行过程中某个特定的点，比如某方法调用的时候或者处理异常的时候都可以是连接点。
    Advice：通知，指切面对于某个连接点所产生的动作，包括前置通知、后置通知、返回后通知、异常通知和环绕通知。
        @Before：在切点之前执行
        @After：在切点方法之后执行
        @AfterReturning：切点方法返回后执行
        @AfterThrowing：切点方法抛异常执行
        @Around：属于环绕增强，能控制切点执行前，执行后，用这个注解后，程序抛异常，会影响@AfterThrowing这个注解。
    Pointcut：切入点，指被拦截的连接点，切入点一定是连接点，但连接点不一定是切入点。
    Proxy：代理，Spring AOP 中有 JDK 动态代理和 CGLib 代理，目标对象实现了接口时采用 JDK 动态代理，反之采用 CGLib 代理。
    Target：代理的目标对象，指一个或多个切面所通知的对象。
    Weaving ：织入，指把增强应用到目标对象来创建代理对象的过程。
AOP相关注解：
    @JoinPoint进行织入操作的程序执行点。
    @pointcut切点注解 可以设置拦截方式
    @Advice 单一横切关注点逻辑的载体，织入到Joinpoint的横切逻辑。
    @Aspect 对横切关注点逻辑进行模块化封装的AOP概念实体，包含多个Pointcut和相关Advice的定义。
    @Before：前置通知，指在某个连接点之前执行的通知。
    @After：后置通知，指某个连接点退出时执行的通知（不论正常返回还是异常退出）。
    @AfterReturning：返回后通知，指某连接点正常完成之后执行的通知，返回值使用returning属性接收。
    @AfterThrowing：异常通知，指方法抛出异常导致退出时执行的通知，和@AfterReturning只会有一个执行，异常使用throwing属性接收。
1.JDK动态代理：利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
2.CGlib动态代理：利用ASM（开源的Java字节码编辑库，操作字节码）开源包，将代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
3.区别：JDK代理只能对实现接口的类生成代理；CGlib是针对类实现代理，对指定的类生成一个子类，并覆盖其中的方法，这种通过继承类的实现方式，不能代理final修饰的类。
AOP 的过程？
    Spring AOP由BeanPostProcessor后置处理器开始，这个后置处理器是一个监听器，可以监听容器触发的Bean生命周期事件，向容器注册后置处理器以后，容器中管理的Bean就具备了接收 IoC 容器回调事件的能力。
        BeanPostProcessor的调用发生在Spring IoC容器完成Bean实例对象的创建和属性的依赖注入后，为Bean对象添加后置处理器的入口是initializeBean方法。
    Spring中JDK动态代理通过JdkDynamicAopProxy调用Proxy的newInstance方法来生成代理类，JdkDynamicAopProxy也实现了InvocationHandler接口，invoke方法的具体逻辑是先获取应用到此方法上的拦截器链，
        如果有拦截器则创建MethodInvocation并调用其proceed方法，否则直接反射调用目标方法。因此Spring AOP对目标对象的增强是通过拦截器实现的。
```
### 9.Spring中Bean的生命周期和bean自动装配【10+】
[spring之bean的生命周期](https://blog.csdn.net/windsunmoon/article/details/44276765)
[JAVA面试题：Spring中bean的生命周期](https://blog.csdn.net/weixin_34174105/article/details/85739351)
[Spring中bean的生命周期详解（面试说辞）](https://blog.csdn.net/knknknkn8023/article/details/107130806)
[Spring Bean各阶段生命周期的介绍](https://www.cnblogs.com/-beyond/p/13188675.html)
![Bean的生命周期](https://img2020.cnblogs.com/blog/848880/202006/848880-20200627155704205-940936200.png)
```markdown
在spring中，从BeanFactory或ApplicationContext取得的实例为Singleton，也就是预设为每一个Bean的别名只能维持一个实例，
    而不是每次都产生一个新的对象使用Singleton模式产生单一实例，对单线程的程序说并不会有什么问题，但对于多线程的程序，就必须注意安全(Thread-safe)的议题，
    防止多个线程同时存取共享资源所引发的数据不同步问题。
在spring中，singleton属性默认是true，只有设定为false，则每次指定别名取得的Bean时都会产生一个新的实例
    一个Bean从创建到销毁，如果是用BeanFactory来生成,管理Bean的话，会经历几个执行阶段:
        1.实例化 Instantiation  createBeanInstance() -> 实例化
        2.属性赋值 Populate     populateBean() -> 属性赋值
        3.初始化 Initialization initializeBean() -> 初始化
        4.销毁 Destruction    销毁阶段是在容器关闭时调用的，在ConfigurableApplicationContext类的close()中
        其中实例化和属性赋值分别对应构造方法和setter方法的注入，初始化和销毁是用户能自定义扩展的两个阶段。
单例对象： singleton
    出生：当容器创建时对象出生
    活着：只要容器还在，对象一直或者
    死亡：容器销毁，对象消亡
    总结：单例对象的生命周期和容器相同
多例对象： prototype
    出生: 使用对象时spring框架为我们创建
    活着：对象只要是在使用过程中就一直活着
    死亡：当对象长时间不用且没有其它对象引用时，由java的垃圾回收机制回收
Spring自动装配bean的方式：
    no:默认值，表示没有自动装配，应使用显式bean引用进行装配。
    byName:根据bean的名称注入对象依赖项
    byType:根据类型注入对象依赖项
    构造函数：通过构造函数来注入依赖项，需要设置大量的参数
    autodetect:容器首先通过构造函数使用autowire装配，如果不能，则通过byType自动装配。
Spring在创建一个Bean时是分为三个步骤的
    - 实例化，可以理解为new一个对象
    - 属性注入，可以理解为调用setter方法完成属性注入
    - 初始化，你可以按照Spring的规则配置一些初始化的方法（例如，@PostConstruct注解）
Bean的生命周期指的就是在上面三个步骤中后置处理器BeanPostprocessor穿插执行的过程。
BeanFactory是Spring的核心--容器，ApplicationContext则是包裹容器的上下文，丰富容器的功能（资源加载，事件驱动等）。FactoryBean也是Spring扩展性的提现。
BeanFactory已经定义了整个的生命周期，子类只是负责实现，demo演示也只是为了证实。我们更应该关注更上层的东西。
ApplicationContext是对容器更精细化的包装，提供了更完善的功能。
FactoryBean是Spring扩展性的提现，可供用户自己定义创建bean。扩展性提炼的很好。
Bean生命周期
    - 实例化Bean：Ioc容器通过获取BeanDefinition对象中的信息进行实例化，实例化对象被包装在BeanWrapper对象中
    - 设置对象属性（DI）：通过BeanWrapper提供的设置属性的接口完成属性依赖注入；
    - 注入Aware接口（BeanFactoryAware， 可以用这个方式来获取其它 Bean，ApplicationContextAware）；
        Spring会检测该对象是否实现了xxxAware接口，并将相关的xxxAware实例注入给bean
    - BeanPostProcessor：自定义的处理器（分前置处理器和后置处理器）对前面所生成的对象进行加工
    - InitializingBean和init-method：执行我们自己定义的初始化方法
    - 使用
    - destroy：bean的销毁
```
### 1.6.Spring容器装配Bean的三种方式
[spring注入bean的几种策略模式](https://www.cnblogs.com/zyjimmortalp/p/12833761.html)
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
BeanDefinition是spring容器创建对象的模板，定义了bean创建的细节。
BeanFactoryPostProcessor可以拿到整个容器对象，当然也能修改BeanDefinition，所以能直接操作bean的创建。
BeanPostProcessor执行的时候bean已经创建完成了，我们可以拿到想要的对象进行干预和设值等操作。
```
### 10.Bean作用域？默认什么级别？是否线程安全？Spring如何保障线程安全的?
```markdown
Bean作用域
    singleton	单例对象，默认值的作用域。SpringIOC容器中只存在一个bean实例，bean以单例模式存在。
    prototype	每次获取都会创建⼀个新的bean实例，即每次getBean()相当于执行new Bean()操作。频繁创建和销毁bean会带来很大的性能开销。
    request	    每⼀次HTTP请求都会产⽣⼀个新的bean，该bean仅在当前HTTP request内有效。
    session	    在一次HTTP session中共享一个bean实例。
    global-session	将对象存入到web项目集群的session域中,若不存在集群,则global session相当于session
默认作用域是singleton，多个线程访问同一个bean时会存在线程不安全问题
Spring中的Bean默认的单例模式，Spring框架并没有对单例bean进行多线程的封装处理，大部分的bean是无状态的，也就是说是线程安全的。
如果bean是有状态的(比如说view model对象)，那么开发者就要去保证线程安全，最简单的就是改变bean的作用域，
把“singleton”改变为“prototype”,这样请求bean相当于new Bean()了，所以就可以保证线程安全了。
    - 有状态就是有数据存储功能，无状态就是不会保存数据。
保障线程安全方法：
    1.在Bean对象中尽量避免定义可变的成员变量（不太现实）。
    2.在类中定义⼀个ThreadLocal成员变量，将需要的可变成员变量保存在ThreadLocal 中
        ThreadLocal：每个线程中都有一个自己的ThreadLocalMap类对象，可以将线程自己的对象保持到其中，各管各的，线程可以正确的访问到自己的对象。
​        将一个共用的ThreadLocal静态实例作为key，将不同对象的引用保存到不同线程的ThreadLocalMap中，
        然后在线程执行的各处通过这个静态ThreadLocal实例的get()方法取得自己线程保存的那个对象，避免了将这个对象作为参数传递的麻烦。
```
### 11.Spring事务隔离级别和事务传播属性【5+】
[【源码讲解】Spring事务是如何应用到你的业务场景中的？](https://www.cnblogs.com/winkin/p/13667568.html)
#### 11.1.Spring事务管理详解
[Spring 事务管理详解](https://www.cnblogs.com/liantdev/p/10149443.html)
```markdown
Spring事务管理为我们提供了三个高层抽象的接口，分别是TransactionProxyFactoryBean，TransactionDefinition，TransactionStatus
Spring事务管理器的接口是org.springframework.transaction.PlatformTransactionManager，Spring框架并不直接管理事务，而是通过这个接口为不同的持久层框架提供了不同的
    PlatformTransactionManager接口实现类，也就是将事务管理的职责委托给Hibernate或者iBatis等持久化框架的事务来实现
TransactionDefinition定义事务基本属性:org.springframework.transaction.TransactionDefinition接口用于定义一个事务，
    它定义了Spring事务管理的五大属性：隔离级别、传播行为、是否只读、事务超时、回滚规则。
事务状态：org.springframework.transaction.TransactionStatus接口用来记录事务的状态，该接口定义了一组方法，用来获取或判断事务的相应状态信息。
Spring 事务管理实现方式
Spring 事务管理有两种方式：编程式事务管理、声明式事务管理
编程式事务管理通过TransactionTemplate手动管理事务，在实际应用中很少使用，我们来重点学习声明式事务管理
声明式事务管理有三种实现方式：基于TransactionProxyFactoryBean的方式、基于AspectJ的XML方式、基于注解的方式
Spring事物的实现方式：
    声明式事物：声明式事物也有两种方式：1.基于xml配置文件的方式和注解方式（@Transaction）
    编码方式：提供编码的形式管理和维护事物
```
#### 11.2.Spring事务的隔离级别
```markdown
在 Spring 事务管理中，为我们定义了如下的隔离级别：
ISOLATION_DEFAULT：使用数据库默认的隔离级别
ISOLATION_READ_UNCOMMITTED：最低的隔离级别，允许读取已改变而没有提交的数据，可能会导致脏读、幻读或不可重复读
ISOLATION_READ_COMMITTED：允许读取事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生
ISOLATION_REPEATABLE_READ：对同一字段的多次读取结果都是一致的，除非数据事务本身改变，可以阻止脏读和不可重复读，但幻读仍有可能发生
ISOLATION_SERIALIZABLE：最高的隔离级别，完全服从ACID的隔离级别，确保不发生脏读、不可重复读以及幻读，也是最慢的事务隔离级别，因为它通常是通过完全锁定事务相关的数据库表来实现的
隔离级别：
    1) DEFAULT （默认）
    这是一个Platfrom TransactionManager默认的隔离级别，**使用数据库默认的事务隔离级别**。另外四个与JDBC的隔离级别相对应。
    2) READ_UNCOMMITTED （读未提交）
    这是事务最低的隔离级别，它允许另外一个事务可以看到这个事务未提交的数据。这种隔离级别会产生脏读，不可重复读和幻像读。
    3) READ_COMMITTED （读已提交）
    保证一个事务修改的数据提交后才能被另外一个事务读取，另外一个事务不能读取该事务未提交的数据。这种事务隔离级别可以避免脏读出现，但是可能会出现不可重复读和幻像读。
    4) REPEATABLE_READ （可重复读）MySQL默认
    这种事务隔离级别可以防止脏读、不可重复读，但是可能出现幻像读。它除了保证一个事务不能读取另一个事务未提交的数据外，还保证了不可重复读。
    5) SERIALIZABLE（串行化）
    这是花费最高代价但是最可靠的事务隔离级别，事务被处理为顺序执行。除了防止脏读、不可重复读外，还避免了幻像读。

```
#### 11.3.Spring事务的传播级别
[Spring事务的传播级别](https://www.cnblogs.com/jack1995/p/13233540.html)
```markdown
传播属性	                            描述
PROPAGATION_REQUIRED	如果当前没有事务，就创建一个事务，如果当前存在事务，就加入该事务。
PROPAGATION_REQUIRED_NEW	当前的方法必须启动新事务，并在它自己的事务内运行，不管是否存着事务，都开启新事务。
PROPAGATION_SUPPORTS	如果当前存在事务，就加入该事务，如果当前不存在事务，就以非事务的方式执行。
PROPAGATION_NOT_SUPPORTED	当前的方法不应该运行在事务中，如果有运行的事务，将它挂起
PROPAGATION_MANDATORY	如果当前存在事务，就加入当前事务，如果当前不存在事务，就抛出异常
PROPAGATION_NEVER	当前的方法不应该运行在事务中，如果当前存在事务，就抛出异常
PROPAGATION_NESTED	如果有事务在运行，当前的方法就应该在这个事务的嵌套事务内运行，否则，就启动一个新的事务，并在它自己的事务内运行。
Spring事务传播属性（Propagation）：
    1) REQUIRED（默认属性）
    如果存在一个事务，则支持当前事务。如果没有事务则开启一个新的事务。 被设置成这个级别时，会为每一个被调用的方法创建一个逻辑事务域。
    如果前面的方法已经创建了事务，那么后面的方法支持当前的事务，如果当前没有事务会重新建立事务。
    2) MANDATORY
    支持当前事务，如果当前没有事务，就抛出异常。
    3) NEVER
    以非事务方式执行，如果当前存在事务，则抛出异常。
    4) NOT_SUPPORTED
    以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
    5) REQUIRES_NEW
    新建事务，如果当前存在事务，把当前事务挂起。
    6) SUPPORTS
    支持当前事务，如果当前没有事务，就以非事务方式执行。
    7) NESTED
    支持当前事务，新增Savepoint点，与当前事务同步提交或回滚。 嵌套事务一个非常重要的概念就是内层事务依赖于外层事务。
    外层事务失败时，会回滚内层事务所做的动作。而内层事务操作失败并不会引起外层事务的回滚。
```
#### 11.4.@Transactional及其错误使用失效场景【5+】
[一个@Transaction哪里来这么多坑？](https://mp.weixin.qq.com/s?__biz=MzU5ODg2Njk4OA==&mid=2247484779&idx=1&sn=2854d0210a65f8beb7c96ef133027632&chksm=febce828c9cb613e50dafd57ee67e5abf5aa3492f9ace748583ce02203bb86f526f69b5a9289&mpshare=1&scene=23&srcid=0829tnZEWo22Np1e0KqU4Dgw&sharer_sharetime=1598715775957&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
```markdown
1.@Transactional在private上：当标记在protected、private、package-visible方法上时，不会产生错误，
    但也不会表现出为它指定的事务配置。可以认为它作为一个普通的方法参与到一个public方法的事务中。
2.@Transactional的事务传播方式配置错误。
3.@Transactional 注解属性rollbackFor设置错误：Spring默认抛出了未检查unchecked异常（继承自 RuntimeException 的异常）或者 Error才回滚事务；其他异常不会触发回滚事务。
4.同一个类中方法调用，导致@Transactional失效：由于使用Spring AOP代理造成的，因为只有当事务方法被当前类以外的代码调用时，才会由Spring生成的代理对象来管理。
    出现了自调用：及未配置事物的方法调用了配置事物的方法，导致配置事物的方法失效。
    解决方案：
        1.自己注入自己，然后显示的调用，
        2.利用AopContext
5.异常被 catch 捕获导致@Transactional失效。
6.数据库引擎不支持事务。
```
### 12.Spring常见注解[10+]
```markdown
Spring部分：
​ 声明bean的注解
​   @Component 通⽤的注解，可标注任意类为 Spring 组件
​   @Service 在业务逻辑层使用（service层）
​   @Repository 在数据访问层使用（dao层）
​   @Controller 在展现层使用，控制器的声明（controller层）
​ 注入bean的注解
​   @Autowired：可以对类成员变量、方法、构造方法进行标注
​   默认按照类型注入，若要按照名称注入，需要搭配@Qualifier注解一起使用
​   @Resource：默认按照名称来装配注入
@autowired和@resource的区别？
    @Autowired：可以对类成员变量、方法、构造方法进行标注。默认按照类型注入，若要按照名称注入，需要搭配@Qualifier注解一起使用
    @Resource：默认按照名称来装配注入
@Autowired的使用简化了我们的开发，其原理是使用AutowiredAnnotationBeanPostProcessor类来实现，该类实现了Spring框架的一些扩展接口，
    通过实现BeanFactoryAware接口使其内部持有了BeanFactory（可轻松的获取需要依赖的的Bean）；通过实现MergedBeanDefinitionPostProcessor扩展接口，
    在BeanFactory里面的每个Bean实例化前获取到每个Bean里面的@Autowired信息并缓存下来；通过实现Spring框架的postProcessPropertyValues扩展接口
    在BeanFactory里面的每个Bean实例后从缓存取出对应的注解信息，获取依赖对象，并通过反射设置到Bean属性里面。
Spring MVC部分：
​   @Controller 声明该类为SpringMVC中的Controller
​   @RequestMapping 用于映射Web请求
​   @ResponseBody 支持将返回值放在response内，而不是一个页面，通常用户返回json数据
​   @RequestBody 允许request的参数在request体中，而不是在直接连接在地址后面。
​   @PathVariable 用于接收路径参数，比如@RequestMapping("/hello/{name}")申明的路径，将注解放在参数中前，即可获取该值，通常作为Restful的接口实现方法。
```
### 13.BeanFactory和ApplicationContext的区别
[为什么大多数IOC容器使用ApplicationContext，而不用BeanFactory](https://www.cnblogs.com/liululee/p/13967437.html)
```markdown
1.BeanFactory是Spring里面最低层的接口，提供了最简单的容器的功能，只提供了实例化对象和拿对象的功能。
2.ApplicationContext应用上下文，继承BeanFactory接口，它是Spring的一各更高级的容器，提供了更多的有用的功能。
    如国际化，访问资源，载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，消息发送、响应机制，AOP等。
3.BeanFactory在启动的时候不会去实例化Bean，中有从容器中拿Bean的时候才会去实例化。
    ApplicationContext在启动的时候就把所有的Bean全部实例化了。它还可以为Bean配置lazy-init=true来让Bean延迟实例化。
    BeanFactory按需加载bean，而ApplicationContext则在启动时加载所有bean。因此，BeanFactory与ApplicationContext相比是轻量级的。
```
### 14.BeanFactory和FactoryBean的区别
[一文读懂BeanFactory和FactoryBean区别](https://www.cnblogs.com/lonecloud/p/13550688.html)
[Spring之BeanFactory和FactoryBean接口的区别](https://www.cnblogs.com/dengpengbo/p/10493782.html)
```markdown
BeanFactory:是所有Spring Bean的容器根接口，给Spring的容器定义一套规范，给IOC容器提供了一套完整的规范，比如我们常用到的getBean方法等。
   1.定义方法 
        getBean(String name): Spring容器中获取对应Bean对象的方法，如存在，则返回该对象
        containsBean(String name)：Spring容器中是否存在该对象
        isSingleton(String name)：通过beanName是否为单例对象
        isPrototype(String name)：判断bean对象是否为多例对象
        isTypeMatch(String name, ResolvableType typeToMatch):判断name值获取出来的bean与typeToMath是否匹配
        getType(String name)：获取Bean的Class类型
        getAliases(String name):获取name所对应的所有的别名
   2.主要的实现类(包括抽象类)
        AbstractBeanFactory：抽象Bean工厂，绝大部分的实现类，都是继承于他
        DefaultListableBeanFactory:Spring默认的工厂类
        XmlBeanFactory：前期使用XML配置用的比较多的时候用的Bean工厂
        AbstractXmlApplicationContext:抽象应用容器上下文对象
        ClassPathXmlApplicationContext:XML解析上下文对象，用户创建Bean对象我们早期写Spring的时候用的就是他
   3.使用方式
        使用ClassPathXmlApplicationContext读取对应的xml文件实例对应上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
        BeanFactory factory = (BeanFactory) context;
FactoryBean:是SpringIOC容器是创建Bean的一种形式，这种方式创建Bean会有加成方式，融合了简单的工厂设计模式于装饰器模式
    FactoryBean接口，可以让用户通过实现该接口来自定义该Bean接口的实例化过程。即包装一层，将复杂的初始化过程包装，让调用者无需关系具体实现细节。
    1.方法
        T getObject()：返回实例
        Class<?> getObjectType();:返回该装饰对象的Bean的类型
        default boolean isSingleton():Bean是否为单例
    2.常用类
        ProxyFactoryBean :Aop代理Bean
        GsonFactoryBean:Gson
    3.使用
        1.Spring XML方式
两者区别：
    1.BeanFactory:负责生产和管理Bean的一个工厂接口，提供一个Spring Ioc容器规范,
    2.FactoryBean: 一种Bean创建的一种方式，对Bean的一种扩展。对于复杂的Bean对象初始化创建使用其可封装对象的创建细节。
```
### 15.Spring中用到了哪些设计模式【10+】
[面试官:“谈谈Spring中都用到了那些设计模式?”](https://github.com/Snailclimb/JavaGuide/blob/master/docs/system-design/framework/spring/Spring-Design-Patterns.md#)
```markdown
单例设计模式 : Spring中的Bean默认都是单例的。提供了全局的访问点BeanFactory；
⼯⼚设计模式 : Spring使⽤⼯⼚模式通过BeanFactory 、ApplicationContext创建bean对象。
代理设计模式 : Spring AOP功能的实现。（1、JDK动态代理。2、CGLib字节码生成技术代理。）
观察者模式： Spring事件驱动模型就是观察者模式很经典的⼀个应⽤。spring中Observer模式常用的地方是listener的实现。如ApplicationListener。
适配器模式：Spring AOP的增强或通知(Advice)使⽤到了适配器模式、spring MVC 中也是⽤到了适配器模式适配 Controller 。
策略模式：Bean的实例化的时候决定采用何种方式初始化bean实例（反射或者CGLIB动态字节码生成）
```
### 16.Spring全局异常捕获如何编写
### 18.Spring的后置处理器
[谈谈Spring中的BeanPostProcessor接口](https://www.cnblogs.com/tuyang1129/p/12866484.html)
```markdown
1.BeanPostProcessor：Bean的后置处理器，主要在bean初始化前后工作。（before和after两个回调中间只处理了init-method）
2.InstantiationAwareBeanPostProcessor：继承于BeanPostProcessor，主要在实例化bean前后工作（TargetSource的AOP创建代理对象就是通过该接口实现）
3.BeanFactoryPostProcessor：Bean工厂的后置处理器，在bean定义(bean definitions)加载完成后，bean尚未初始化前执行。
4.BeanDefinitionRegistryPostProcessor：继承于BeanFactoryPostProcessor。
    其自定义的方法postProcessBeanDefinitionRegistry会在bean定义(bean definitions)将要加载，bean尚未初始化前真执行，
    即在BeanFactoryPostProcessor的postProcessBeanFactory方法前被调用。
```
### 19.spring中拦截器与过滤器的区别【10+】
[过滤器和拦截器有啥区别，这次会了！](https://www.cnblogs.com/summerday152/p/13658788.html)
```markdown
filter定义在web.xml中，拦截器定义在Spring.xml中；
filter必须实现javax.servlet.Filter接口，拦截器需要实现HandleInterceptor接口或者继承抽象类HandlerInterceptorAdapter；
filter只能在servlet前后起作用，拦截器能够深入到方法的前后，异常抛出前后等，更加的细致；
filter是servlet规范规定的，拦截器是Spring容器内的，是Spring框架支持的；
一、实现原理不同
    过滤器的实现基于回调函数，拦截器基于Java的反射机制【动态代理】实现。
二、使用范围不同
    过滤器是Servlet的规范，需要实现javax.servlet.Filter接口，Filter使用需要依赖于Tomcat等容器。
    拦截器是Spring组件，定义在org.springframework.web.servlet包下，由Spring容器管理【又有更加丰富的生缪那个周期处理方法，细粒度，且能够使用Spring中的资源】，不依赖Tomcat等容器。
三、触发时机不同
    过滤器：对请求在进入后Servlet之前或之后进行处理。
    拦截器：对请求在handler【Controller】前后进行处理。
四、执行顺序不同
五、控制执行顺序方式不同
    控制粒度上：过滤器和拦截器都能够实现对请求的拦截功能，但是在拦截的粒度上有较大的差异，拦截器对访问控制的粒度更细。
    使用场景上：拦截器往往用于权限检查、日志记录等，过滤器主要用于过滤请求中无效参数，安全校验。
    依赖容器上：过滤器依赖于Servlet容器，局限于web，而拦截器依赖于Spring框架，能够使用Spring框架的资源，不仅限于web。
    触发时机上：过滤器在Servlet前后执行，拦截器在handler前后执行，现在大多数web应用基于Spring，拦截器更细。    
```
### 20.@autowired 与@resource的区别【5+】
```markdown
1.二者所属包不同
    @Resource：javax.annotation.Resource j2ee
    @Autowired：org.springframework.beans.factory.annotation.Autowired Spring
2.@Autowired默认按类型装配,@Resource默认安照名称进行装配。
    @Autowired默认情况下必须要求依赖对象必须存在，如果要允许null 值，可以设置它的required属性为false。想使用名称装配，可以结合@Qualifier注解进行使用。
    @Resource的名称可以通过name属性进行指定，如果没有指定name属性，当注解写在字段上时，默认取字段名进行按照名称查找。
        如果注解写在setter方法上默认取属性名进行装配。 当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
3.相同：@Autowired与@Resource都可以用来装配bean. 都可以写在字段上,或写在setter方法上。
实践总结：
    1.用@Resource注解在字段上，可不用写setter方法了，因为J2EE的内部注解解析器已经会自动完成setter的功能。而目前@Autowired不行。
    2.可减少与spring的耦合。
```
### 21.SpringBatch
[通过例子讲解Spring Batch入门，优秀的批处理框架](https://www.pkslow.com/archives/spring-batch-introduction)
[大量数据也不在话下，Spring Batch并行处理四种模式初探](https://www.cnblogs.com/larrydpk/p/13664256.html)
```markdown
SpringBatch是一个轻量级的、完善的批处理框架，作为Spring体系中的一员，它拥有灵活、方便、生产可用的特点。
在应对高效处理大量信息、定时处理大量数据等场景十分简便。
Spring Batch的分层架构图如下：
    Application应用层：包含了所有任务batch jobs和开发人员自定义的代码，主要是根据项目需要开发的业务流程等。
    Batch Core核心层：包含启动和管理任务的运行环境类，如JobLauncher等。
    Batch Infrastructure基础层：上面两层是建立在基础层之上的，包含基础的读入reader和写出writer、重试框架等。
```


## 2.Spring源码
[读Spring源码，我们可以从第一行读起](https://blog.csdn.net/qq_41907991/article/details/105667900)
[spring源码](https://www.cnblogs.com/youzhibing/category/958792.html)
[由浅入深详细的介绍Spring框架的原理和源码](https://www.cnblogs.com/binghe001/category/1780611.html)
[当前标签：品Spring](https://www.cnblogs.com/lixinjie/tag/%E5%93%81Spring/)
### 2.1.SpringIOC框架容器核心源码逐步剖析
[Spring Framework框架容器核心源码逐步剖析](https://www.cnblogs.com/jimisun/p/10104002.html)

### 2.2.Spring bean的实例化过程
[Spring 源码学习 - 单例bean的实例化过程](https://www.cnblogs.com/hackingForest/p/13054173.html)
[我该如何学习spring源码以及解析bean定义的注册](https://www.cnblogs.com/liyus/p/10983108.html)
### 2.3.Spring源码分析笔记--AOP
[Spring源码分析笔记--AOP](https://www.cnblogs.com/little-sheep/p/10103797.html)
[SpringAOP+源码解析，切就完事了](https://www.cnblogs.com/summerday152/p/13652903.html)
[Spring拓展接口之FactoryBean，我们来看看其源码实现](https://www.cnblogs.com/youzhibing/p/10528821.html)
### 2.4.Spring源码分析笔记--事务管理
[Spring源码分析笔记--事务管理](https://www.cnblogs.com/little-sheep/p/10115173.html)
[【面试】足够应付面试的Spring事务源码阅读梳理（建议珍藏）](https://www.cnblogs.com/lixinjie/p/a-enough-source-read-of-spring-tx-for-interview.html)
### 2.5.通过ClassLoader了解双亲委派模型
```markdown
protected Class<?> loadClass(String name, boolean resolve) {
     synchronized (getClassLoadingLock(name)) {
         // 首先，检查该类是否已经被加载，如果从JVM缓存中找到该类，则直接返回
         Class<?> c = findLoadedClass(name);
         if (c == null) {
             try {
                 // 遵循双亲委派的模型，首先会通过递归从父加载器开始找，
                 // 直到父类加载器是BootstrapClassLoader为止
                 if (parent != null) {
                     c = parent.loadClass(name, false);
                 } else {
                     c = findBootstrapClassOrNull(name);
                 }
             } catch (ClassNotFoundException e) {}
         if (c == null) {
             // 如果还找不到，尝试通过findClass方法去寻找
             // findClass是留给开发者自己实现的，也就是说
             // 自定义类加载器时，重写此方法即可
            c = findClass(name);
         }
     }
         if (resolve) {
             resolveClass(c);
         }
         return c;
     }
 }
但双亲委派模型并不能解决所有的类加载器问题，比如，Java提供了很多服务提供者接口(Service Provider Interface，SPI)，允许第三方为这些接口提供实现。
```

## 3.Spring注解
### [【Spring注解驱动开发】聊聊Spring注解驱动开发那些事儿！](https://www.cnblogs.com/binghe001/p/13047333.html)
![Spring注解驱动开发](https://img2020.cnblogs.com/blog/1729473/202006/1729473-20200605000243595-700419751.jpg)

### Spring IOC相关常用注解
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
### [聊聊依赖注入注解@Resource和@Autowired](https://www.cnblogs.com/felordcn/p/13063802.html)
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
### [spring下应用@Resource, @Autowired 和 @Inject注解进行依赖注入的差异](https://www.cnblogs.com/both-eyes/p/10096882.html)

### Java配置类 @Configuration
```markdown
@Configuration注释向Spring 表明这是一个配置类，它将为Spring 应用程序上下文提供beans。
配置的类方法带有@Bean注释，指示它们返回的对象应作为beans添加到应用程序上下文中（默认情况下，它们各自的bean IDs将与定义它们的方法的名称相同）。
与基于XML的配置相比，基于Java的配置具有多个优点，包括更高的类型安全性和改进的可重构性。即使这样，仅当Spring无法自动配置组件时，才需要使用Java或XML进行显式配置。
```
### @Conditional 
```markdown
@Conditional注解表示在满足某种条件后才初始化一个bean或者启用某些配置。
它一般用在由@Component、@Service、@Configuration等注解标识的类上面，或者由@Bean标记的方法上。
如果一个@Configuration类标记了@Conditional，则该类中所有标识了@Bean的方法和@Import注解导入的相关类将遵从这些条件。
@Conditional 将会通过 Condition 接口进行条件对比：
public interface Condition {
  boolean matches(ConditionContext ctxt, AnnotatedTypeMetadata metadata);
}
如果matches()方法返回true，那么就会创建带有@Conditional注解的bean。
如果matches()方法返回false，将不会创建这些bean。
```
### Spring @Transactional事务
[Spring中异步注解@Async的使用、原理及使用时可能导致的问题](https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650138502&idx=3&sn=023c3fc5d7799ec0e327018c71673819&chksm=f36bfaa7c41c73b11d5832e85b015f7e2b6b05a41dc189f77d439c21eda43c223530ebf22918&mpshare=1&scene=23&srcid=0805kBPe9hJDcJYXGAkouuIc&sharer_sharetime=1596598705572&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)

## 4.Spring框架应用
[从spring框架中的事件驱动模型出发，优化实际应用开发代码](https://www.cnblogs.com/l3306/p/10757291.html)
[Spring4+Springmvc+quartz实现多线程动态定时调度](https://www.cnblogs.com/alterem/p/11301235.html)
[Spring Cache缓存注解](https://www.cnblogs.com/WangJpBlog/p/13389932.html)


## 5.Spring强化 -- 博客
#### 1.[SpringIoC BeanDefinition的加载和注册](https://www.cnblogs.com/leisurexi/p/12701046.html)
```markdown
SpringIOC容器如何加载Bean的定义信息：
    //声明bean工厂，然后通过指定的XML文件加载bean的定义元信息，最后通过bean工厂获取bean。
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    reader.loadBeanDefinitions("bean-definition.xml");
    User user = beanFactory.getBean("user", User.class);
    System.err.println(user);//User{id=1, name='leisurexi', password='azxcfs', age=18, city=City{id=1, name='beijing'}}
2个核心类DefaultListableBeanFactory和XmlBeanDefinitionReader。
    1.XmlBeanDefinitionReader继承AbstractBeanDefinitionReader#loadBeanDefinitions方法#
        #方法#将资源文件转换为Resource对象，然后调用loadBeanDefinitions(Resource...) 加载 BeanDefinition 。
    2.XmlBeanDefinitionReader#loadBeanDefinitions方法#
        #方法#将Resource封装成EncodedResource ，也就是制定资源的编码和字符集。然后获取Resource的输入流InputStream ，
        并封装成InputSource设置其编码，最终调用doLoadBeanDefinitions开始真正的加载流程。
    3.XmlBeanDefinitionReader#doLoadBeanDefinitions方法#
        #方法#根据inputSource和resource加载XML文件，并封装成Document，用doc去解析和注册bean definition，
    4.XmlBeanDefinitionReader#registerBeanDefinitions方法#
        #方法#获取到本次注册的数量
    5.DefaultBeanDefinitionDoucumentReader#registerBeanDefinitions方法#
        #方法#提取root，注册BeanDefinition
```
#### 2.[SpringIoC bean的加载](https://www.cnblogs.com/leisurexi/p/13194515.html)
>> beanFactory.getBean() 方法就获取了在XML中定义的bean。
```markdown
FactoryBean的特殊之处在于它可以向容器中注册两个bean，一个是它本身，一个是FactoryBean.getObject() 法返回值所代表的bean。
beanName前面加上&获取的是FactoryBean本身，不加获取的getObject()返回的对象。
bean的加载
   1.AbstractBeanFactory#getBean方法# 
        getBean调用doGetBean方法(方法以do开头实际做操作的方法)，根据bean的名称和类型返回bean实例。
            1.转换对应的beanName->AbstractBeanFactory#transformedBeanName#
            2.尝试从单例缓存获取 bean->AbstractBeanFactory#getSingleton#
            3.获取 bean 实例对象->AbstractBeanFactory#getObjectForBeanInstance#
            4.合并 bean 定义元信息->AbstractBeanFactory#getMergedLocalBeanDefinition#
                主要是获取 MergedBeanDefinition ，主要步骤如下：
                1.首先从缓存中获取bean的MergedBeanDefinition，如果存在并且未过期直接返回。
                2.不存在或者已过期的MergedBeanDefinition，获取已经注册的BeanDefinition去作为顶级bean合并。
                3.bean没有parent(就是XML中的parent属性)，直接封装成RootBeanDefinition 。
                4.bean有parent，先去获取父MergedBeanDefinition，然后覆盖和合并与parent相同的属性。
                注意：这里只有abstract、scope、lazyInit、autowireMode、dependencyCheck、dependsOn 、factoryBeanName、factoryMethodName、
                initMethodName、destroyMethodName会覆盖，而constructorArgumentValues、propertyValues、methodOverrides 会合并。
                5.如果没有设置作用域，默认作用域为singleton 。
                6.缓存MergedBeanDefinition 。
            5.寻找依赖->DefaultSingletonBeanRegistry#isDependent#
                     ->DefaultSingletonBeanRegistry#registerDependentBean#
            6.创建和注册单例 bean->DefaultSingletonBeanRegistry#getSingleton#
getBean()方法流程，我们可以重新梳理一下思路：
    1.获取bean实际名称，如果缓存中存在直接取出实际bean返回。
    2.缓存中不存在，判断当前工厂是否有BeanDefinition，没有递归去父工厂创建bean。
    3.合并BeanDefinition，如果depends-on不为空，先去初始化依赖的bean。
    4.如果bean的作用域是单例，调用createBean()方法创建实例，这个方法会执行bean的其它生命周期回调，以及属性赋值等操作；
        接着执行单例bean创建前后的生命周期回调方法，并放入singletonObjects缓存起来。
    5.如果bean的作用域是原型，调用createBean()方法创建实例，并执行原型bean前后调用生命周期回调方法。
    6.如果bean的作用域是自定义的，获取对应的Scope对象，调用重写的get()方法获取实例，并执行原型bean前后调用生命周期回调方法。
    7.最后检查所需的类型是否与实际bean实例的类型匹配，如果不等进行转换，最后返回实例。
```
#### 3.[SpringIoC bean的创建](https://www.cnblogs.com/leisurexi/p/13196998.html)
```markdown
本篇文章主要介绍Spring IoC容器是怎么创建bean的实例。
if (mbd.isSingleton()) {
    // 创建和注册单例 bean
    sharedInstance = getSingleton(beanName, () -> {
        try {
            // 创建 bean 实例
            return createBean(beanName, mbd, args);
        }
       	// 省略异常处理...
    });
    bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
}
如果bean的作用域是单例，会调用getSingleton()方法并传入beanName和ObjectFactory作为参数；
而getSingleton()方法会调用ObjectFactory的getObject()方法也就是上面代码中的createBean()方法，返回bean。
这里的 ObjectFactory 是 bean 的延迟依赖查找接口，定义如下：
    @FunctionalInterface
    public interface ObjectFactory<T> {
        T getObject() throws BeansException;
    }
只有在调用getObject()方法时才会真正去获取bean。下面我们正式开始分析createBean()方法。
    1.调用createBean()->AbstractAutowireCapableBeanFactory#createBean#
    2.bean实例化前置处理->InstantiationAwareBeanPostProcessor接口继承于BeanPostProcessor
        BeanPostProcessor中定义了bean的初始化阶段生命周期回调方法，会在后续介绍）提供了三个扩展点，如下：
             bean 实例化前
             bean 实例化后
             bean 属性赋值前
             这也是bean实例化阶段的生命周期回调方法。
        判断bean在实例化之前是否已经解析过->AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation#
    3.创建bean->AbstractAutowireCapableBeanFactory#doCreateBean#
        1).创建bean的实例->AbstractAutowireCapableBeanFactory#createBeanInstance#
        2).默认无参构造器实例化bean->AbstractAutowireCapableBeanFactory#instantiateBean#
        3).寻找合适的构造器实例化bean->ConstructorResolver#autowireConstructor#
            解析构造函数参数->ConstructorResolver#resolveConstructorArguments#
            创建参数数组->ConstructorResolver#createArgumentArray#判断构造函数如果有匹配的参数会转换成对应类型，如果没有匹配的参数，
                多半是构造函数自动注入，通过resolveAutowiredArgument()去查找bean并返回实例。
                ConstructorResolver#resolveAutowiredArgument#
        4).依赖解决->DefaultListableBeanFactory#resolveDependency#
            DefaultListableBeanFactory#doResolveDependency#
        5).BeanDefinition合并后处理->AbstractAutowireCapableBeanFactory#applyMergedBeanDefinitionPostProcessors#
            拿到所有注册的BeanPostProcessor，然后遍历判断是否是MeragedBeanDefinitionPostProcessor类型，是的话进行BeanDefinition合并后的方法回调，
            在这个回调方法内你可以对指定bean的BeanDefinition做一些修改。
本文主要介绍了创建bean实例的流程，我们可以重新梳理一下思路：
    1.进行bean的实例化前方法回调，如果返回非空，跳过后面步骤
    2.创建bean的实例，如果是构造函数注入会选择最适合的构造函数进行参数自动注入，否则调用默认的无参构造进行实例化bean。
```
#### 4.[SpringIoC 循环依赖的处理](https://www.cnblogs.com/leisurexi/p/13199726.html)
```markdown
什么是循环依赖?循环依赖就是循环引用，就是两个或多个bean相互之间的持有对方，比如A引用B，B引用A。
Spring IoC容器对循环依赖的处理有三种情况：
    1.构造器循环依赖：此依赖Spring无法处理，直接抛出BeanCurrentlylnCreationException异常。
    2.单例作用域下的setter循环依赖：此依赖Spring通过三级缓存来解决。
    3.非单例的循环依赖：此依赖Spring无法处理，直接抛出BeanCurrentlylnCreationException异常。
构造器循环依赖：
    getSingleton()方法会判断是否是第一次创建该bean，如果是第一次会先去创建bean，也就是调用ObjectFacoty的getObject()方法，
        即调用createBean()方法创建bean前，会先将当前正要创建的bean记录在缓存singletonsCurrentlyInCreation中。
setter循环依赖：
    在创建A时发现依赖B，便先去创建B；B在创建时发现依赖A，此时A因为是通过构造函数创建，所以没创建完，便又去创建A，
        发现A存在于singletonsCurrentlyInCreation，即正在创建中，便抛出BeanCurrentlylnCreationException异常。
非单例循环依赖：
    对于非单例的bean，Spring容器无法完成依赖注入，因为Spring容器不进行缓存，因此无法提前暴露一个创建中的bean。
```
#### 5.[搞懂Spring代理创建及AOP链式调用过程](https://www.cnblogs.com/yewy/p/13199260.html)
```markdown
AOP也就是面向切面编程，它可以将公共的代码抽离出来，动态的织入到目标类、目标方法中，大大提高我们编程的效率，也使程序变得更加优雅。
如事务、操作日志等都可以使用AOP实现。这种织入可以是在运行期动态生成代理对象实现，也可以在编译期、类加载时期静态织入到代码中。
而Spring正是通过第一种方法实现，且在代理类的生成上也有两种方式：JDK Proxy和CGLIB，默认当类实现了接口时使用前者，否则使用后者；另外Spring AOP只能实现对方法的增强。
    AOP的术语很多，虽然不清楚术语我们也能很熟练地使用AOP，但是要理解分析源码，术语就需要深刻体会其含义。
        增强（Advice）：就是我们想要额外增加的功能
        目标对象（Target）：就是我们想要增强的目标类，如果没有AOP，我们需要在每个目标对象中实现日志、事务管理等非业务逻辑
        连接点（JoinPoint）：程序执行时的特定时机，如方法执行前、后以及抛出异常后等等。
        切点（Pointcut）：连接点的导航，我们如何找到目标对象呢？切点的作用就在于此，在Spring中就是匹配表达式。
        引介（Introduction）：引介是一种特殊的增强，它为类添加一些属性和方法。这样，即使一个业务类原本没有实现某个接口，
            通过AOP的引介功能，我们可以动态地为该业务类添加接口的实现逻辑，让业务类成为这个接口的实现类。
        织入（Weaving）：即如何将增强添加到目标对象的连接点上，有动态（运行期生成代理）、静态（编译期、类加载时期）两种方式。
        代理（Proxy）：目标对象被织入增强后，就会产生一个代理对象，该对象可能是和原对象实现了同样的一个接口（JDK），也可能是原对象的子类（CGLIB）。
        切面（Aspect、Advisor）：切面由切点和增强组成，包含了这两者的定义。
```
##