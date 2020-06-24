# Spring学习
## 一、Spring 
### 1.Spring基础知识
[关于Spring AOP，除了动态代理、CGLIB，你还知道什么？](https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650128800&idx=3&sn=3a3e9806348f3b280bdc5ee725238a20&chksm=f36bdc81c41c5597d0e206d637dd7bd986886212e38ae69fd506cc8488ab98bac5698204e9bc&mpshare=1&scene=23&srcid=&sharer_sharetime=1590070530809&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)

[SpringBoot切面Aop的demo简单讲解](https://www.cnblogs.com/xuwujing/p/12927081.html)

[Spring IOC的核心机制：实例化与注入](https://www.cnblogs.com/zyjimmortalp/p/12828726.html)

[重新认识 Spring IOC](https://www.cnblogs.com/i-code/p/12832545.html)

[spring注入bean的几种策略模式](https://www.cnblogs.com/zyjimmortalp/p/12833761.html)

[Spring中资源的加载原来是这么一回事啊！](https://www.cnblogs.com/i-code/p/12845329.html)

[Spring 循环引用(三)源码深入分析版](https://www.cnblogs.com/burg-xun/p/12865205.html)

[一文读懂Spring中的DI和AOP](cnblogs.com/xiaoyao2011/p/12866456.html)

[谈谈Spring中的BeanPostProcessor接口](https://www.cnblogs.com/tuyang1129/p/12866484.html)

### 2.Spring源码
[读Spring源码，我们可以从第一行读起](https://blog.csdn.net/qq_41907991/article/details/105667900)

【随笔分类 - spring源码】[spring源码]()https://www.cnblogs.com/youzhibing/category/958792.html

[当前标签：品Spring](https://www.cnblogs.com/lixinjie/tag/%E5%93%81Spring/)

[Spring 源码学习 - 单例bean的实例化过程](https://www.cnblogs.com/hackingForest/p/13054173.html)
### 3.Spring注解
#### [【Spring注解驱动开发】聊聊Spring注解驱动开发那些事儿！](https://www.cnblogs.com/binghe001/p/13047333.html)
![Spring注解驱动开发](https://img2020.cnblogs.com/blog/1729473/202006/1729473-20200605000243595-700419751.jpg)
```markdown

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
[[Spring框架使用@Autowired自动装配引发的讨论](https://www.cnblogs.com/ibigboy/p/11236729.html)]

[[Spring Boot @Condition 注解，组合条件你知道吗](https://www.cnblogs.com/FraserYu/p/11280420.html)]

[[Spring中重要的注解](https://www.cnblogs.com/rolandlee/p/11014923.html)]

[精进Spring—Spring常用注解](https://blog.csdn.net/u010648555/article/details/76299467)

##
[[我该如何学习spring源码以及解析bean定义的注册](https://www.cnblogs.com/liyus/p/10983108.html)]
[[从spring框架中的事件驱动模型出发，优化实际应用开发代码](https://www.cnblogs.com/l3306/p/10757291.html)]
[[AVA-Spring AOP五大通知类型](https://www.cnblogs.com/xiaoluohao/p/11286242.html)]
[[Spring4+Springmvc+quartz实现多线程动态定时调度](https://www.cnblogs.com/alterem/p/11301235.html)]
