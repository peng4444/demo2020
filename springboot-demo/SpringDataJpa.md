# Spring Data Jpa学习
>> [干货|一文读懂 Spring Data Jpa！](https://www.cnblogs.com/lenve/p/10640472.html)

## 一,JPA
### 1. JPA是什么?
```markdown
1.Java Persistence API：用于对象持久化的 API
2.Java EE 5.0 平台标准的 ORM 规范，使得应用程序以统一的方式访问持久层
```
### 2. JPA和Hibernate的关系
```markdown
1.JPA 是 Hibernate的一个抽象（就像JDBC和JDBC驱动的关系）；
2.JPA是规范：JPA本质上就是一种ORM规范，不是ORM框架，这是因为JPA并未提供ORM实现，它只是制订了一些规范，提供了一些编程的API接口，但具体实现则由ORM厂商提供实现；
3.Hibernate是实现：Hibernate除了作为ORM框架之外，它也是一种JPA实现
4.从功能上来说，JPA是Hibernate功能的一个子集
```
### 3. JPA的供应商
```markdown
JPA 的目标之一是制定一个可以由很多供应商实现的API，Hibernate 3.2+、TopLink 10.1+ 以及OpenJPA都提供了JPA的实现，Jpa供应商有很多，常见的有如下四种：
1.Hibernate
    JPA 的始作俑者就是 Hibernate 的作者，Hibernate 从 3.2 开始兼容 JPA。
2.OpenJPA
    OpenJPA 是 Apache 组织提供的开源项目。
3.TopLink
    TopLink 以前需要收费，如今开源了。
4.EclipseLink
```
### 4. JPA的优势
```markdown
1.标准化: 提供相同的API，这保证了基于JPA开发的企业应用能够经过少量的修改就能够在不同的JPA框架下运行。
2.简单易用，集成方便: JPA的主要目标之一就是提供更加简单的编程模型，在JPA框架下创建实体和创建Java类一样简单，只需要使用javax.persistence.Entity进行注解；JPA的框架和接口也都非常简单。
3.可媲美JDBC的查询能力: JPA的查询语言是面向对象的，JPA定义了独特的JPQL，而且能够支持批量更新和修改、JOIN、GROUP BY、HAVING等通常只有SQL才能够提供的高级查询特性，甚至还能够支持子查询。
4.支持面向对象的高级特性: JPA中能够支持面向对象的高级特性，如类之间的继承、多态和类之间的复杂关系，最大限度的使用面向对象的模型
```
### 5. JPA包含的技术
```markdown
ORM映射元数据：JPA支持XML和JDK5.0注解两种元数据的形式，元数据描述对象和表之间的映射关系，框架据此将实体对象持久化到数据库表中。
JPA的API：用来操作实体对象，执行CRUD操作，框架在后台完成所有的事情，开发者从繁琐的JDBC和SQL代码中解脱出来。
查询语言（JPQL）：这是持久化操作中很重要的一个方面，通过面向对象而非面向数据库的查询语言查询数据，避免程序和具体的SQL紧密耦合。
```

## 二,Spring Data
>> Spring Data是Spring的一个子项目。用于简化数据库访问，支持NoSQL和关系数据存储。其主要目标是使数据库的访问变得方便快捷。
### 1.Spring Data具有如下特点：
```markdown
1.SpringData 项目支持 NoSQL 存储：
    MongoDB （文档数据库）
    Neo4j（图形数据库）
    Redis（键/值存储）
    Hbase（列族数据库）
2.SpringData 项目所支持的关系数据存储技术：
    JDBC
    JPA
3.Spring Data Jpa致力于减少数据访问层 (DAO) 的开发量. 开发者唯一要做的，就是声明持久层的接口，其他都交给Spring Data JPA来帮你完成！
4.框架怎么可能代替开发者实现业务逻辑呢？比如：当有一个 UserDao.findUserById()这样一个方法声明，大致应该能判断出这是根据给定条件的ID查询出满足条件的 User 对象。
    Spring Data JPA做的便是规范方法的名字，根据符合规范的名字来确定方法需要实现什么样的逻辑。
```
### 2.Repository
```markdown
1.Repository接口是Spring Data的一个核心接口，它不提供任何方法，开发者需要在自己定义的接口中声明需要的方法
    public interface Repository<T, ID extends Serializable> { }
2.若我们定义的接口继承了Repository, 则该接口会被IOC容器识别为一个Repository Bean，进而纳入到IOC容器中，进而可以在该接口中定义满足一定规范的方法。
3.Spring Data可以让我们只定义接口，只要遵循Spring Data的规范，就无需写实现类。
4.与继承Repository等价的一种方式，就是在持久层接口上使用@RepositoryDefinition注解，并为其指定domainClass和idClass属性。
基础的Repository提供了最基本的数据访问功能，其几个子接口则扩展了一些功能，它的几个常用的实现类如下：
    CrudRepository：继承 Repository，实现了一组 CRUD 相关的方法。
    PagingAndSortingRepository： 继承 CrudRepository，实现了一组分页排序相关的方法。
    JpaRepository： 继承 PagingAndSortingRepository，实现一组 JPA 规范相关的方法。
    自定义的 XxxxRepository 需要继承 JpaRepository，这样的 XxxxRepository 接口就具备了通用的数据访问控制层的能力。
    JpaSpecificationExecutor： 不属于Repository体系，实现一组 JPA Criteria 查询相关的方法。
```
### SpringData注解
```markdown
 @Query 关键字，来自定义查询 SQL
 @Modifying 注解，@Query 与 @Modifying 这两个 annotation一起声明，可定义个性化更新操作
```
##