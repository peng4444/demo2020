# SpringMVC学习
  [手码两万余字，SpringMVC 包教包会](https://www.cnblogs.com/lenve/p/12100698.html)
  
  [SpringMVC源码学习：容器初始化+MVC初始化+请求分发处理+参数解析+返回值解析+视图解析](https://www.cnblogs.com/summerday152/p/12856338.html)
  
  [随笔分类 - SpringMVC](https://www.cnblogs.com/xinhudong/category/1150740.html)
  
[TOC]

## SpringMVC基础
>> Spring MVC的核心是控制器的概念，这是一个处理请求并使用某种信息进行响应的类。
>> 对于面向浏览器的应用程序，控制器的响应方式是可选地填充模型数据并将请求传递给视图，以生成返回给浏览器的HTML。
### 1. SpringMVC简介
  ```markdown
MVC即模型-视图-控制器（Model-View-Controller）
Spring Web MVC是一种基于Java的实现了Web MVC设计模式的请求驱动类型的轻量级Web框架，即使用了MVC架构模式的思想，
将web层进行职责解耦，基于请求驱动指的就是使用请求-响应模型，框架的目的就是帮助我们简化开发，SpringWebMVC也是要简化我们日常Web开发的。
  ```
### 2. SpringMVC运行原理
![SpringMVC运行原理步骤](https://img2018.cnblogs.com/blog/1363940/201910/1363940-20191031231647804-398588825.png)
  ```markdown
（1）：用户请求发送给DispatcherServlet，DispatcherServlet调用HandlerMapping处理器映射器；
（2）：HandlerMapping根据xml或注解找到对应的处理器，生成处理器对象返回给DispatcherServlet；
（3）：DispatcherServlet会调用相应的HandlerAdapter；
（4）：HandlerAdapter经过适配调用具体的处理器去处理请求，生成ModelAndView返回给DispatcherServlet
（5）：DispatcherServlet将ModelAndView传给ViewReslover解析生成View返回给DispatcherServlet；
（6）：DispatcherServlet根据View进行渲染视图；
->DispatcherServlet->HandlerMapping->Handler->DispatcherServlet->HandlerAdapter处理handler->ModelAndView
->DispatcherServlet->ModelAndView->ViewReslover->View ->DispatcherServlet->返回给客户
  ```
### 3. 常用注解
  ```markdown
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