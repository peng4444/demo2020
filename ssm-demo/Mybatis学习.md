# Mybatis学习
## Mybatis基础
```markdown
Mybatis原理
- sqlsessionFactoryBuilder生成sqlsessionFactory（单例）
- 工厂模式生成sqlsession执行sql以及控制事务
- Mybatis通过动态代理使Mapper（sql映射器）接口能运行起来即为接口生成代理对象将sql查询到结果映射成pojo
sqlSessionFactory构建过程
- 解析并读取配置中的xml创建Configuration对象 （单例）
- 使用Configruation类去创建sqlSessionFactory（builder模式）
Mybatis一级缓存与二级缓存
默认情况下一级缓存是开启的，而且是不能关闭的。
- 一级缓存是指SqlSession级别的缓存 原理：使用的数据结构是一个map，如果两次中间出现commit操作 （修改、添加、删除），本sqlsession中的一级缓存区域全部清空
- 二级缓存是指可以跨SqlSession的缓存。是mapper级别的缓存；原理：是通过CacheExecutor实现的。CacheExecutor其实是Executor的代理对象
```
[随笔分类-Mybatis](https://www.cnblogs.com/zwwhnly/category/1492402.html) 

[MyBatis框架的使用及源码分析](https://www.cnblogs.com/zsg88/category/1080098.html)  

[mybatis源码](https://www.cnblogs.com/sanzao/tag/mybatis/) 

[mybatis源码]( https://www.cnblogs.com/java-chen-hao/category/1576447.html )
## Mybatis相关博客
[mybatis是怎样炼成的](https://www.cnblogs.com/roytian/p/12762218.html)
[mybatis缓存之一级缓存（一）](https://www.cnblogs.com/zhenghengbin/p/13193999.html)
[mybatis源码学习：一级缓存和二级缓存分析](https://www.cnblogs.com/summerday152/p/12773135.html)

#### 1.Mybatis SQL如何执行
[面试官问你MyBatis SQL是如何执行的？把这篇文章甩给他](https://www.cnblogs.com/cxuanBlog/p/12248536.html)
[MyBatis执行流程的各阶段介绍](https://www.cnblogs.com/-beyond/p/13232624.html)
##### Mybatis的特点
```markdown
1.解除SQL与程序代码的耦合，通过提供 DAO层，将业务逻辑和数据访问逻辑分离，使系统的设计更清晰，更易维护，更易单元测试。SQL和代码的分离，提高了可维护性。
2.MyBatis 比较简单和轻量： 只要通过配置 jar 包，或者如果你使用 Maven 项目的话只需要配置 Maven 以来就可以。
3.屏蔽样板代码 MyBatis: 回屏蔽原始的 JDBC 样板代码，让你把更多的精力专注于 SQL 的书写和属性-字段映射上。
4.编写原生 SQL，支持多表关联 MyBatis 最主要的特点就是你可以手动编写 SQL 语句，能够支持多表关联查询。
    提供映射标签，支持对象与数据库的 ORM 字段关系映射
    提供 XML 标签，支持编写动态 SQL。
```
####
###
[Mybatis学习总结（一）](https://blog.csdn.net/Marmara01/article/details/88878280)
[mybatis探究之延迟加载和缓存](https://www.cnblogs.com/liyier/p/12516626.html)
[MyBatis缓存机制（一级缓存，二级缓存）](https://www.cnblogs.com/ljl150/p/12918581.html)
[MyBatis 开发手册](https://www.cnblogs.com/ZhuChangwu/p/11734347.html)
[mybatis的插件机制](https://www.cnblogs.com/qm-article/p/11785350.html)
[深入理解Mybatis插件](https://www.cnblogs.com/heartlake/p/12855653.html)

[Mybatis通用分页插件](https://github.com/pagehelper/Mybatis-PageHelper)

[手把手教你如何玩转插件：分页插件（Pagehelper）](https://blog.csdn.net/cs_hnu_scw/article/details/80718467)


[[面试官：你分析过mybatis工作原理吗？](https://www.cnblogs.com/almm/p/11254403.html)]

[springboot2 + mybatis 多种方式实现多数据配置](https://www.cnblogs.com/song27/p/12595603.html)

[持久层框架JPA与Mybatis该如何选型](https://www.cnblogs.com/zimug/p/11790285.html)

[Mybatis 强大的结果集映射器resultMap](https://www.cnblogs.com/felordcn/p/12945815.html)
```markdown

```
[抛开 Spring ，你知道 MyBatis 加载 Mapper 的底层原理吗？](https://www.cnblogs.com/Howinfun/p/12973902.html)

[天哪！手动编写mybatis雏形竟然这么简单](https://www.cnblogs.com/quellanan/p/13055000.html)
