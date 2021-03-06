# 极客时间 Java业务开发常见错误100例

## Java开发中的一些常见错误&坏味道
[消灭 Java 代码的“坏味道”](https://developer.aliyun.com/article/718160)
```markdown
范例5：final修饰的对象调用成员变量
class Other{
    public int i;	
}
public class Main{
    public static void void main(String[] args){
        Other o = new Other();
        new Main().addOne();
    }
    public void addOne(final Other o){//在addOne方法中，Other对象被修饰为final
        o.i++;//修改Other对象的成员变量，而o对象的reference并没有改变
    }
}
范例6，7 实例变量（成员变量）有默认值，而final修饰的变量在构造器结束之前必须被赋予一个明确的值。
public class Main{
    int i;
    public void doSomething(){
        System.out.println("i="+i);正常输出i=0,当final int i;时程序会报错。
    }
}
范例8：static方法不能直接调用非static方法，但是可以调用类的非static方法。
------------------------------------------------------------------------------------
Java业务开发常见错误100例
02.代码加锁
    # 代码块级别的synchronized和方法上标记的synchronized关键字，在实现上有什么区别？
    # JDK里的ReentrantLock和ReentrantReadWriteLock都提供了公平锁的版本，但是不要轻易开启，
    在任务很轻的情况下开启公平锁可能会让性能下降上百倍。
03.线程池
    # 为什么禁止使用Java中Executor类中定义的工具方法创建线程池？
    	newFixedThreadPool和newCachedThreadPool可能因为资源耗尽导致OOM问题。
	为什么？
	     1.newFixedThreadPool创建的线程池的工作队列直接new了一个LinkedBlockingQueue,
	     而默认构造方法的LinkedBlockingQueue是一个Integer.MAX_VALUE长度的队列，可以认为是无界的。
	     2.newCacheThreadPool创建的线程池的最大线程数是Integral.MAX_VALUE,可以认为是没有上限的，
	     而其工作队列SynchronousQueue是一个没有存储空间的阻塞队列。这意味着，只要请求到来，
	     就必须找到一条工作线程来处理，如果当前没有空闲的线程就再创建一条新的。
    # 建议手动声明线程池 new ThreadPoolExecutor来创建线程池。还建议用一些监控手段来观察线程池的状态。
    # Java8中的parallel stream功能，可以让我们很方便地并行处理集合中元素，其背后是共享一个ForkJoinPool，默认并行度是CPU核数-1。
	对于CPU绑定任务来说使用这样的配置比较合理，但是如果集合操作涉及到同步IO操作(比如数据库操作，外部服务调用)，
	建议自定义一个ForkJoinPool(或者普通线程池)参考第一讲Demo。
04.连接池
    数据库连接池，Redis连接池，HTTP连接池。
    为了保证连接池正确使用：1.连接池务必确保复用。2.在程序退出之前显式关闭连接池释放资源。
    连接池配置参数：最重要的是最大连接数。
05.HTTP调用
    HTTP调用的超时，重试，并发等等问题。
06.Spring事务
07.数据库索引
08.判等问题
09.数值计算
10.集合类
11.空值处理
12.异常处理：
	1.Spring“统一异常处理”方式是第一个错误： 不在业务代码层面考虑异常处理，仅在框架层面粗犷捕获和处理异常。
	三层架构：
		controller：负责信息收集，参数校验。转换服务层处理的数据适配前端，轻应用逻辑。
		service：负责核心业务逻辑，包括各种外部服务调用，访问数据库，缓存处理，消息处理。
		Respiratory：负责数据访问实现，一般没有业务逻辑。  
	2.小心finally代码块中的资源回收逻辑，确保finally代码块不出现异常。
		AutoCloseable接口的资源，务必使用try-with-resources模式使用资源，确保正常释放。
	3.确保正确处理了线程池中任务的异常。
13.日志
	1.日志框架众多。
	2.配置复杂且容易出错。
	3.日志记录本身就存在一些误区，比如没考虑到日志内容获取的代价，胡乱使用日志级别等等。
14.文件IO
	1.要读写字符流，需要确保文件中字符的字符集和字符流的字符集一致，否则可能产生乱码。
	2.使用files类的一些流式处理操作，注意使用try-with-resources包装stream，确保底层文件资源可以释放。
	3.进行文件字符流操作时，一般不考虑逐字节操作，使用缓冲区进行批量读写减少磁盘IO，性能会提示不少。
15.序列化
16.JDK8日期类

```
## 01 | 使用了并发工具类库，线程安全就高枕无忧了吗？
```markdown
1.没有意识到线程重用导致用户信息错误乱的BUG
    - 1.使用ThreadLocal来缓存获取到的用户信息，线程池会重用固定的几个线程，一旦线程重用，从ThreadLocal获取的值是之前遗留的值。
        执行程序的线程是Tomcat的工作线程，而Tomcat的工作线程是基于线程池的。    
        解决方式：将Tomcat的工作线程池最大线程数设置为1，这样始终是同一个线程在处理请求；server.tomcat.max-threads=1
            在使用类似于ThreadLocal工具来存放一些数据时，需要特别注意在代码运行完成后，显式地去清空设置的数据。
        总结收获：在写业务代码之前，首先要理解代码会跑在什么线程上。即使代码没有显示开启多线程，也要注意多线程问题。
2.使用了线程安全的并发工具，并不代表解决了所有线程安全问题
    期望通过把线程不安全的类替换为线程安全的类来一键解决问题。比如认为使用ConcurrentHashMap就可以解决线程安全问题，没有对复合逻辑加锁导致业务逻辑错误。

3.没有充分了解并发工具的特性，从而无法发挥其威力
    比如使用了ConcurrentHashMap，但是没有充分利用其提供的基于CAS安全的方法，还是使用锁的方式来实现逻辑。
4.没有认清并发工具的使用场景，因而导致性能问题
    比如没有理解CopyOnWriteArrayList的使用场景，把大量读写均衡或者大量写操作的场景下，导致出现性能问题。
```
## 02 | 代码加锁：不要让“锁”事成为烦心事
```markdown
1.加锁前要清楚锁和被保护对象是不是一个层面的
    使用synchronized加锁
2.加锁要考虑锁的粒度和场景问题
    区分读写分离以及资源的访问冲突，考虑使用悲观方式的锁还是乐观方式的锁。
3.多把锁小心死锁问题
```
## 03 | 线程池：业务代码最常用也最容易犯错的组件
```markdown
1.线程池声明需要手动进行
2.线程池线程管理策略详解
3.务必确认清楚线程池本身是不是复用的
4.需要仔细斟酌线程池的混用策略
```
## 04 | 连接池：别让连接池帮了倒忙
```markdown
常见的会用到的连接池，主要是数据库连接池，Redis连接池和HTTP连接池。
1.注意鉴别客户端SDK是否基于连接池
2.使用连接池务必确保复用，尽可能在程序退出之前显式关闭连接池释放资源。
3.连接池的配置不是一成不变的，连接池中最重要的参数是最大连接数。
```
## 05 | HTTP调用：你考虑到超时、重试、并发了吗？
```markdown
1.配置连接超时和读取超时参数的学问
2.Feign和Ribbon配合使用，怎么配置超时
3.你是否知道Ribbon会自动重试请求呢？
4.HTTP客户端以及浏览器都会限制客户端调用的最大并发数
```
## 06 | 20%的业务代码的Spring声明式事务，可能都没处理正确
```markdown
1.小心Spring的事务可能没有生效
    @Transactions生效原则
        1.除非特殊配置（比如使用AspectJ静态织入实现AOP），否则只有定义下public方法上的@Transactions才能生效。
        2.必须通过代理过的类从外部调用目标方法才能生效
2.事务即使生效也不一定能够回滚
    1.只有异常传播出了标记了@Transactions注解的方法，事务才能回滚
    2.默认情况下，出现RuntimeException（非受检异常）或者Error的时候，Spring才会回滚异常。
3.请确认事务传播配置是否符合自己的业务逻辑
```
## 07 | 数据库索引：索引并不是万能药
```markdown
1.InnoDB是如何存储数据的
2.考虑额外创建非聚簇索引的代价
3.不是所有针对索引列的查询都能用上索引
4.数据库基于成本决定是否走索引
```


