# Redis&NoSQL

[TOC]


## 一、NoSQL
```markdown
NoSQL是什么：Redis(REmote DIctionary Server 远程字典服务器)，Memcache,Mongdb
1、什么是NoSQL
NoSQL泛指非关系型的数据库。随着互联网web2.0网站的兴起，传统的关系数据库在应付web2.0网站，
特别是超大规模和高并发的SNS类型的web2.0纯动态网站已经显得力不从心，暴露了很多难以克服的问题，
而非关系型的数据库则由于其本身的特点得到了非常迅速的发展。
NoSQL数据库的产生就是为了解决大规模数据集合多重数据种类带来的挑战，尤其是大数据应用难题。
2、NoSQL解决的问题
（1）对数据库的高并发读写需求
（2）对海量数据的高效率存储及访问
（3）对数据库的高可扩展性和高可用性需求
3、主流的NoSQL产品
4、NoSql数据库的四大分类
（1）键值存储数据库
    相关产品：Tokyo Cabinet/Tyrant、Redis、Voldemort、Berkeley DB
    典型应用：内容缓存，主要用于处理大量数据的高访问负载
    数据模型：一系列的键值对
    优势：快速查询
    劣势：存储的数据缺少结构化
（2）列存储数据库
    相关产品：Cassandra，Hbase，Riak
    典型应用：分布式文件系统
    数据模型：以列簇式存储，将同一列数据存在一起
    优势：查找速度块，可扩展性强，更容易进行分布式扩展
    劣势：功能相对局限
（3）文档型数据库
    相关产品：CouchDB，MongoDB
    典型应用：与key-value类似，value是结构化的
    数据模型：一系列的键值对
    优势：数据结构要求不严格
    劣势：查询性能不高，而且缺乏统一查询语法
（4）图形数据库
    相关数据库：Neo4j，infogrid，infinite Graph
    典型应用：社交网络
    数据模型：图结构
    优势：利用图结构相关算法
    劣势：需要对整个图做计算才能得出结果，不容易做分布式的集群方案
5、NoSql特点
在大数据存取上具备关系型数据库无法比拟的性能优势，例如：
（1）易扩展
NoSQL数据库种类繁多，但是一个共同的特点都是去掉关系数据库的关系型特性。数据之间无关系，这样就非常容易扩展。
也无形之间，在架构的层面上带来了可扩展的能力。
（2）大数据量
高性能NoSQL数据库都具有非常高的读写性能，尤其在大数据量下，同样表现优秀。这得益于它 的无关系性，数据库的结构简单。
（3）灵活的数据模型
NoSQL无需事先为要存储的数据建立字段，随时可以存储自定义的数据格式。而在关系数据库里，增删字段是一件非常麻烦的事情。
如果是非常大数据量的表，增加字段简直就是一个噩梦。这点在大数据量的Web2.0时代尤其明显。
（4）高可用
NoSQL在不太影响性能的情况，就可以方便的实现高可用的架构。比如Cassandra, HBase模型，通过复制模型也能实现高可用。
综上所述，NoSQL的非关系特性使其成为了后Web2.0时代的宠儿，助力大型Web2.0网站的再次起飞，是一项全新的数据库革命性运动。
NoSQL能够做什么：
KV，Cache，Persistence ，。。。。
```
## Redis
[参考资料：随笔分类 - redis 系列篇](https://www.cnblogs.com/MrHSR/category/1318528.html)

[参考资料：随笔分类 - Redis详解](https://www.cnblogs.com/ysocean/category/1221478.html)
### Redis概述
```markdown
Redis:REmote DIctionary Server(远程字典服务)。是由意大利人Salvatore Sanfilippo（网名：antirez）开发的一款内存高速缓存数据库。
是完全开源免费的，用C语言编写的，遵守BSD协议，高性能的(key/value)分布式内存数据库，基于内存运行并支持持久化的NoSQL数据库。
Redis是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。 
支持多种类型的数据结构，如字符串（strings），散列（hashes），列表（lists），集合（sets），有序集合（sorted sets）与范围查询，
bitmaps，hyperloglogs 和 地理空间（geospatial）索引半径查询。Redis内置了复制（replication），LUA脚本（Lua scripting），
LRU驱动事件（LRU eviction），事务（transactions）和不同级别的 磁盘持久化（persistence），并通过 Redis哨兵（Sentinel）和自动 
分区（Cluster）提供高可用性（high availability）。
Redis优势
　　1. 性能极高 – Redis能读的速度是110000次/s,写的速度是81000次/s 。
　　2.丰富的数据类型 – Redis支持二进制案例的 Strings, Lists, Hashes, Sets 及 Ordered Sets 数据类型操作。
　　3.原子 – Redis的所有操作都是原子性的，同时Redis还支持对几个操作全并后的原子性执行。
　　4.丰富的特性 – Redis还支持 publish/subscribe, 通知, key 过期等等特性。
Redis的数据时在内存中的，所以读写速度非常快，因此redis被广泛用于缓存方向，另外，redis也经常用来做分布式锁。
redis提供了多种数据类型来支持不同的业务场景，除此之外，redis支持事务，持久化，LUA脚本，LRU驱动事件，多种集群方案。
Redis启动：【安装在centos101】
    # redis-server /usr/local/sofeware/redis/redis.conf --启动服务器
查看redis进程信息：
    # ps -ef|grep redis
    # ps -ef|grep redis|grep -v grep
连接服务器：# redis-cli -a redisadmin(密码)
    127.0.0.1:6379> ping
    set k1 peng
    get k1
    DEL k1
    set k1
    set k1 v1
    127.0.0.1:6379> keys *       ##查询所有的值
    127.0.0.1:6379> quit         ##退出 关闭客户端
    127.0.0.1:6379> SHUTDOWN     ##退出 关闭服务器
Redis关闭：
    　①、redis-cli shutdown：安全关闭，如果有密码需要加上-a {password}参数。
        redis -cli -a redisadmin shutdown （推荐使用此方式关闭，会进行持久化文件生成，能够防止数据丢失）
    　②、kill -9 pid：强制关闭，可能会造成Redis内存数据丢失（不推荐使用）。
    单实例关闭： redis-cli shutdown
    多实例关闭，指定端口关闭：redis-cli -p 6379 shutdown
```
### Redis链表&Redis用到的主要数据结构
```markdown
Redis链表提供了高效的节点重排能力，以及顺序性的节点访问方式，并且可能通过增删节点来灵活地调整链表的长度。
作为一种数据结构，在C语言中并没有内置的这种数据结构。所以Redis构建了自己的链表实现。
链表在Redis中应用非常多，比如列表键的底层实现之一就是链表，当一个列表键包含了数量比较多的元素，
又或者列表中包含的元素都是比较长的字符串时，Redis就会使用链表作为列表键的底层实现。
-- 例1：使用integers列表键包含了从1到10，共有10个整数
127.0.0.1:6379> rpush integers "1" "2" "3" "4" "5" "6" "7" "8" "9" "10"
(integer) 10
127.0.0.1:6379> llen integers
(integer) 10
127.0.0.1:6379> lrange integers 0 5
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
6) "6"
integers列表键的底层实现就是一个链表。链表中的每个节点都保存了一个整数值，除了链表键之外，发布与订阅，慢查询，监视器等功能也用到了链表。
Redis服务器本身还使用链表来保存多个客户端的状态信息，以及使用链表来构建客户端输出缓冲区(output buffer)。
Redis的链表实现的特性总结
　　(1)双端: 链表节点带有prev 和next 指针，获取某个节点的前置节点和后置节点的复杂度都是0(1) 。
　　(2)无环: 表头节点的prev指针和表尾节点的next 指针都指向null ,对链表的访问以null 为终点 。
　　(3)带表头指针和表尾指针：通过list结构的head指针和tail 指针，程序获取链接的表头节点和表尾节点的复杂度为0(1)
　　(4)带链表长度计数器：程序使用list结构的len属性来对list持有的链表节点进行计数，程序获取链表中节点数量的复杂度为0(1)
　　(5)多态:链表节点使用void* 指针来保存节点值，并且可能通过list结构的dup,free,match 三个属性为节点值设置类型特定函数，所以链表可以用于保存各种不同类型的值。
Redis用到的主要数据结构，包括：简单动态字符串、链表(双端链表)、字典、跳跃表、 整数集合、压缩列表(后面再了解)。
Redis没有直接使用这些数据结构来实现键值对数据库，而是基于这些数据结构创建一个对象系统，
这个系统对象包括:字符串对象、列表对象、哈希对象(散列)、集合对象、有序集合对象这五种类型，每种类型对象都用到了至少一种前面所介绍的数据结构。
通过这五种不同类型的对象，可以针对不同的使用场景，在Redis 内部会为对象设置不同的数据结构实现，从而优化对象在不同场景下的使用效率。
下面先直观看下关系图(五种对象与type与encoding编码与ptr底层数据结构），然后再来详细介绍它们之间的关系。
-- 值为字符串对象
127.0.0.1:6379> set msg "hello world"
OK
127.0.0.1:6379> type msg
string
-- 值为列表对象
127.0.0.1:6379> rpush number 1 3 5 
(integer) 3
127.0.0.1:6379> type number
list
-- 值为哈希对象    
127.0.0.1:6379> hmset profile name tom age 25 career programmer
OK
127.0.0.1:6379> type profile
hash
-- 值为集合对象
127.0.0.1:6379> sadd fruit apple banana cherry
(integer) 3
127.0.0.1:6379> type fruit
set
-- 值为有序集合对象
127.0.0.1:6379> zadd price 8.50 apple 3.30 banana
(integer) 2
127.0.0.1:6379> type price
zset
```
![redis数据类型](https://img2018.cnblogs.com/blog/151560/201811/151560-20181113164258101-454528589.png)
```markdown
总结：通过encoding属性来设定对象所使用的编码，而不是为特定类型的对象关联一种固定的编码，极大提升了redis的灵活性和效率。
例如：上面演示的zaddfruit-price添加列表元素，redis使用压缩列表作为列表对象的底层实现，因为压缩列表比链表更节约内存，
并且在元素数量较少时，在内存中以连续块方式保存的压缩列表比链表可以更快被载入到缓存中。
但随着列表对象元素越来越多时，这种压缩优势就会消失，此时对象就会将底层实现从压缩列表转向链表。
其它类型的对象也会通过使用多种不同的编码来进行类似的优化。
使用对象key通过Type命令查看value值的对象类型，通过object encoding命令查看value值的底层数据结构。
```
### Redis内存淘汰机制(数据淘汰策略)
```markdown
volatile-LRU:从已设置过期时间的数据集中挑选最近最少使用的数据淘汰。
volatile-TTL:从已设置过期时间的数据集中挑选将要过期的数据淘汰。
volatile-random:从已设置过期时间的数据集中任意选择数据淘汰。
allkeys-LRU:当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的key。(这个是最常用的)
allkeys-rendom:从数据集中任意选择淘汰数据。
no-eviction：禁止驱逐数据，也就是说当内存不足以容纳新写入的数据时，新写入操作会报错。（不会使用）
volatile-LFU:从已设置过期时间的数据集中挑选最不经常使用的数据淘汰。
allkeys-LRU:当内存不足以容纳新写入数据时，在键空间中，移除最不经常使用的key。(这个是最常用的)
```
### Redis持久化机制RDB与AOF
[详细分析Redis的持久化操作——RDB与AOF](https://www.cnblogs.com/tuyang1129/p/12776526.html)
>> Redis是一种内存型数据库，一旦服务器进程退出，数据库的数据就会丢失，为了解决这个问题，
    Redis提供了两种持久化的方案，将内存中的数据保存到磁盘中，避免数据的丢失。
```markdown
RDB(快照)持久化：保存某一个时间点的全量数据快照，保存到硬盘中，redis可以通过这个文件还原数据库当时的状态。
    可以手动执行。也可以在redis.conf中配置，定期执行。
    save:阻塞Redis的服务器进程，直到RDB文件被创建完毕。
    BGSAVE：Fork出一个子进程来创建RDB文件，不阻塞服务器进程。
    自动化触发RDB持久化的方式：
        根据redis.conf配置里的SAVE m n 定时触发（用的是BGSAVE） save m秒 n个操作
        主从复制时，主节点自动触发
        执行Debug Reload
        执行Shutdown且没有开启AOF持久化
    缺点：
        内存数据的全量同步，数据量大会由于I/O而严重影响性能。
        可能会因为Redis挂掉而丢失从当前值最近一次快照期间的数据。
AOF(Append-only-File) 持久化：记录服务器执行的所有变更操作命令（例如set del等），并在服务器启动时，
    通过重新执行这些命令来还原数据集AOF文件中的命令全部以redis协议的格式保存，新命令追加到文件末尾。
    redis.conf设置 appendonly yes  appendfsync everysec开启AOF功能
    日志重写解决AOF文件大小不断增大的问题：
        1.调用fork()，创建一个子进程
        2.子进程把新的AOF写到一个临时文件里，不依赖原来的AOF文件。
        3.主进程持续将新的变动同时写入到内存和原来的AOF里。
        4.主进程获取子进程重写AOF的完成信号，往新AOF同步增量变动。
        5.使用新的AOF文件替换掉旧的AOF文件。
RDB和AOF文件共存情况下的恢复流程
RDB和AOF的优缺点：
    RDB优点：全量数据快照，文件小，恢复快
    RDB缺点：无法保存最近一次快照之后的数据
    AOF优点：可读行高，适合保存增量数据，数据不易丢失。
    AOF缺点：文件体积大，恢复时间长。
Redis4.0:RDB-AOF混合持久化方式：    
    BGSAVE做镜像全量持久化，AOF做增量持久化。
```
### Redis事务
```markdown
事务是一个单独的隔离操作：事务中的所有命令都会序列化、按顺序地执行，事务在执行的过程中，不会被其他客户端发送来的命令请求所打断。
事务是一个原子操作：事务中的命令要么全部被执行，要么全部都不执行。
命令：MULTI、EXEC、DISCARD、WATCH、UNWATCH.
    DISCARD 取消事务，放弃执行事务块内的所有命令。
    EXEC    执行所有事务块内的命令。
    MULTI   标记一个事务块的开始。
    UNWATCH 取消 WATCH 命令对所有 key 的监视。
    WATCH key [key ...] 监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。
在传统的关系式数据库中，常常用ACID性质来检验事务功能的可靠性和安全性。在Redis中，
事务总是具有原子性（Atomicity)、一致性(Consistency)和隔离性（Isolation），
并且当Redis运行在某种特定的持久化模式下时，事务也具有持久性（Durability）。
```
### Redis主从同步（全量同步，增量同步）&Redis Sentinel
[Linux下的redis的持久化,主从同步及哨兵](https://www.cnblogs.com/qq752059037/p/10278296.html)
[深入分析Redis的主从复制机制](https://www.cnblogs.com/tuyang1129/p/12781631.html)
```markdown
全量同步过程：
    1.Slave发送sync命令到Master
    2.Master启动一个后台进程，将Redis中的数据快照保存到文件中
    3.Master将保存的数据快照期间接收到的写命令缓存起来
    4.Master完成写文件操作后，将该文件发送给Salve
    5.使用新否AOF文件替换掉旧的AOF文件
    6.Master将这个期间的增量写命令发送给Salve端
增量同步过程：
    1.Master接收到用户的操作指令，判断是否需要传播到Slave
    2.将操作记录加到AOF文件
    3.将操作传播到其他SLave：1.对齐主从库；2.往响应缓存写入指令
    4.将缓存中的数据发送给Slave
解决主从同步Master宕机后的主从切换问题：
    监控：检查主从服务器是否运行正常。
    提醒：通过API向管理员或者其他应用程序发送故障通知。
    找到故障迁移：主从切换
redis哨兵(redis-sentinel):哨兵进行检测，主从架构是否正常，如果主库挂掉，哨兵会自动的修改redis.conf，进行添加/删除slaveof指令。
```
### Redis集群
[分布式缓存 Redis 集群搭建](https://www.cnblogs.com/esofar/p/10486621.html)
[深入学习Redis（5）：集群](https://www.cnblogs.com/kismetv/p/9853040.html)
```markdown
Redis Sentinal:着眼于高可用，在master宕机时会自动将slave提升为master，继续提供服务。
Redis Cluster:着眼于扩展性，在单个redis内存不足时，使用Cluster进行分片存储。
Slot：插槽，可以存储两个数值的一个变量这个变量的取值范围是：0-16383。
Cluster：集群管理者，使集群对外暴漏的是一个整体。
redis cluster：采用虚拟分区的方式，将整个集群看成一个整体，然后分成16384个槽位。
    然后再将16484个槽位分别分配给集群的各个节点，然后各个节点各自负责一部分槽位。
原理：节点1负责 0-5000之间的槽位，节点2负责5001-10000之间的槽位，节点3负责10001-16383之间的槽位。
    k-v键值对数据只会和槽位相关，与物理机器无关。通过crc16算法计算出 k对应的整数值（有点类似hash），然后对算出的整数值%16384取模，
    计算出k-v对应在哪个槽位上，然后再根据槽位与机器节点的映射关系，存储到相应的节点上去。
    取的时候，也是相应的过 程所以整个集群协同一致对外，给client看到的视图就是完整的数据集。
```
### 缓存雪崩和缓存穿透问题解决⽅案
```markdown
缓存雪崩我们可以简单的理解为：由于原有缓存失效，新缓存未到期间(例如：我们设置缓存时采用了相同的过期时间，在同一时刻出现大面积的缓存过期)，
所有原本应该访问缓存的请求都去查询数据库了，而对数据库CPU和内存造成巨大压力，严重的会造成数据库宕机。从而形成一系列连锁反应，造成整个系统崩溃。
解决办法：
大多数系统设计者考虑用加锁（ 最多的解决方案）或者队列的方式保证来保证不会有大量的线程对数据库一次性进行读写，
从而避免失效时大量的并发请求落到底层存储系统上。还有一个简单方案就时讲缓存失效时间分散开。
缓存穿透是指用户查询数据，在数据库没有，自然在缓存中也不会有。这样就导致用户查询的时候，在缓存中找不到，
每次都要去数据库再查询一遍，然后返回空（相当于进行了两次无用的查询）。这样请求就绕过缓存直接查数据库，这也是经常提的缓存命中率问题。
解决办法:
最常见的则是采用布隆过滤器，将所有可能存在的数据哈希到一个足够大的bitmap中，一个一定不存在的数据会被这个bitmap拦截掉，
从而避免了对底层存储系统的查询压力。
另外也有一个更为简单粗暴的方法，如果一个查询返回的数据为空（不管是数据不存在，还是系统故障），我们仍然把这个空结果进行缓存，
但它的过期时间会很短，最长不超过五分钟。通过这个直接设置的默认值存放到缓存，这样第二次到缓冲中获取就有值了，
而不会继续访问数据库，这种办法最简单粗暴。
5TB的硬盘上放满了数据，请写一个算法将这些数据进行排重。如果这些数据是一些32bit大小的数据该如何解决？如果是64bit的呢？
对于空间的利用到达了一种极致，那就是Bitmap和布隆过滤器(Bloom Filter)。
Bitmap：典型的就是哈希表
缺点是，Bitmap对于每个元素只能记录1bit信息，如果还想完成额外的功能，恐怕只能靠牺牲更多的空间、时间来完成了。
布隆过滤器（推荐）
就是引入了k(k>1)k(k>1)个相互独立的哈希函数，保证在给定的空间、误判率下，完成元素判重的过程。
它的优点是空间效率和查询时间都远远超过一般的算法，缺点是有一定的误识别率和删除困难。
Bloom-Filter算法的核心思想就是利用多个不同的Hash函数来解决“冲突”。
Hash存在一个冲突（碰撞）的问题，用同一个Hash得到的两个URL的值有可能相同。为了减少冲突，我们可以多引入几个Hash，如果通过其中的一个Hash值我们得出某元素不在集合中，那么该元素肯定不在集合中。只有在所有的Hash函数告诉我们该元素在集合中时，才能确定该元素存在于集合中。这便是Bloom-Filter的基本思想。
Bloom-Filter一般用于在大数据量的集合中判定某元素是否存在。
> get locknx
> setnx locknx test 
> expire locknx 2 # 设置过期时间
> set locktarget 12345 ex 10 nx  # 对值设置过期时间
定期删除：redis默认是每隔100ms就随机抽取一些设置了过期时间的key，检查其是否过期，如果过期就删除。注意这里是随机抽取的。
    为什么要随机呢？你想一想假如redis存了几十万个key ，每隔100ms就遍历所有的设置过期时间的key的话，就会给CPU带来很大的负载！
惰性删除 ：定期删除可能会导致很多过期key到了时间并没有被删除掉。所以就有了惰性删除。假如你的过期key，靠定期删除没有被删除掉，
    还停留在内存里，除非你的系统去查一下那个key，才会被redis给删除掉。这就是所谓的惰性删除，也是够懒的哈！

```
### 简单说一说缓存预热和缓存降级？
```markdown
除了缓存服务器自带的缓存失效策略之外（Redis默认的有6中策略可供选择），我们还可以根据具体的业务需求进行自定义的缓存淘汰，常见的策略有两种：
    （1）定时去清理过期的缓存；
    （2）当有用户请求过来时，再判断这个请求所用到的缓存是否过期，过期的话就去底层系统得到新数据并更新缓存。
两者各有优劣，第一种的缺点是维护大量缓存的key是比较麻烦的，第二种的缺点就是每次用户请求过来都要判断缓存失效，逻辑相对比较复杂！
具体用哪种方案，大家可以根据自己的应用场景来权衡。
当访问量剧增、服务出现问题（如响应时间慢或不响应）或非核心服务影响到核心流程的性能时，仍然需要保证服务还是可用的，即使是有损服务。
系统可以根据一些关键数据进行自动降级，也可以配置开关实现人工降级。
降级的最终目的是保证核心服务可用，即使是有损的。而且有些服务是无法降级的（如加入购物车、结算）。
以参考日志级别设置预案：
（1）一般：比如有些服务偶尔因为网络抖动或者服务正在上线而超时，可以自动降级；
（2）警告：有些服务在一段时间内成功率有波动（如在95~100%之间），可以自动降级或人工降级，并发送告警；
（3）错误：比如可用率低于90%，或者数据库连接池被打爆了，或者访问量突然猛增到系统能承受的最大阀值，此时可以根据情况自动降级或者人工降级；
（4）严重错误：比如因为特殊原因数据错误了，此时需要紧急人工降级。
服务降级的目的，是为了防止Redis服务故障，导致数据库跟着一起发生雪崩问题。因此，对于不重要的缓存数据，可以采取服务降级策略，
例如一个比较常见的做法就是，Redis出现问题，不去数据库查询，而是直接返回默认值给用户。
```
### 如何解决Redis的并发竞争Key问题
```markdown
所谓Redis的并发竞争Key的问题也就是多个系统同时对一个key进行操作，但是最后执行的顺序和我们期望的顺序不同，这样也就导致了结果的不同！
推荐一种方案：分布式锁（zookeeper和redis都可以实现分布式锁）。（如果不存在Redis的并发竞争Key问题，不要使用分布式锁，这样会影响性能）
基于zookeeper临时有序节点可以实现的分布式锁。
大致思想为：每个客户端对某个方法加锁时，在zookeeper上的与该方法对应的指定节点的目录下，生成一个唯一的瞬时有序节点。 
判断是否获取锁的方式很简单，只需要判断有序节点中序号最小的一个。 当释放锁的时候，只需将这个瞬时节点删除即可。
同时，其可以避免服务宕机导致的锁无法释放，而产生的死锁问题。完成业务流程后，删除对应的子节点释放锁。
在实践中，当然是从以可靠性为主。所以首推Zookeeper。
```
### 如何保证缓存与数据库双写时的数据⼀致性?
```markdown
你只要用缓存，就可能会涉及到缓存与数据库双存储双写，你只要是双写，就一定会有数据一致性的问题，那么你如何解决一致性问题？
一般来说，就是如果你的系统不是严格要求缓存+数据库必须一致性的话，缓存可以稍微的跟数据库偶尔有不一致的情况，
最好不要做这个方案，读请求和写请求串行化，串到一个内存队列里去，这样就可以保证一定不会出现不一致的情况
串行化之后，就会导致系统的吞吐量会大幅度的降低，用比正常情况下多几倍的机器去支撑线上的一个请求。
还有一种方式就是可能会暂时产生不一致的情况，但是发生的几率特别小，就是先更新数据库，然后再删除缓存。
这种情况不存在并发问题么？
不是的。假设这会有两个请求，一个请求A做查询操作，一个请求B做更新操作，那么会有如下情形产生
（1）缓存刚好失效
（2）请求A查询数据库，得一个旧值
（3）请求B将新值写入数据库
（4）请求B删除缓存
（5）请求A将查到的旧值写入缓存
ok，如果发生上述情况，确实是会发生脏数据。然而，发生这种情况的概率又有多少呢？
发生上述情况有一个先天性条件，就是步骤(3)的写数据库操作比步骤(2)的读数据库操作耗时更短，才有可能使得步骤（4）先于步骤（5）。
数据库的读操作的速度远快于写操作的（不然做读写分离干嘛，做读写分离的意义就是因为读操作比较快，耗资源少），因此步骤(3)耗时比步骤(2)更短，这一情形很难出现。
如何解决上述并发问题？
首先，给缓存设有效时间是一种方案。其次，采用异步延时删除策略，保证读请求完成以后，再进行删除操作。
```
### 如何通过Redis实现分布式锁
[Redis与Zookeeper实现分布式锁的区别](https://www.cnblogs.com/mengchunchen/p/9647756.html)
>> redis分布式锁，其实需要自己不断去尝试获取锁，比较消耗性能
zk分布式锁，获取不到锁，注册个监听器即可，不需要不断主动尝试获取锁，性能开销较小
另外一点就是，如果是redis获取锁的那个客户端bug了或者挂了，那么只能等待超时时间之后才能释放锁；
而zk的话，因为创建的是临时znode，只要客户端挂了，znode就没了，此时就自动释放锁
```markdown
1.根据lockKey区进行setnx（set not exist，如果key值为空，则正常设置，返回1，否则不会进行设置并返回0）操作，如果设置成功，表示已经获得锁，否则并没有获取锁。
2.如果没有获得锁，去Redis上拿到该key对应的值，在该key上我们存储一个时间戳（用毫秒表示，t1），
    为了避免死锁以及其他客户端占用该锁超过一定时间（5秒），使用该客户端当前时间戳，与存储的时间戳作比较。
3.如果没有超过该key的使用时限，返回false，表示其他人正在占用该key，不能强制使用；如果已经超过时限，那我们就可以进行解锁，使用我们的时间戳来代替该字段的值。
4.但是如果在setnx失败后，get该值却无法拿到该字段时，说明操作之前该锁已经被释放，这个时候，最好的办法就是重新执行一遍setnx方法来获取其值以获得该锁。
```
### Redis大量的key同时过期的注意事项
```markdown
集中过期，由于清除大量的key很耗时，会出现短暂的卡顿现象
解决方案:在设置过期时间的时候，给每个key加上随机值。
```
### Redis如何实现异步队列
```markdown
使用List作为队列，RPUSH生产消息，LPOP消费消息
缺点：没有等待队列里有值就直接消费
弥补：可以通过在应用层引入Sleep机制去调用LPOP重试。
BLPOP key [key ...] timeout :阻塞直到队列有消息或者超时。
缺点：只能供一个消费者消费。
pub/sub:主题订阅者模式
缺点：消息的发布是无状态的，无法保证可达。
```
>> 参考书籍-[《Redis深度历险》]()
>> 参考博客-[敖丙在蘑菇街的技术分享](https://mp.weixin.qq.com/s?__biz=MzAwNDA2OTM1Ng==&mid=2453142767&idx=1&sn=940fb466ac45396c71ad71ebddb0bf40&chksm=8cf2de6cbb85577a469579c22a26639bb690399a83e8e4113be72486e40421b641480980f758&mpshare=1&scene=23&srcid=&sharer_sharetime=1590628507176&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
>> Redis主要作用就是缓存 五种基础类型
>> 高级用法,基础数据类型底层的，SDS动态字符，链表，字典，跳跃表,相关的底层衍生比如字典的渐进式hash，集合的升级降级。
### Redis可以做什么？应用场景
```markdown
博客哪些场景会用到redis：
1.记录帖子的点赞数、评论数和点击数 (hash)。
2.记录用户的帖子 ID 列表 (排序)，便于快速显示用户的帖子列表 (zset)。
3.记录帖子的标题、摘要、作者和封面信息，用于列表页展示 (hash)。
4.记录帖子的点赞用户 ID 列表，评论 ID 列表，用于显示和去重计数 (zset)。
5.缓存近期热帖内容 (帖子内容空间占用比较大)，减少数据库压力 (hash)。
6.记录帖子的相关文章 ID，根据内容推荐相关帖子 (list)。
7.如果帖子 ID 是整数自增的，可以使用 Redis 来分配帖子 ID(计数器)。
8.收藏集和帖子之间的关系 (zset)。
9.记录热榜帖子 ID 列表，总热榜和分类热榜 (zset)。
10.缓存用户行为历史，进行恶意行为过滤 (zset,hash)。
11.数据推送去重Bloom filter
12.pv，uv统计
```
### 后端开发应该掌握的Redis基础
[后端开发应该掌握的 Redis 基础](https://mp.weixin.qq.com/s?__biz=MzUxOTc4NjEyMw==&mid=2247485057&idx=1&sn=cede5e1f9d08e2c7ea54b371f3fc7be1&chksm=f9f51d65ce829473f621a2d748ea6d776f10de42ce633fbfea05a7fdd2ce3bb8e83c02de89ab&mpshare=1&scene=23&srcid=&sharer_sharetime=1571895796183&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
```markdown
Redis的数据结构
    五种基础数据结构
        String：字符串，是构建其他数据结构的基础
        Hash：哈希列表
        List：列表
        Set：集合，在哈希列表的基础上实现
        Sort Set：有序集合
    复杂的数据结构
        Bitmaps:位图，在string的基础上进行位操作，可以实现节省空间的数据结构。
        Hyperloglog：用于估计一个 set 中元素数量的概率性的数据结构。
        Geo：geospatial,地理空间索引半径查询。
        BloomFilter：布隆过滤器。
Redis的通用命令
    keys:列出Redis所有的key  > keys hello*  以hello开头的key
    exists:判断一个或多个key是否存在  > exists test1 test2  (integer) 1 #只有一个key存在
    del:删除一个或多个key，多个key之间用空格分隔，其返回值为整数。 > del test1  (integer) 1
    expire,pexpire:expire设置key在多少秒之后过期，pexpire设置key在多少毫秒之后过期,成功返回1，失败返回0。
        > expire test 10 #设置10秒后过期   > pexpire test1_value 10000 #设置10000毫秒(10s)后过期
    ttl和pttl:获取key的过期时间，其返回值为整型。
        1.当key不存在或过期时间，返回-2。 > ttl test # test已过期 (integer) -2
        2.当key存在且永久有效时，返回-1。 > ttl test1 (integer) -1
        3.当key有设置过期时间时，返回为剩下的秒数(pttl为毫秒数) > ttl test (integer) 98#返回剩下的秒数
    expireat,pexpireat：设置key在某个时间戳过期,expreat参数时间戳用秒表示，
        而pexpireat则用毫秒表示，与expire和pexpire功能类似，返回1表示成功，0表示失败。
        > expireat test 1560873600 # 2019-06-19 00:00:00   (integer) 1
        > pexpireat test1 156087360000 # 2019-06-19 00:00:00的毫秒表示  (integer) 1
    persis：移除key的过期时间，将key设置为永久有效，当key设置了过期时间，使用persist命令移除后返回1，如果key不存在或本身就是永久有效的，则返回0。
        > persist test  (integer) 1   > persist test   (integer) 0 # 对永久有效或不存在的key使用persist命令，返回
    type：判断key是什么类型的数据结构,返回值为string,list,set,hash,zset,
        > type test  string   > hset htest test test  > type htest   hash
小结
    上面介绍的是Redis中最常用的通用命令，虽然简单，但还是非常有必要掌握其用法和使用方面要注意的事项，
    其实，对于普通开发人员来说，很多时候，也只是使用这些基础通用的命令来操作Redis而已。
```
### Redis分布式锁,延时队列,位图bitmap,HyperLogLog,布隆过滤器
```markdown

```

[灵感来袭，基于Redis的分布式延迟队列]( https://www.cnblogs.com/hujunzheng/p/12587572.html )

[[Redis缓存设计与性能优化](https://www.cnblogs.com/nijunyang/p/12587429.html)]

[[Spring优雅整合Redis缓存](https://www.cnblogs.com/xxbiao/p/12593525.html)]
### Redis 常见性能问题和解决方案？
```markdown
(1) Master最好不要做任何持久化工作，如RDB内存快照和AOF日志文件
(2) 如果数据比较重要，某个Slave开启AOF备份数据，策略设置为每秒同步一次
(3) 为了主从复制的速度和连接的稳定性，Master和Slave最好在同一个局域网内
(4) 尽量避免在压力很大的主库上增加从库
(5) 主从复制不要用图状结构，用单向链表结构更为稳定，即：Master<-Slave1<-Slave2<-Slave3…
```

### Redis命令操作
[Redis命令参考](http://redisdoc.com/index.html)
[Redis不是只有get set那么简单](https://www.cnblogs.com/CodeBear/p/12402932.html)

### Redis面试题
#### 1.Redis为什么是单线程的和Redis为什么这么快
[Redis为什么是单线程的](https://www.cnblogs.com/tuyang1129/p/12822501.html)
[为什么单线程的Redis这么快？](https://www.cnblogs.com/haha12/p/10470786.html)
```markdown
Redis的单线程，不是指Redis程序真的只会有一个线程。Redis在执行其他操作的时候，可能会开启多个进程或线程，比如说持久化。
指的是Redis处理客户端发来的数据操作请求（增删改查），只会使用一个线程去执行。
    因为Redis是基于内存的操作，CPU不是Redis的瓶颈，Redis的瓶颈最有可能是机器内存的大小或者网络带宽。
    既然单线程容易实现，而且CPU不会成为瓶颈，那就顺理成章地采用单线程的方案了。
多线程并不能有效提升Redis的性能，相反可能还会降低性能，所以自然而然使用单线程。
原因主要是以下三点：
    - Redis是纯内存数据库，一般都是简单的存取操作，线程占用的时间很多，时间的花费主要集中在IO上，所以读取速度快。
    - Redis使用的是非阻塞IO、IO多路复用，使用了单线程来轮询描述符，将数据库的开、关、读、写都转换成了事件，减少了线程切换时上下文的切换和竞争。
    - Redis采用了单线程的模型，保证了每个操作的原子性，也减少了线程的上下文切换和竞争。
    - Redis避免了多线程的锁的消耗。
    - Redis采用自己实现的事件分离器，效率比较高，内部采用非阻塞的执行方式，吞吐能力比较大。
```
#### 7.Redis持久化中save和BGSAVE执行流程和区别
```markdown
通过配置文件执行快照持久化的方式，实际上就是Redis在判断满足条件时，调用BGSAVE或者save指令来实现的。
Save执行流程：
    Redis执行SAVE指令时，不会创建一个子进程，异步的生成快照文件，而是直接使用Redis当前进程。
    执行SAVE指令在创建快照的过程中，Redis服务器会阻塞所有的Redis客户端，直到快照生成完毕，并更新到磁盘之后，
    才会继续执行客户端发来的增删改查的指令。肯会阻塞客户端，造成停顿。执行效率一般比BGSAVE更高，因为不需要创建子进程。
BGSAVE的执行流程如下：
    1.Redis调用系统的fork()，创建出一个子进程；
    2.子进程将当前Redis中的数据，写入到一个临时文件中；同时父进程不受影响，继续执行客户端的请求；
    3.子进程将所有的数据写入到了临时文件后，于是使用这个文件替换原来的快照文件（默认是dump.rdb）；
```
[面试前必须要知道的Redis面试题](https://www.cnblogs.com/Java3y/p/10266306.html)
[Redis的最常被问到知识点总结](https://www.cnblogs.com/Young111/p/11518346.html)
[Redis面试题集锦（精选）](https://www.cnblogs.com/coder-programming/p/12458686.html)

### Redis操作demo
>> 安装redis在centos101，密码 redisadmin
[参考资料：Springboot+VUE全栈开发实战-第六章Redis]()

[Redis高可用架构](https://www.cnblogs.com/jimersylee/p/11458520.html)
[Redis 的底层数据结构（SDS和链表）](https://www.cnblogs.com/yangming1996/p/11521492.html)
[Redis的内存和实现机制](https://www.cnblogs.com/panlq/p/13098786.html)
[springboot+redis+Interceptor+自定义annotation实现接口自动幂等](https://www.cnblogs.com/wyq178/p/11130034.html)
[以商品超卖为例讲解Redis分布式锁](https://www.cnblogs.com/vandusty/p/11561160.html)


### [看完这篇Redis缓存三大问题，保你能和面试官互扯。](https://mp.weixin.qq.com/s?__biz=Mzg2NzA4MTkxNQ==&mid=2247487744&idx=2&sn=229eae99316099e4ab37f62e8acc137c&chksm=ce405ad4f937d3c2b5d97d59cbb828aec7d03fcf3ef6328d88c3ff492d838785ab948ce57085&mpshare=1&scene=23&srcid=&sharer_sharetime=1590070257441&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)


### 待阅读
[学习Redis好一阵了，我对它有了一些新的看法](https://www.cnblogs.com/tanshaoshenghao/p/13063886.html)

[随笔分类 - redis、分布式锁的三种方式](https://www.cnblogs.com/ft535535/category/1366532.html)

[Redis实现分布式锁（设计模式应用实战）](https://www.cnblogs.com/sx-bj-srr/p/distributedLock.html)

[基于redis实现分布式锁](https://www.cnblogs.com/zhangxinhua/p/13023449.html)

[Redis之分布式锁实现](https://www.cnblogs.com/aobing/p/12694508.html)

[Redis实现的分布式锁和分布式限流](https://www.cnblogs.com/huangqingshi/p/10290615.html)

[关于分布式锁原理的一些学习与思考-redis分布式锁，zookeeper分布式锁](https://www.cnblogs.com/JJJ1990/p/10496850.html)

[Redis服务之高可用组件sentinel](https://www.cnblogs.com/qiuhom-1874/p/13429776.html)
### 相关博客
[从源码研究如何不重启Springboot项目实现redis配置动态切换](https://www.cnblogs.com/breakingdawn/p/13043921.html)

## MongoDB技术
[[MongoDB非关系型数据库开发手册](https://www.cnblogs.com/yueshutong/p/11491106.html)]

[MongoDB 4.X CRUD基本操作](https://www.cnblogs.com/dbabd/p/13045006.html)

## HBase
### [再谈全局网HBase八大应用场景](https://yq.aliyun.com/articles/558255?utm_content=m_45690)
```markdown
HBase是一个分布式存储、数据库引擎，可以支持千万的QPS、PB级别的存储，这些都已经在生产环境验证，并且在广大的公司已经验证。
HBase场景:
    - 对象存储：我们知道不少的头条类、新闻类的的新闻、网页、图片存储在HBase之中，一些病毒公司的病毒库也是存储在HBase之中
    - 时序数据：HBase之上有OpenTSDB模块，可以满足时序类场景的需求
    - 推荐画像：特别是用户的画像，是一个比较大的稀疏矩阵，蚂蚁的风控就是构建在HBase之上
    - 时空数据：主要是轨迹、气象网格之类，滴滴打车的轨迹数据主要存在HBase之中，另外在技术所有大一点的数据量的车联网企业，数据都是存在HBase之中
    - CubeDB OLAP：Kylin一个cube分析工具，底层的数据就是存储在HBase之中，不少客户自己基于离线计算构建cube存储在hbase之中，满足在线报表查询的需求
    - 消息/订单：在电信领域、银行领域，不少的订单查询底层的存储，另外不少通信、消息同步的应用构建在HBase之上
    - Feeds流：典型的应用就是xx朋友圈类似的应用
    - NewSQL：之上有Phoenix的插件，可以满足二级索引、SQL的需求，对接传统数据需要SQL非事务的需求
    - 更多的场景需要不断挖掘
```
[我终于看懂了HBase，太不容易了...](https://www.cnblogs.com/Java3y/p/13037056.html)

###

##
