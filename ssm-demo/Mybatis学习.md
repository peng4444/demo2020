# Mybatis学习

[TOC]

## Mybatis基础学习总结
[随笔分类-Mybatis](https://www.cnblogs.com/zwwhnly/category/1492402.html) 
[当前标签：Mybatis](https://www.cnblogs.com/Chenjiabing/tag/Mybatis/)
[Mybatis 使用的 9 种设计模式，真是太有用了](https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650146473&idx=2&sn=7f25d271492b5239b8ca2214be9fba5e&chksm=f3681b88c41f929e64bdaa8c94d0e80ac65ba982684b79b3daae2f46a14507c6dd5663345e56&mpshare=1&scene=23&srcid=1104DSFh2jwu5fPDbK44c6GW&sharer_sharetime=1604456536477&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
### 1.MyBatis是什么
```markdown
MyBatis是一款优秀的持久层框架，一个半ORM（对象关系映射）框架，它支持定制化SQL、存储过程以及高级映射。
    MyBatis避免了几乎所有的JDBC代码和手动设置参数以及获取结果集。
    MyBatis可以使用简单的XML或注解来配置和映射原生类型、接口和Java的POJO为数据库中的记录。
ORM是什么？
ORM（Object Relational Mapping），对象关系映射，是一种为了解决关系型数据库数据与简单Java对象（POJO）的映射关系的技术。
    简单的说，ORM是通过使用描述对象和数据库之间映射的元数据，将程序中的对象自动持久化到关系型数据库中。
为什么说Mybatis是半自动ORM映射工具？它与全自动的区别在哪里？
    Hibernate属于全自动ORM映射工具，使用Hibernate查询关联对象或者关联集合对象时，可以根据对象关系模型直接获取，所以它是全自动的。
    而Mybatis在查询关联对象或关联集合对象时，需要手动编写sql来完成，所以，称之为半自动ORM映射工具。
```
### 2.JDBC开发存在的问题&MyBatis是如何解决
```markdown
频繁创建数据库连接对象、释放，容易造成系统资源浪费，影响系统性能。可以使用连接池解决这个问题。但是使用jdbc需要自己实现连接池。
sql语句定义、参数设置、结果集处理存在硬编码。实际项目中sql语句变化的可能性较大，一旦发生变化，需要修改java代码，系统需要重新编译，重新发布。不好维护。
使用preparedStatement向占有位符号传参数存在硬编码，因为sql语句的where条件不一定，可能多也可能少，修改sql还要修改代码，系统不易维护。
结果集处理存在重复代码，处理麻烦。如果可以映射成Java对象会比较方便。
1、数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库连接池可解决此问题。
解决：在mybatis-config.xml中配置数据链接池，使用连接池管理数据库连接。
2、Sql语句写在代码中造成代码不易维护，实际应用sql变化的可能较大，sql变动需要改变java代码。
解决：将Sql语句配置在XXXXmapper.xml文件中与java代码分离。
3、向sql语句传参数麻烦，因为sql语句的where条件不一定，可能多也可能少，占位符需要和参数一一对应。
解决：Mybatis自动将java对象映射至sql语句。
4、对结果集解析麻烦，sql变化导致解析代码变化，且解析前需要遍历，如果能将数据库记录封装成pojo对象解析比较方便。
解决：Mybatis自动将sql执行结果映射至java对象。
```
### 3.Mybatis优缺点
```markdown
优点：
    与传统的数据库访问技术相比，ORM有以下优点：
    基于SQL语句编程，相当灵活，不会对应用程序或者数据库的现有设计造成任何影响，SQL写在XML里，解除sql与程序代码的耦合，
        便于统一管理；提供XML标签，支持编写动态SQL语句，并可重用。
    与JDBC相比，减少了50%以上的代码量，消除了JDBC大量冗余的代码，不需要手动开关连接。
    很好的与各种数据库兼容（因为MyBatis使用JDBC来连接数据库，所以只要JDBC支持的数据库MyBatis都支持）
    提供映射标签，支持对象与数据库的ORM字段关系映射；提供对象关系映射标签，支持对象关系组件维护
    能够与Spring很好的集成。
缺点：
    SQL语句的编写工作量较大，尤其当字段多、关联表多时，对开发人员编写SQL语句的功底有一定要求
    SQL语句依赖于数据库，导致数据库移植性差，不能随意更换数据库
```
### 4.MyBatis的解析和运行原理及使用MyBatis的步骤【10+】
```markdown
MyBatis编程步骤是什么样的？
    1、 创建SqlSessionFactory
    2、 通过SqlSessionFactory创建SqlSession
    3、 通过sqlsession执行数据库操作
    4、 调用session.commit()提交事务
    5、 调用session.close()关闭会话       
请说说MyBatis的工作原理:
    1）读取MyBatis配置文件：mybatis-config.xml为MyBatis的全局配置文件，配置了MyBatis的运行环境等信息，例如数据库连接信息。
    2）加载映射文件。映射文件即SQL映射文件，该文件中配置了操作数据库的SQL语句，需要在MyBatis配置文件mybatis-config.xml中加载。
        mybatis-config.xml 文件可以加载多个映射文件，每个文件对应数据库中的一张表。
    3）构造会话工厂：通过MyBatis的环境等配置信息构建会话工厂SqlSessionFactory。
    4）创建会话对象：由会话工厂创建SqlSession对象，该对象中包含了执行SQL语句的所有方法。
    5）Executor执行器：MyBatis底层定义了一个Executor接口来操作数据库，它将根据SqlSession传递的参数动态地生成需要执行的 SQL语句，同时负责查询缓存的维护。
    6）MappedStatement对象：在Executor接口的执行方法中有一个MappedStatement类型的参数，该参数是对映射信息的封装，用于存储要映射的SQL语句的id、参数等信息。
    7）输入参数映射：输入参数类型可以是Map、List 等集合类型，也可以是基本数据类型和POJO类型。输入参数映射过程类似于JDBC对preparedStatement对象设置参数的过程。
    8）输出结果映射：输出结果类型可以是Map、List 等集合类型，也可以是基本数据类型和POJO类型。输出结果映射过程类似于JDBC对结果集的解析过程。
```
### 8.Mybatis都有哪些Executor执行器【5+】
```markdown
**Mybatis有三种基本的Executor执行器，SimpleExecutor、ReuseExecutor、BatchExecutor。**
    1.SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。
    2.ReuseExecutor：执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，
        而是放置于Map<String, Statement>内，供下一次使用。简言之，就是重复使用Statement对象。
    3.BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），
        它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。
作用范围：Executor的这些特点，都严格限制在SqlSession生命周期范围内。
Mybatis中如何指定使用哪一种Executor执行器？
    在Mybatis配置文件中，在设置（settings）可以指定默认的ExecutorType执行器类型，也可以手动给DefaultSqlSessionFactory的创建SqlSession的方法
    传递ExecutorType类型参数，如SqlSession openSession(ExecutorType execType)。
配置默认的执行器。SIMPLE就是普通的执行器；REUSE执行器会重用预处理语句（prepared statements）；BATCH执行器将重用语句并执行批量更新。
```
### 9.Mybatis一级缓存和二级缓存【5+】
[Mybatis的缓存——一级缓存和源码分析](https://www.cnblogs.com/isdxh/p/13956845.html)
[Mybatis的二级缓存、使用Redis做二级缓存](https://www.cnblogs.com/isdxh/p/13963636.html)
```markdown
一级缓存：基于PerpetualCache和HashMap本地缓存，它的生命周期是和SQLSession一致的，有多个SQLSession或者分布式的环境中数据库操作，可能会出现脏数据。
    当Session flush或者close之后，该Session中非所有的Cache就将被清空，默认一级缓存是开启。
二级缓存：也是基于PerpetualCache和HashMap本地缓存，不同在于其存储作用域为Mapper级别的，如果多个SQLSession之间需要共享缓存，则需要使用到二级缓存，
    并且二级缓存可以自定义存储源，如Ehcache。默认不开启二级缓存，要开启二级环境，使用二级缓存属性类需要实现Serializable序列化接口(可用来保存对象的状态)。
开启二级缓存数据查询流程：二级缓存->一级缓存->数据库
缓存更新机制：当某一个作用域（一级缓存Session、二级缓存Mapper）进行C/U/D操作后，默认该作用域下所有select中的缓存将被clear。
如果是一级缓存，那么我在同一个SqlSession里面执行了三行语句，第一行，从表中查询数据，第二行，给这个表添加数据，第三行，执行跟第一行一样的查询语句，
根据一级缓存，那第三行查询的结果不就是第一次缓存的数据吗，可是表已经增加了新的数据，不就出现数据不一致了吗？（insert语句会刷新缓存！）
默认情况下一级缓存是开启的，而且是不能关闭的。
- 一级缓存是指SqlSession级别的缓存 原理：使用的数据结构是一个map，如果两次中间出现commit操作 （修改、添加、删除），本sqlsession中的一级缓存区域全部清空
- 二级缓存是指可以跨SqlSession的缓存。是mapper级别的缓存；原理：是通过CacheExecutor实现的。CacheExecutor其实是Executor的代理对象
1、一级缓存：指的是mybatis中sqlSession对象的缓存，当我们执行查询以后，查询的结果会同时存入sqlSession中，再次查询的时候，
先去sqlSession中查询，有的话直接拿出，当sqlSession消失时，mybatis的一级缓存也就消失了，当调用sqlSession的修改、添加、删除、commit()、close()等方法时，会清空一级缓存。
2、二级缓存：指的是mybatis中的sqlSessionFactory对象的缓存，由同一个sqlSessionFactory对象创建的sqlSession共享其缓存，但是其中缓存的是数据而不是对象。
当命中二级缓存时，通过存储的数据构造成对象返回。查询数据的时候，查询的流程是二级缓存 > 一级缓存 > 数据库。
3、如果开启了二级缓存，sqlSession进行close()后，才会把sqlSession一级缓存中的数据添加到二级缓存中，为了将缓存数据取出执行反序列化，
还需要将要缓存的pojo实现Serializable接口，因为二级缓存数据存储介质多种多样，不一定只存在内存中，也可能存在硬盘中。
4、mybatis框架主要是围绕sqlSessionFactory进行的，具体的步骤：
    1.定义一个configuration对象，其中包含数据源、事务、mapper文件资源以及影响数据库行为属性设置settings。
    2.通过配置对象，则可以创建一个sqlSessionFactoryBuilder对象。
    3.通过sqlSessionFactoryBuilder获得sqlSessionFactory实例。
    4.通过sqlSessionFactory实例创建qlSession实例，通过sqlSession对数据库进行操作。
在xml文件中添加<cache />
    加上这个标签，二级缓存就会开启，他的默认属性如下：
    - 1.映射语句文件中的所有select语句将会被缓存。
    - 2.映射语句文件中的所有insert,update和delete语句会刷新缓存。
    - 3.缓存会使用Least Recently Used(LRU,最近最少使用的)算法来收回。
    - 4.根据时间表(比如 no Flush Interval,没有刷新间隔), 缓存不会以任何时间顺序来刷新。
    - 5.缓存会存储列表集合或对象(无论查询方法返回什么)的1024个引用。
    - 6.缓存会被视为是read/write(可读/可写)的缓存,意味着对象检索不是共享的,而且可以安全地被调用者修改,而不干扰其他调用者或线程所做的潜在修改。
也可以自定义二级缓存的属性，例如：
    <cache
      eviction="FIFO"
      flushInterval="60000"
      size="512"
      readOnly="true"/>
    配置创建了一个FIFO缓存,并每 60秒刷新,存数结果对象或列表的512个引用,而且返回的对象被认为是只读的,因此在不同线程中的调用者之间修改它们会导致冲突。
```
### 10.Mybatis如何防止sql注入？${}和#{}的区别是什么？传入表名用哪个？【5+】
```markdown
防止sql注入：在编写mybatis的映射语句时，尽量采用“#{xxx}”这样的格式
#和$区别：
        #{}	                     ${}
    相当于对数据加上双引号	 相当于直接显示数据
    很大程度上防止SQL注入	 无法防止SQL注入
#{xxx},使用的是PreparedStatement,会有类型转换，比较安全	${xxx}，使用字符串拼接，容易SQL注入
​简单的说就是#{}是经过预编译的，是安全的，${}是未经过预编译的，仅仅是取变量的值，是非安全的，存在SQL注入。
要实现动态传入表名、列名，需要做如下修改：
    添加属性statementType="STATEMENT"，同时sql里的属有变量取值都改成${xxxx}
${}是Properties文件中的变量占位符，它可以用于标签属性值和sql内部，属于静态文本替换，比如${driver}会被静态替换为com.mysql.jdbc.Driver。
#{}是sql的参数占位符，Mybatis会将sql中的#{}替换为?号，在sql执行前会使用PreparedStatement的参数设置方法，按序给sql的?号占位符设置参数值，
比如ps.setInt(0, parameterValue)，#{item.name}的取值方式为使用反射从参数对象中获取item对象的name属性值，相当于param.getItem().getName()。
```
### 11.Mybatis是如何将sql执行结果封装为目标对象并返回的？都有哪些映射形式？
```markdown
第一种是使用<resultMap>标签，逐一定义列名和对象属性名之间的映射关系。第二种是使用sql列的别名功能，将列别名书写为对象属性名，
比如T_NAME AS NAME，对象属性名一般是name，小写，但是列名不区分大小写，Mybatis会忽略列名大小写，智能找到与之对应对象属性名，你甚至可以写成T_NAME AS NaMe，Mybatis一样可以正常工作。
有了列名与属性名的映射关系后，Mybatis通过反射创建对象，同时使用反射给对象的属性逐一赋值并返回，那些找不到映射关系的属性，是无法完成赋值的。
```
### 12.Mybatis能执行一对一、一对多的关联查询吗？都有哪些实现方式，以及它们之间的区别
```markdown
能，Mybatis不仅可以执行一对一、一对多的关联查询，还可以执行多对一，多对多的关联查询，多对一查询，其实就是一对一查询，只需要把selectOne()修改为selectList()即可；
多对多查询，其实就是一对多查询，只需要把selectOne()修改为selectList()即可。
关联对象查询，有两种实现方式，一种是单独发送一个sql去查询关联对象，赋给主对象，然后返回主对象。另一种是使用嵌套查询，嵌套查询的含义为使用join查询，
一部分列是A对象的属性值，另外一部分列是关联对象B的属性值，好处是只发一个sql查询，就可以把主对象和其关联对象查出来。
那么问题来了，join查询出来100条记录，如何确定主对象是5个，而不是100个？
其去重复的原理是<resultMap>标签内的<id>子标签，指定了唯一确定一条记录的id列，Mybatis根据<id>列值来完成100条记录的去重复功能，<id>可以有多个，代表了联合主键的语意。
同样主对象的关联对象，也是根据这个原理去重复的，尽管一般情况下，只有主对象会有重复记录，关联对象一般不会重复。
```
### 13.Mybatis动态sql是做什么的？都有哪些动态sql？能简述一下动态sql的执行原理不？
[Mybatis动态SQL](https://www.cnblogs.com/isdxh/p/13956223.html)
```markdown
Mybatis动态sql可以让我们在Xml映射文件内，以标签的形式编写动态sql，完成逻辑判断和动态拼接sql的功能，
Mybatis提供了9种动态sql标签trim|where|set|foreach|if|choose|when|otherwise|bind。
其执行原理为，使用OGNL从sql参数对象中计算表达式的值，根据表达式的值动态拼接sql，以此来完成动态sql的功能。
```
### 14.MyBatis与Hibernate的区别是什么？
```markdown
Hibernate 框架：
​ Hibernate是一个开放源代码的对象关系映射框架,它对JDBC进行了非常轻量级的对象封装,建立对象与数据库表的映射。是一个全自动的、完全面向对象的持久层框架。
Mybatis框架：
​ Mybatis是一个开源对象关系映射框架，原名：ibatis,2010年由谷歌接管以后更名。是一个半自动化的持久层框架。
区别：
    开发方面
​       在项目开发过程当中，就速度而言：
​       hibernate开发中，sql语句已经被封装，直接可以使用，加快系统开发；
​       Mybatis 属于半自动化，sql需要手工完成，稍微繁琐；
​       但是，凡事都不是绝对的，如果对于庞大复杂的系统项目来说，复杂语句较多，hibernate 就不是好方案。
    sql优化方面
​       Hibernate 自动生成sql,有些语句较为繁琐，会多消耗一些性能；
​       Mybatis 手动编写sql，可以避免不需要的查询，提高系统性能；
    对象管理比对
​       Hibernate是完整的对象-关系映射的框架，开发工程中，无需过多关注底层实现，只要去管理对象即可；
​       Mybatis需要自行管理映射关系；
```
MyBatis 如何实现模糊查询?
### 15.Mybatis如何找到指定的Mapper的，如何完成查询的。
### 16.MyBatis分页方式和原理【2+】
[新技能 MyBatis 千万数据表，快速分页！](https://mp.weixin.qq.com/s?__biz=MzIwODkzOTc1MQ==&mid=2247487085&idx=1&sn=4c50d2595f2308b9911c5af22841d789&chksm=977a31e5a00db8f38837b441cbfccad15a40628dfd2b1fea93557802b5fca48ac199660d1a85&mpshare=1&scene=23&srcid=1129BARfomwvSrpMugY6QeFO&sharer_sharetime=1606650267912&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
```markdown
分页方式：逻辑分页，物理分页
    逻辑分页：使用Mybatis自带的RowBounds进行分页，它是一次性查询很多数据，然后在数据中再进行检索。
        弊端：需要消耗大量的内存，有内存溢出的风险，对数据库压力较大。
    物理分页：注解手动SQL分页或者使用分页插件PageHelper,去数据库查询指定条数的分页数据的形式。没有上述弊端。
RowBounds是一次性查询全部结果吗?并不是，Mybatis是对jdbc进行的封装，需要按照jdbc中的配置一次取多少条数据。
Mybatis分页插件的实现原理：
    分页插件的基本原理是使用MyBatis提供的插件接口，实现自定义插件，在插件的拦截方法内等待执行的SQL，然后重写SQL
    根据dialect方言，添加对应的物理分页语句和物理分页参数。
    举例：select * from student，拦截sql后重写为：select t.* from （select * from student）t limit 0，10;
自定义插件实现原理
    Mybatis自定义插件针对MyBatis四大对象（Executor,StatementHandler,ParameterHandler,ResultSetHandler）进行拦截：
        Executor：拦截内部执行器，负责调用StatementHandler操作数据库，并且把结果通过ResultSetHandler进行自动映射，还处理了二级缓存的操作。
        StatementHandler:拦截SQL语法构建的处理，是Mybatis直接和数据库执行SQL脚本的对象，另外也实现了Mybatis的意见缓存。
        ParameterHandler:拦截参数的处理。
        ResultSetHandler:拦截结果集的处理。
```
### 17.MyBatis是否支持延迟加载，延迟加载的原理
```markdown
Mybatis支持延迟加载，设置lazyLoadingEnabled=true即可。
延迟加载的原理是：调用的时候触发加载，而不是在初始化的时候就进行加载。
Mybatis仅支持association关联对象和collection关联集合对象的延迟加载，association指的就是一对一，collection指的就是一对多查询。
在Mybatis配置文件中，可以配置是否启用延迟加载lazyLoadingEnabled=true|false。
原理是，使用CGLIB创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用a.getB().getName()，拦截器invoke()方法发现a.getB()是null值，
那么就会单独发送事先保存好的查询关联B对象的sql，把B查询上来，然后调用a.setB(b)，于是a的对象b属性就有值了，接着完成a.getB().getName()方法的调用。
这就是延迟加载的基本原理。
当然了，不光是Mybatis，几乎所有的包括Hibernate，支持延迟加载的原理都是一样的。
```
### 18.Xml映射文件中，除了常见的select|insert|update|delete标签之外，还有哪些标签？
```markdown
select、insert、update、delete 标签分别对应查询、添加、更新、删除操作。
还有很多其他的标签，<resultMap>、<parameterMap>、<sql>、<include>、<selectKey>，
加上动态sql的9个标签，trim|where|set|foreach|if|choose|when|otherwise|bind等，
其中<sql>为sql片段标签，通过<include>标签引入sql片段，<selectKey>为不支持自增的主键生成策略标签。
parameterType属性表示参数的数据类型，包括基本数据类型和对应的包装类型、String和Java Bean类型，当有多个参数时可以使用#{argn}的形式表示第n个参数。除了基本数据类型都要以全限定类名的形式指定参数类型。
resultType表示返回的结果类型，包括基本数据类型和对应的包装类型、String和Java Bean类型。还可以使用把返回结果封装为复杂类型的resultMap 。
```
### 19.Dao接口的工作原理是什么？Dao接口里的方法，参数不同时，方法能重载吗？
```markdown
最佳实践中，通常一个Xml映射文件，都会写一个Dao接口与之对应，
请问，这个Dao接口的工作原理是什么？Dao接口里的方法，参数不同时，方法能重载吗？
Dao接口，就是人们常说的Mapper接口，接口的全限名，就是映射文件中的namespace的值，接口的方法名，就是映射文件中MappedStatement的id值，接口方法内的参数，就是传递给sql的参数。
Mapper接口是没有实现类的，当调用接口方法时，接口全限名+方法名拼接字符串作为key值，可唯一定位一个MappedStatement，举例：com.mybatis3.mappers.StudentDao.findStudentById，
可以唯一找到namespace为com.mybatis3.mappers.StudentDao下面id=findStudentById的MappedStatement。在Mybatis中，每一个<select>、<insert>、<update>、<delete>标签，都会被解析为一个MappedStatement对象。
Dao接口里的方法，是不能重载的，因为是全限名+方法名的保存和寻找策略。
Dao接口的工作原理是JDK动态代理，Mybatis运行时会使用JDK动态代理为Dao接口生成代理proxy对象，代理对象proxy会拦截接口方法，转而执行MappedStatement所代表的sql，然后将sql执行结果返回。
```
### 20.简述Mybatis的插件运行原理，以及如何编写一个插件
```markdown
Mybatis仅可以编写针对ParameterHandler、ResultSetHandler、StatementHandler、Executor这4种接口的插件，
Mybatis使用JDK的动态代理，为需要拦截的接口生成代理对象以实现接口方法拦截功能，每当执行这4种接口对象的方法时，就会进入拦截方法，
具体就是InvocationHandler的invoke()方法，当然，只会拦截那些你指定需要拦截的方法。
实现Mybatis的Interceptor接口并复写intercept()方法，然后在给插件编写注解，指定要拦截哪一个接口的哪些方法即可，记住，别忘了在配置文件中配置你编写的插件。
```
### 21.Mybatis的Xml映射文件中，不同的Xml映射文件，id是否可以重复？
```markdown
不同的Xml映射文件，如果配置了namespace，那么id可以重复；如果没有配置namespace，那么id不能重复；毕竟namespace不是必须的，只是最佳实践而已。
原因就是namespace+id是作为Map<String, MappedStatement>的key使用的，如果没有namespace，就剩下id，那么，id重复会导致数据互相覆盖。
有了namespace，自然id就可以重复，namespace不同，namespace+id自然也就不同。
```
### 22.Mybatis中如何执行批处理？
```markdown
使用BatchExecutor完成批处理。
```
### 23.Mybatis如何执行Select语句
[Mybatis如何执行Select语句](https://www.cnblogs.com/Chenjiabing/p/13666108.html)
### 24.mybatis在xml文件中处理大于号小于号的方法
[mybatis在xml文件中处理大于号小于号的方法](https://blog.csdn.net/erlian1992/article/details/78218977)
```markdown
1.使用转义字符
&lt;  < 小于号
&gt;  > 大于号
&amp;  &  和
&apos; '  单引号
&quot; "   双引号
2.使用<![CDATA[   ]]>区，将sql语句包括起来，在两者之间嵌入不想被解析程序解析的原始数据，解析器不对
  CDATA区中的内容进行解析，而是将这些数据原封不动地交给下游程序处理。<![CDATA[ ]]>标记的sql语句中的<where>、<if>等标签不会被解析
```


## MyBatis源码
[MyBatis框架的使用及源码分析](https://www.cnblogs.com/zsg88/category/1080098.html)  
[mybatis源码](https://www.cnblogs.com/sanzao/tag/mybatis/) 
[mybatis源码]( https://www.cnblogs.com/java-chen-hao/category/1576447.html )
[精尽 MyBatis 源码分析 - 整体架构](https://www.cnblogs.com/lifullmoon/p/14014901.html)
## Mybatis相关博客
[mybatis是怎样炼成的](https://www.cnblogs.com/roytian/p/12762218.html)

### 1.Mybatis SQL如何执行
[面试官问你MyBatis SQL是如何执行的？把这篇文章甩给他](https://www.cnblogs.com/cxuanBlog/p/12248536.html)
[MyBatis执行流程的各阶段介绍](https://www.cnblogs.com/-beyond/p/13232624.html)
### Mybatis的特点
```markdown
1.解除SQL与程序代码的耦合，通过提供DAO层，将业务逻辑和数据访问逻辑分离，使系统的设计更清晰，更易维护，更易单元测试。SQL和代码的分离，提高了可维护性。
2.MyBatis 比较简单和轻量： 只要通过配置 jar 包，或者如果你使用 Maven 项目的话只需要配置 Maven 以来就可以。
3.屏蔽样板代码 MyBatis: 回屏蔽原始的 JDBC 样板代码，让你把更多的精力专注于 SQL 的书写和属性-字段映射上。
4.编写原生 SQL，支持多表关联 MyBatis 最主要的特点就是你可以手动编写 SQL 语句，能够支持多表关联查询。
    提供映射标签，支持对象与数据库的 ORM 字段关系映射
    提供 XML 标签，支持编写动态 SQL。
```
### Mybatis延迟加载，缓存
[mybatis缓存之一级缓存（一）](https://www.cnblogs.com/zhenghengbin/p/13193999.html)
[mybatis源码学习：一级缓存和二级缓存分析](https://www.cnblogs.com/summerday152/p/12773135.html)
[Mybatis延迟加载、缓存](https://www.cnblogs.com/sun-10387834/p/13656885.html)
[mybatis探究之延迟加载和缓存](https://www.cnblogs.com/liyier/p/12516626.html)
[MyBatis缓存机制（一级缓存，二级缓存）](https://www.cnblogs.com/ljl150/p/12918581.html)
#### Mybatis插件
[mybatis的插件机制](https://www.cnblogs.com/qm-article/p/11785350.html)
[深入理解Mybatis插件](https://www.cnblogs.com/heartlake/p/12855653.html)
### 
[Mybatis学习总结（一）](https://blog.csdn.net/Marmara01/article/details/88878280)

[MyBatis 开发手册](https://www.cnblogs.com/ZhuChangwu/p/11734347.html)

[Mybatis通用分页插件](https://github.com/pagehelper/Mybatis-PageHelper)

[手把手教你如何玩转插件：分页插件（Pagehelper）](https://blog.csdn.net/cs_hnu_scw/article/details/80718467)
[通过源代码分析Mybatis的功能](https://www.cnblogs.com/Weilence/p/13416986.html)

[[面试官：你分析过mybatis工作原理吗？](https://www.cnblogs.com/almm/p/11254403.html)]

[springboot2 + mybatis 多种方式实现多数据配置](https://www.cnblogs.com/song27/p/12595603.html)

[持久层框架JPA与Mybatis该如何选型](https://www.cnblogs.com/zimug/p/11790285.html)

[Mybatis强大的结果集映射器resultMap](https://www.cnblogs.com/felordcn/p/12945815.html)

[抛开Spring，你知道MyBatis加载Mapper的底层原理吗？](https://www.cnblogs.com/Howinfun/p/12973902.html)

[天哪！手动编写mybatis雏形竟然这么简单](https://www.cnblogs.com/quellanan/p/13055000.html)

[MyBatis 使用手册](https://www.cnblogs.com/lifullmoon/p/14014660.html)

[MyBatis 面试题](https://www.cnblogs.com/lifullmoon/p/14014648.html)

### mybatis plus
[提升开发效率的一款mybatis开发神器](https://mp.weixin.qq.com/s?__biz=MzA4NzQ0Njc4Ng==&mid=2247489537&idx=3&sn=73b164a80c147a17356b8cb82ae06d30&chksm=90381c6ca74f957a0acd9198ae0df657fee5fc49697ee109698030df56775cbfe62b67fe7aac&mpshare=1&scene=23&srcid=0907sWM491xNOoEDNoS2lEPk&sharer_sharetime=1599452230019&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
[小书MybatisPlus第7篇-代码生成器的原理精讲及使用方法](https://www.cnblogs.com/zimug/p/13370107.html)
[MyBatis-Plus](https://www.cnblogs.com/ideal-20/p/13763616.html)