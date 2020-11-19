# SpringMVC学习
[手码两万余字，SpringMVC 包教包会](https://www.cnblogs.com/lenve/p/12100698.html)
[SpringMVC源码学习：容器初始化+MVC初始化+请求分发处理+参数解析+返回值解析+视图解析](https://www.cnblogs.com/summerday152/p/12856338.html)
[随笔分类 - SpringMVC](https://www.cnblogs.com/xinhudong/category/1150740.html)
[springmvc的面试知识点总结](https://www.cnblogs.com/yunjiandubu/p/10269713.html)
[SpringMVC系列之SpringMVC快速入门 MVC设计模式介绍+什么是SpringMVC+ SpringMVC的作用及其基本使用+组件解析+注解解析](https://www.cnblogs.com/pjhaymy/p/13782927.html)
  
[TOC]


## SpringMVC基础

### 1.简单谈谈你对MVC的理解。
```markdown
mvc是一种设计模式（设计模式就是日常开发中编写代码的一种好的方法和经验的总结）。MVC即模型-视图-控制器（Model-View-Controller）
模型（model）-视图（view）-控制器（controller），三层架构的设计模式。
用于实现前端页面的展现与后端业务数据处理的分离。
mvc设计模式的好处
    1.分层设计，实现了业务系统各个组件之间的解耦，有利于业务系统的可扩展性，可维护性。
    2.有利于系统的并行开发，提升开发效率。
Spring MVC的核心是控制器的概念，这是一个处理请求并使用某种信息进行响应的类。
对于面向浏览器的应用程序，控制器的响应方式是可选地填充模型数据并将请求传递给视图，以生成返回给浏览器的HTML。
Spring Web MVC是一种基于Java的实现了Web MVC设计模式的请求驱动类型的轻量级Web框架，即使用了MVC架构模式的思想，
将web层进行职责解耦，基于请求驱动指的就是使用请求-响应模型，框架的目的就是帮助我们简化开发，SpringWebMVC也是要简化我们日常Web开发的。
```
### 2.什么是 SpringMVC？SpringMVC的优点?
```markdown
SpringMVC是一个基于Java的实现了MVC设计模式的请求驱动类型的轻量级Web框架，通过把模型-视图-控制器分离，
将web层进行职责解耦，把复杂的web应用分成逻辑清晰的几部分，简化开发，减少出错，方便组内开发人员之间的配合。
Spring MVC的优点
（1）可以支持各种视图技术,而不仅仅局限于JSP；
（2）与Spring框架集成（如IoC容器、AOP等）；
（3）清晰的角色分配：前端控制器(dispatcherServlet),请求到处理器映射（handlerMapping),处理器适配器（HandlerAdapter),视图解析器（ViewResolver）。
（4）支持各种请求资源的映射策略。
```
### 3.SpringMVC的核心组件有哪些？【5+】
```markdown
（1）前端控制器 DispatcherServlet（不需要程序员开发）
    作用：接收请求、响应结果，相当于转发器，有了DispatcherServlet 就减少了其它组件之间的耦合度。
（2）处理器映射器HandlerMapping（不需要程序员开发）
    作用：根据请求的URL来查找Handler
（3）处理器适配器HandlerAdapter
    注意：在编写Handler的时候要按照HandlerAdapter要求的规则去编写，这样适配器HandlerAdapter才可以正确的去执行Handler。
（4）处理器Handler（需要程序员开发）
（5）视图解析器 ViewResolver（不需要程序员开发）
    作用：进行视图的解析，根据视图逻辑名解析成真正的视图（view）
（6）视图View（需要程序员开发jsp）
    View是一个接口，它的实现类支持不同的视图类型（jsp，freemarker，pdf等等）
```
### 4.springmvc的工作流程?SpringMVC执行原理【5+】
![](https://img-blog.csdnimg.cn/20200208211439106.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoaW5rV29u,size_16,color_FFFFFF,t_70)
[浅谈SpringMVC执行过程](https://www.cnblogs.com/wangjiming/p/10487832.html)
![SpringMVC运行原理步骤](https://img2018.cnblogs.com/blog/1363940/201910/1363940-20191031231647804-398588825.png)
```markdown
（1）用户发送请求至前端控制器DispatcherServlet；
（2）DispatcherServlet收到请求后，调用HandlerMapping处理器映射器，找到处理请求的Controller；
（3）处理器映射器根据请求url找到具体的Controller，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet；
（4）DispatcherServlet调用HandlerAdapter处理器适配器；
（5）HandlerAdapter 经过适配调用 具体处理器(Handler，也叫后端控制器)；
（6）Handler执行完成返回ModelAndView；
（7）HandlerAdapter将Handler执行结果ModelAndView返回给DispatcherServlet；
（8）DispatcherServlet将ModelAndView传给ViewResolver视图解析器进行解析；
（9）ViewResolver解析后返回具体View；
（10）DispatcherServlet对View进行渲染视图（即将模型数据填充至视图中）
（11）DispatcherServlet响应用户。
->DispatcherServlet->HandlerMapping->Handler->DispatcherServlet->HandlerAdapter处理handler->ModelAndView
->DispatcherServlet->ModelAndView->ViewReslover->View ->DispatcherServlet->返回给客户
```
### 5.springmvc dispatcherservlet与handleradapter如何关联？
```markdown
DispatcherServlet拦截到请求之后,根据handlerMapping获取到handlerExecutionChain对象,然后,
在handlerExecutionChain对象不为null的情况下,根据handlerExecutionChain中的handler对象获取HandlerAdapter对象;
```
### 6.有了springmvc的IOC容器,还需要spring的IOC容器吗?为什么?如何处理兼容问题?
```markdown
这个问题再进一步,换个方式提问即是否还需要在web.xml方法里面配置启动springIOC容器的ContextLoaderListener?
可以从正反两方面来回答,首先是需要,通常情况下,类似于数据源,事务,整合其他框架等都是放在spring的配置文件中,而不是springmvc的配置文件中, 
一般情况下,开发过程中的Service,Dao也都是放在spring的IOC容器当中;其次可以是不需要的,也可以都放在springmvc的配置文件当中,
当然需要分多个spring的配置文件,然后通过import节点导入到springmvc的配置文件当中.
几点要注意的地方:
1. springIOC容器和springmvc的IOC容器 扫描的部分有重合的地方,就会导致bean被创建2次,解决方案:使用 exclude-filter和include-filter配合
    来区分哪些bean交给springIOC容器,哪些bean交给springmvcIOC容器;
2. springmvc IOC容器里面的bean可以引用springIOC容器的bean,反之则不行,多个springIOC 容器之间可以设置为父子关系,以实现良好的解耦.
举个栗子,springIOC容器好比是全局作用域,springmvcIOC容器好比是局部作用域,局部的可以引用全局的,而全局的却不能引用局部的.
```
### 8.SpringMVC怎么样设定重定向和转发的？
```markdown
（1）转发：在返回值前面加"forward:"，譬如"forward:user.do?name=method4"
（2）重定向：在返回值前面加"redirect:"，譬如"redirect:http://www.baidu.com"
```
### 9.如何解决POST请求和GET请求的中文乱码问题？
```markdown
（1）解决post请求乱码问题：
在web.xml中配置一个CharacterEncodingFilter过滤器，设置成utf-8；
（2）get请求中文参数出现乱码解决方法有两个：
①修改tomcat配置文件添加编码与工程编码一致，如下：
<ConnectorURIEncoding="utf-8" connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>
②另外一种方法对参数进行重新编码：
String userName = new String(request.getParamter(“userName”).getBytes(“ISO8859-1”),“utf-8”)
ISO8859-1是tomcat默认编码，需要将tomcat编码后的内容按utf-8编码。
```
### 10. @ModelAttribute注解应该如何使用？
[@ModelAttribute注解的使用总结](https://blog.csdn.net/leo3070/article/details/81046383)
```markdown
@ModelAttribute最主要的作用是将数据添加到模型对象中，用于视图页面展示时使用。
@ModelAttribute等价于model.addAttribute("attributeName", abc);
但是根据@ModelAttribute注释的位置不同，和其他注解组合使用，致使含义有所不同。
1.@ModelAttribute注释方法 
    （1）@ModelAttribute注释void返回值的方法 
    （2）@ModelAttribute注释返回具体类的方法
    （3）@ModelAttribute(value="")注释返回具体类的方法
    （4）@ModelAttribute和@RequestMapping同时注释一个方法
2.@ModelAttribute注释一个方法的参数 
    （1）从model中获取
    （2）从Form表单或URL参数中获取（实际上，不做此注释也能拿到user对象）
3.@ModelAttribute注释一个方法的返回值
    
```
### 11. 说说你对自定义数据类型转换器的理解？
[springmvc自定义类型转换器Converter](https://blog.csdn.net/qq_43364241/article/details/90296580)
```markdown

```
### 12.使用Hibernate Validator注解方式校验Email数据格式应该怎么写？
[Hibernate Validator验证注解说明](https://blog.csdn.net/fighterandknight/article/details/72186260)
### 13.Spring MVC常用注解？当接收一个对象，对某些字段加校验，非空校验，长度校验？
```markdown
@Controller：在类定义处添加，将类交给IoC容器管理。
@RequestMapping：将URL请求和业务方法映射起来，在类和方法定义上都可以添加该注解。value属性指定URL请求的实际地址，是默认值。
    method属性限制请求的方法类型，包括GET、POST、PUT、DELETE等。如果没有使用指定的请求方法请求URL，会报405 Method Not Allowed 错误。params属性限制必须提供的参数，如果没有会报错。
@RequestParam：如果Controller方法的形参和URL 参数名一致可以不添加注解，如果不一致可以使用该注解绑定。
    value属性表示HTTP请求中的参数名。required属性设置参数是否必要，默认false。defaultValue 属性指定没有给参数赋值时的默认值。
@PathVariable：Spring MVC支持RESTful风格URL，通过@PathVariable完成请求参数与形参的绑定。
@Controller负责注册一个bean到spring上下文中
@RequestMapping注解为控制器指定可以处理哪些URL请求
@RequestBody注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，
  然后把相应的数据绑定到要返回的对象上 ,再把HttpMessageConverter返回的对象数据绑定到controller中方法的参数上
@ResponseBody注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区
@ModelAttribute在方法定义上使用 @ModelAttribute注解：SpringMVC在调用目标处理方法前，会先逐个调用在方法级上标注了@ModelAttribute的方法，
  在方法的入参前使用@ModelAttribute注解：可以从隐含对象中获取隐含的模型数据中获取对象，再将请求参数 –绑定到对象中，再传入入参将方法入参对象添加到模型中
@RequestParam在处理方法入参处使用@RequestParam可以把请求参 数传递给请求方法
@PathVariable绑定URL占位符到入参
@ExceptionHandler注解到方法上，出现异常时会执行该方法
@ControllerAdvice使一个Controller成为全局的异常处理类，类中用@ExceptionHandler方法注解的方法可以处理所有Controller发生的异常
  ```
### 14.SpringMVC的Controller是如何将参数和前端传来的数据一一对应的。
### 15.@RequestMapping的作用
```markdown
将HTTP请求映射到相应的类/方法上
```
### 16.@Autowired的作用
```markdown
@Autowired可以对类成员变量，方法及构造函数进行标注，完成自动装配的工作，通过@Autowired的使用来消除set/get方法。
```
### 17.Servlet
[Java Web(一) Servlet详解！！](https://www.cnblogs.com/whgk/p/6399262.html)

### 4.总结SpringMVC请求参数接收
[总结SpringMVC请求参数接收](https://www.cnblogs.com/throwable/p/13302991.html)
```markdown
SpringMVC中处理控制器参数的接口是HandlerMethodArgumentResolver，此接口有众多子类，分别处理不同(注解类型)的参数，
下面只列举几个子类：
    RequestParamMethodArgumentResolver：解析处理使用了@RequestParam注解的参数、MultipartFile类型参数和Simple类型(如long、int等类型)参数。
    RequestResponseBodyMethodProcessor：解析处理@RequestBody注解的参数。
    PathVariableMapMethodArgumentResolver：解析处理@PathVariable注解的参数。
实际上，一般在解析一个控制器的请求参数的时候，用到的是HandlerMethodArgumentResolverComposite，里面装载了所有启用的HandlerMethodArgumentResolver子类。
HandlerMethodArgumentResolver子类在解析参数的时候使用到HttpMessageConverter（实际上也是一个列表，进行遍历匹配解析）子类进行匹配解析，
常见的如MappingJackson2HttpMessageConverter（使用Jackson进行序列化和反序列化）。
JSON参数
    一般来说，直接在POST请求中的请求体提交一个JSON字符串这种方式对于SpringMVC来说是比较友好的，
    只需要把Content-Type设置为application/json，然后直接上传一个原始的JSON字符串即可，控制器方法参数使用@RequestBody注解处理：
请求头
    请求头的值主要通过@RequestHeader注解的参数获取，参数处理器是RequestHeaderMethodArgumentResolver，需要在注解中指定请求头的Key。
Cookie
    Cookie的值主要通过@CookieValue注解的参数获取，参数处理器为ServletCookieValueMethodArgumentResolver，需要在注解中指定Cookie的Key。
Model类型参数
    Model类型参数的处理器是ModelMethodProcessor，实际上处理此参数是直接返回ModelAndViewContainer实例中的Model（具体是ModelMap类型），
    因为要桥接不同的接口和类的功能，因此回调的实例是BindingAwareModelMap类型，此类型继承自ModelMap同时实现了Model接口。
@ModelAttribute参数
    @ModelAttribute注解处理的参数处理器为ModelAttributeMethodProcessor，@ModelAttribute的功能源码的注释如下：
```
### SpringMVC源码

##
[SpringMVC+Mybatis 如何配置多个数据源并切换？](https://www.cnblogs.com/haha12/p/10613549.html)
[SpringMVC之文件上传](https://www.cnblogs.com/huskysir/p/13170179.html)