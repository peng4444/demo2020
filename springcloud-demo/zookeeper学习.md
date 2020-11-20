# Zookeeper学习
## Zookeeper
[ZooKeeper的十二连问，你顶得了嘛？](https://www.cnblogs.com/jay-huaxiao/p/13599519.html)
[不懂 ZooKeeper？没关系，这一篇给你讲的明明白白](https://www.cnblogs.com/lazyegg/p/13672958.html)
[万字长文带你入门Zookeeper！！！](https://www.cnblogs.com/Chenjiabing/p/12678607.html)
[zookeeper源码](https://www.cnblogs.com/sunshine-2015/category/1450046.html)
>>zookeeper节点类型、服务器角色，watch机制。
>>使用zookeeper实现分布式锁和读写锁。
### 0.Zookeeper简介和应用场景
[Zookeeper是什么&怎么用](https://www.cnblogs.com/wugongzi/p/13282455.html)
>> Zookeeper是一个开源的分布式协调服务框架，主要用来解决分布式集群中应用系统的一致性问题和数据管理问题。
```markdown
zookeeper是一个分布式的开源的分布式应用程序协调服务，是Google chubby的开源实现的，是Hadoop和HBASE的重要组件。
它是一个分布式应用提供一致性服务的组件，功能包括：配置维护，域名服务，分布式同步，组服务等等。
zookeeper的功能：
    1.集群管理：监控节点存活状态，运行请求等等。
    2.主节点选举：主节点挂掉之后进行主节点选举。
    3.分布式锁：Zookeeper提供两种锁，独占锁和共享锁。
    4.命名服务：在分布式系统中，通过使用命名服务，客户端应用能够根据指定名字来获取资源或服务的地址，提供者等信息。
1.使用ZooKeeper作为「dubbo的注册中心」，使用ZooKeeper实现「分布式锁」。
2.ZooKeeper，它是一个开放源码的「分布式协调服务」，它是一个集群的管理者，它将简单易用的接口提供给用户。
3.可以基于Zookeeper实现诸如数据发布/订阅、负载均衡、命名服务、分布式协调/通知、集群管理、Master选举、分布式锁和分布式队列「等功能」。
4.Zookeeper的「用途」：命名服务、配置管理、集群管理、分布式锁、队列管理
Zookeeper应用场景：
    1.数据发布/订阅系统
    2.命名服务
    3.分布式协调/通知（心跳检测，工作进度汇报，系统调度）
    4.分布式锁
    5.分布式队列
Zookeeper的架构
    Zookeeper集群是一个基于主从架构的高可用集群（Leader，Follower，Observer）
```
### 1.CAP定理
```markdown
一个分布式系统不可能在满足分区容错性（P）的情况下同时满足一致性（C）和可用性（A）。
    在此ZooKeeper保证的是CP，ZooKeeper不能保证每次服务请求的可用性，
    在极端环境下，ZooKeeper可能会丢弃一些请求，消费者程序需要重新请求才能获得结果。
    另外在进行leader选举时集群都是不可用，所以说，ZooKeeper不能保证服务可用性。
```
### 2.BASE理论
```markdown
BASE理论是基本可用，软状态，最终一致性三个短语的缩写。
BASE理论是对CAP中一致性和可用性（CA）权衡的结果，其来源于对大规模互联网系统分布式实践的总结，是基于CAP定理逐步演化而来的，它大大降低了我们对系统的要求。
    1.基本可用：基本可用是指分布式系统在出现不可预知故障的时候，允许损失部分可用性。
        但是，这绝不等价于系统不可用。比如正常情况下，一个在线搜索引擎需要在0.5秒之内返回给用户相应的查询结果，但由于出现故障，查询结果的响应时间增加了1~2秒。
    2.软状态：软状态指允许系统中的数据存在中间状态，并认为该中间状态的存在不会影响系统的整体可用性，即允许系统在不同节点的数据副本之间进行数据同步的过程存在延时。
    3.最终一致性：最终一致性强调的是系统中所有的数据副本，在经过一段时间的同步后，最终能够达到一个一致的状态。
        因此，最终一致性的本质是需要系统保证最终数据能够达到一致，而不需要实时保证系统数据的强一致性。
```
### 3.ZooKeeper特点/特性
```markdown
Zookeeper 保证了如下分布式一致性特性：
「顺序一致性」：从同一客户端发起的事务请求，最终将会严格地按照顺序被应用到ZooKeeper中去。
「原子性」：所有事务请求的处理结果在整个集群中所有机器上的应用情况是一致的，也就是说，要么整个集群中所有的机器都成功应用了某一个事务，要么都没有应用。
「单一视图」：无论客户端连到哪一个ZooKeeper服务器上，其看到的服务端数据模型都是一致的。
「可靠性：」 一旦服务端成功地应用了一个事务，并完成对客户端的响应，那么该事务所引起的服务端状态变更将会被一直保留下来。
「实时性（最终一致性）：」Zookeeper仅仅能保证在一定的时间段内，客户端最终一定能够从服务端上读取到最新的数据状态。
```
### 4.ZAB协议
```markdown
ZAB协议包括两种基本的模式：崩溃恢复和消息广播。当整个Zookeeper集群刚刚启动或者Leader服务器宕机、重启或者网络故障导致不存在过半的服务器与
    Leader服务器保持正常通信时，所有服务器进入崩溃恢复模式，首先选举产生新的Leader服务器，然后集群中Follower服务器开始与新的Leader服务器进行数据同步。
    当集群中超过半数机器与该Leader服务器完成数据同步之后，退出恢复模式进入消息广播模式，Leader服务器开始接收客户端的事务请求生成事物提案（超过半数同意）来进行事务请求处理。
```
### 5.选举算法和流程：FastLeaderElection(默认提供的选举算法)
```markdown
目前有5台服务器，每台服务器均没有数据，它们的编号分别是1,2,3,4,5,按编号依次启动，它们的选择举过程如下：
    1.服务器1启动，给自己投票，然后发投票信息，由于其它机器还没有启动所以它收不到反馈信息，服务器1的状态一直属于Looking。
    2.服务器2启动，给自己投票，同时与之前启动的服务器1交换结果，由于服务器2的编号大所以服务器2胜出，但此时投票数没有大于半数，所以两个服务器的状态依然是LOOKING。
    3.服务器3启动，给自己投票，同时与之前启动的服务器1,2交换信息，由于服务器3的编号最大所以服务器3胜出，此时投票数正好大于半数，所以服务器3成为leader，服务器1,2成为follower。
    4.服务器4启动，给自己投票，同时与之前启动的服务器1,2,3交换信息，尽管服务器4的编号大，但之前服务器3已经胜出，所以服务器4只能成为follower。
    5.服务器5启动，后面的逻辑同服务器4成为follower。
```
### 6.znode节点上的监听机制嘛？讲下Zookeeper watch机制
```markdown
zk类似于linux中的目录节点树方式的数据存储，即分层命名空间，zk并不是专门存储数据的，它的作用是主要是维护和监控存储数据的状态变化，
    通过监控这些数据状态的变化，从而可以达到基于数据的集群管理，zk中的节点的数据上限时1M。
client端会对某个znode建立一个watcher事件，当该znode发生变化时，这些client会收到zk的通知，然后client可以根据znode变化来做出业务上的改变等。
Watcher监听机制:
    Zookeeper允许客户端向服务端的某个Znode注册一个Watcher监听，当服务端的一些指定事件触发了这个Watcher，
    服务端会向指定客户端发送一个事件通知来实现分布式的通知功能，然后客户端根据 Watcher通知状态和事件类型做出业务上的改变。
Watcher监听机制的工作原理:
    ZooKeeper的Watcher机制主要包括客户端线程、客户端 WatcherManager、Zookeeper服务器三部分。
    客户端向ZooKeeper服务器注册Watcher的同时，会将Watcher对象存储在客户端的WatchManager中。
    当zookeeper服务器触发watcher事件后，会向客户端发送通知， 客户端线程从 WatcherManager 中取出对应的 Watcher 对象来执行回调逻辑。
Watcher特性总结:
    「一次性:」一个Watch事件是一个一次性的触发器。一次性触发，客户端只会收到一次这样的信息。
    「异步的：」Zookeeper服务器发送watcher的通知事件到客户端是异步的，不能期望能够监控到节点每次的变化，Zookeeper只能保证最终的一致性，而无法保证强一致性。
    「轻量级：」Watcher 通知非常简单，它只是通知发生了事件，而不会传递事件对象内容。
    「客户端串行：」执行客户端 Watcher 回调的过程是一个串行同步的过程。
    注册watcher用getData、exists、getChildren方法
    触发watcher用create、delete、setData方法

```
### 7，zk部署方式
```markdown
单机部署：一台集群上运行
集群部署：多台集群上运行
伪集群部署：一台集群启动多个zookeeper实例运行
```
### 8.Zookeeper怎么保证主从节点的状态同步
```markdown
zookeeper的核心是原子广播，这个机制保证了各个server之间的同步,实现这个机制的协议是ZAB协议。
```
### 9.什么是命名服务，什么是配置管理，又什么是集群管理
```markdown
命名服务是指通过「指定的名字」来获取资源或者服务地址。Zookeeper可以创建一个「全局唯一的路径」，这个路径就可以作为一个名字。
    被命名的实体可以是「集群中的机器，服务的地址，或者是远程的对象」等。一些分布式服务框架（RPC、RMI）中的服务地址列表，通过使用命名服务，
    客户端应用能够根据特定的名字来获取资源的实体、服务地址和提供者信息等。
配置管理：实际项目开发中，我们经常使用.properties或者xml需要配置很多信息，如数据库连接信息、fps地址端口等等。
    因为你的程序一般是分布式部署在不同的机器上（如果你是单机应用当我没说），如果把程序的这些配置信息「保存在zk的znode节点」下，
    当你要修改配置，即znode会发生变化时，可以通过改变zk中某个目录节点的内容，利用「watcher通知给各个客户端」，从而更改配置。
集群管理包括集群监控和集群控制，其实就是监控集群机器状态，剔除机器和加入机器。
    zookeeper可以方便集群机器的管理，它可以实时监控znode节点的变化，一旦发现有机器挂了，该机器就会与zk断开连接，
    对用的临时目录节点会被删除，其他所有机器都收到通知。新机器加入也是类似酱紫，所有机器收到通知：有新兄弟目录加入啦。
```
### 10.znode有几种类型呢？zookeeper的数据模型是怎样的呢？
```markdown
ZooKeeper的视图数据结构，很像Unix文件系统，也是树状的，这样可以确定每个路径都是唯一的。
zookeeper的节点统一叫做「znode」，它是可以通过「路径来标识」。
znode的4种类型:
    根据节点的生命周期，znode可以分为4种类型，分别是
    持久节点（PERSISTENT）:这类节点被创建后，就会一直存在于Zk服务器上。直到手动删除。
    持久顺序节点（PERSISTENT_SEQUENTIAL）:基本特性同持久节点，不同在于增加了顺序性。父节点会维护一个自增整性数字，用于子节点的创建的先后顺序。
    临时节点（EPHEMERAL）:临时节点的生命周期与客户端的会话绑定，一旦客户端会话失效（非TCP连接断开），那么这个节点就会被自动清理掉。zk规定临时节点只能作为叶子节点。
    临时顺序节点（EPHEMERAL_SEQUENTIAL）:基本特性同临时节点，添加了顺序的特性。
```
### 11.znode节点里面存储的是什么吗？每个节点的数据最大不能超过多少呢？
```markdown
Znode数据节点的代码如下
public class DataNode implements Record {
    byte data[];                    
    Long acl;                       
    public StatPersisted stat;       
    private Set<String> children = null; 
}
Znode包含了「存储数据、访问权限、子节点引用、节点状态信息」，如
    「data:」  znode存储的业务数据信息
    「ACL:」 记录客户端对znode节点的访问权限，如IP等。
    「child:」 当前节点的子节点引用
    「stat:」 包含Znode节点的状态信息，比如「事务id、版本号、时间戳」等等。
为了保证高吞吐和低延迟，以及数据的一致性，znode只适合存储非常小的数据，不能超过1M，最好都小于1K。
```
### 12.zookeeper是如何保证事务的顺序一致性
```markdown
聊一聊ZooKeeper的顺序一致性[1] https://time.geekbang.org/column/article/239261
```
### 13.Zookeeper的服务器有几种角色嘛？Zookeeper下Server工作状态又有几种呢？
```markdown
Zookeeper集群中，有Leader、Follower和Observer三种角色
「Leader」:Leader服务器是整个ZooKeeper集群工作机制中的核心，其主要工作：
    事务请求的唯一调度和处理者，保证集群事务处理的顺序性
    集群内部各服务的调度者
「Follower」:Follower服务器是ZooKeeper集群状态的跟随者，其主要工作：
    处理客户端非事务请求，转发事务请求给Leader服务器
    参与事务请求Proposal的投票
    参与Leader选举投票
「Observer」:Observer是3.3.0 版本开始引入的一个服务器角色，它充当一个观察者角色——观察ZooKeeper集群的最新状态变化并将这些状态变更同步过来。
    其工作：处理客户端的非事务请求，转发事务请求给Leader服务器，不参与任何形式的投票
Zookeeper下Server工作状态
    服务器具有四种状态，分别是LOOKING、FOLLOWING、LEADING、OBSERVING。
    1.LOOKING：寻找Leader状态。当服务器处于该状态时，它会认为当前集群中没有Leader，因此需要进入Leader选举状态。
    2.FOLLOWING：跟随者状态。表明当前服务器角色是Follower。
    3.LEADING：领导者状态。表明当前服务器角色是Leader。
    4.OBSERVING：观察者状态。表明当前服务器角色是Observer。
```
### 14.ZooKeeper集群部署图，ZooKeeper是如何保证主从节点数据一致性的呢？
![](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzFFkTh1ZqMYxOHfdvVIATRpqf6IdWazIqAltZXeXIImvB3cd1rHKIg6Qo0l5KC0ouDQ7jVibKIwdLQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)
```markdown
ZooKeeper集群是一主多从的结构：
    如果是写入数据，先写入主服务器（主节点），再通知从服务器。
    如果是读取数据，既读主服务器的，也可以读从服务器的。
ZooKeeper如何保证主从节点数据一致性
    我们知道集群是主从部署结构，要保证主从节点一致性问题，无非就是两个主要问题：
「主服务器挂了，或者重启了」
「主从服务器之间同步数据」
Zookeeper是采用ZAB协议（Zookeeper Atomic Broadcast，Zookeeper原子广播协议）来保证主从节点数据一致性的，
ZAB协议支持「崩溃恢复和消息广播」两种模式，很好解决了这两个问题：
    崩溃恢复：Leader挂了，进入该模式，选一个新的leader出来
    消息广播：把更新的数据，从Leader同步到所有Follower
```
### 15.Zookeeper分布式锁的实现原理
```markdown
Zookeeper就是使用临时顺序节点特性实现分布式锁的。
    获取锁过程 （创建临时节点，检查序号最小）
    释放锁 （删除临时节点，监听通知）
获取锁过程
    当第一个客户端请求过来时，Zookeeper客户端会创建一个持久节点/locks。如果它（Client1）想获得锁，需要在locks节点下创建一个顺序节点lock1
    接着，客户端Client1会查找locks下面的所有临时顺序子节点，判断自己的节点lock1是不是排序最小的那一个，如果是，则成功获得锁。
    这时候如果又来一个客户端client2前来尝试获得锁，它会在locks下再创建一个临时节点lock2
    客户端client2一样也会查找locks下面的所有临时顺序子节点，判断自己的节点lock2是不是最小的，此时，发现lock1才是最小的，于是获取锁失败。
    获取锁失败，它是不会甘心的，client2向它排序靠前的节点lock1注册Watcher事件，用来监听lock1是否存在，也就是说client2抢锁失败进入等待状态。
    此时，如果再来一个客户端Client3来尝试获取锁，它会在locks下再创建一个临时节点lock3
    同样的，client3一样也会查找locks下面的所有临时顺序子节点，判断自己的节点lock3是不是最小的，发现自己不是最小的，就获取锁失败。
    它也是不会甘心的，它会向在它前面的节点lock2注册Watcher事件，以监听lock2节点是否存在。
释放锁:
    zookeeper的「客户端业务完成或者故障」，都会删除临时节点，释放锁。如果是任务完成，Client1会显式调用删除lock1的指令
    如果是客户端故障了，根据临时节点得特性，lock1是会自动删除的
    lock1节点被删除后，Client2可开心了，因为它一直监听着lock1。lock1节点删除，Client2立刻收到通知，也会查找locks下面的所有临时顺序子节点，发下lock2是最小，就获得锁。
    同理，Client2获得锁之后，Client3也对它虎视眈眈，啊哈哈~
```
### 16.dubbo和Zookeeper的关系，为什么选择Zookeeper作为注册中心
```markdown
dubbo的注册中心可以选Zookeeper，memcached，redis等。为什么选择Zookeeper，因为它的功能特性咯~
    命名服务，服务提供者向Zookeeper指定节点写入url，完成服务发布。
    负载均衡，注册中心的承载能力有限，而Zookeeper集群配合web应用很容易达到负载均衡。
    zk支持监听事件，特别适合发布/订阅的场景，dubbo的生产者和消费者就类似这场景。
    数据模型简单，数据存在内存，可谓高性能
    Zookeeper其他特点都可以搬出来讲一下~
```
### Zookeeper实现分布式锁
[如何用 Zookeeper 实现分布式锁？（附源码）](https://mp.weixin.qq.com/s?__biz=MzUxOTc4NjEyMw==&mid=2247484568&idx=1&sn=d2ae43f697a01d4f4a0a05c3b0e48649&chksm=f9f51f7cce82966a55e8bb51d54f78094112252cba489e77c7aa272c98c24ecbb4b004737af9&mpshare=1&scene=23&srcid=#rd)
[基于缓存或zookeeper的分布式锁实现](https://www.cnblogs.com/jmcui/p/11186224.html)
[zookeeper实现分布式锁总结，看这一篇足矣（设计模式应用实战）](https://www.cnblogs.com/sx-bj-srr/p/zookeeper.html)

### 
[SpringBoot 整合 Zookeeper 接入Starring微服务平台](https://www.cnblogs.com/laramia/p/11978271.html)


