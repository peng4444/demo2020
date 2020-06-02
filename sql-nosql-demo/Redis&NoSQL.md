# Redis&NoSQL
[查看博客：随笔分类 - redis 系列篇](https://www.cnblogs.com/MrHSR/category/1318528.html)
[TOC]

## NoSQL
```
NoSQL是什么：Redis(REmote DIctionary Server 远程字典服务器)，Memcache,Mongdb
1、什么是NoSQL
NoSQL，泛指非关系型的数据库。随着互联网web2.0网站的兴起，传统的关系数据库在应付web2.0网站，特别是超大规模和高并发的SNS类型的web2.0纯动态网站已经显得力不从心，暴露了很多难以克服的问题，而非关系型的数据库则由于其本身的特点得到了非常迅速的发展。NoSQL数据库的产生就是为了解决大规模数据集合多重数据种类带来的挑战，尤其是大数据应用难题。
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
NoSQL数据库种类繁多，但是一个共同的特点都是去掉关系数据库的关系型特性。数据之间无关系，这样就非常容易扩展。也无形之间，在架构的层面上带来了可扩展的能力。

（2）大数据量
高性能NoSQL数据库都具有非常高的读写性能，尤其在大数据量下，同样表现优秀。这得益于它 的无关系性，数据库的结构简单。

（3）灵活的数据模型
NoSQL无需事先为要存储的数据建立字段，随时可以存储自定义的数据格式。而在关系数据库里，增删字段是一件非常麻烦的事情。如果是非常大数据量的表，增加字段简直就是一个噩梦。这点在大数据量的Web2.0时代尤其明显。

（4）高可用
NoSQL在不太影响性能的情况，就可以方便的实现高可用的架构。比如Cassandra, HBase模型，通过复制模型也能实现高可用。综上所述，NoSQL的非关系特性使其成为了后Web2.0时代的宠儿，助力大型Web2.0网站的再次起飞，是一项全新的数据库革命性运动。
```

```
NoSQL能够做什么：
KV，Cache，Persistence ，。。。。
```
## Redis

### Redis概述
```
　　Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。 它支持多种类型的数据结构，如 字符串（strings）， 散列（hashes）， 列表（lists）， 集合（sets）， 有序集合（sorted sets） 与范围查询， bitmaps， hyperloglogs 和 地理空间（geospatial） 索引半径查询。 Redis 内置了 复制（replication），LUA脚本（Lua scripting）， LRU驱动事件（LRU eviction），事务（transactions） 和不同级别的 磁盘持久化（persistence）， 并通过 Redis哨兵（Sentinel）和自动 分区（Cluster）提供高可用性（high availability）。

　　Redis是用ANSI C编写的，适用于大多数POSIX系统，如Linux，* BSD，OS X，没有外部依赖性。Linux和OS X是Redis开发和测试的两个操作系统，我们建议使用Linux进行部署。Redis可能在Solaris衍生系统（如SmartOS）中工作，但支持是最好的努力。Windows版本没有官方支持，但Microsoft开发并维护了Redis的Win-64端口。
Redis 优势
　　1. 性能极高 – Redis能读的速度是110000次/s,写的速度是81000次/s 。
　　2.丰富的数据类型 – Redis支持二进制案例的 Strings, Lists, Hashes, Sets 及 Ordered Sets 数据类型操作。
　　3.原子 – Redis的所有操作都是原子性的，同时Redis还支持对几个操作全并后的原子性执行。
　　4.丰富的特性 – Redis还支持 publish/subscribe, 通知, key 过期等等特性。
Redis启动：
# redis-server /myredis/redis.conf
-- 查看redis进程信息
# ps -ef|grep redis
# ps -ef|grep redis|grep -v grep
# redis-cli -p 6379
127.0.0.1:6379>ping
set k1 peng
get k1
DEL k1
set k1
set k1 v1
keys *       ##查询所有的值
quit         ##退出 关闭客户端
SHUTDOWN     ##退出 关闭服务器
单实例关闭： redis-cli shutdown
多实例关闭，指定端口关闭：redis-cli -p 6379 shutdown
```
### Redis链表& Redis用到的主要数据结构
```
Redis链表提供了高效的节点重排能力，以及顺序性的节点访问方式，并且可能通过增删节点来灵活地调整链表的长度。作为一种数据结构，在C语言中并没有内置的这种数据结构。所以Redis构建了自己的链表实现。链表在Redis中应用非常多，比如列表键的底层实现之一就是链表，当一个列表键包含了数量比较多的元素，又或者列表中包含的元素都是比较长的字符串时，Redis就会使用链表作为列表键的底层实现。
-- 例1：使用integers 列表键包含了从1到10，共有10个整数
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
integers列表键的底层实现就是一个链表。链表中的每个节点都保存了一个整数值，除了链表键之外，发布与订阅，慢查询，监视器等功能也用到了链表。Redis服务器本身还使用链表来保存多个客户端的状态信息，以及使用链表来构建客户端输出缓冲区(output buffer)。
Redis的链表实现的特性总结
　　(1)双端: 链表节点带有prev 和next 指针，获取某个节点的前置节点和后置节点的复杂度都是0(1) 。
　　(2)无环: 表头节点的prev指针和表尾节点的next 指针都指向null ,对链表的访问以null 为终点 。
　　(3)带表头指针和表尾指针：通过list结构的head指针和tail 指针，程序获取链接的表头节点和表尾节点的复杂度为0(1)
　　(4)带链表长度计数器：程序使用list结构的len属性来对list持有的链表节点进行计数，程序获取链表中节点数量的复杂度为0(1)
　　(5)多态:链表节点使用void* 指针来保存节点值，并且可能通过list结构的dup,free,match 三个属性为节点值设置类型特定函数，所以链表可以用于保存各种不同类型的值。
　Redis用到的主要数据结构，包括：简单动态字符串、链表(双端链表)、字典、跳跃表、 整数集合、压缩列表(后面再了解)。Redis没有直接使用这些数据结构来实现键值对数据库，而是基于这些数据结构创建一个对象系统，这个系统对象包括:字符串对象、列表对象、哈希对象(散列)、集合对象、有序集合对象这五种类型，每种类型对象都用到了至少一种前面所介绍的数据结构。
　　通过这五种不同类型的对象，可以针对不同的使用场景， 在Redis 内部会为对象设置不同的数据结构实现，从而优化对象在不同场景下的使用效率。下面先直观看下关系图(五种对象与type与encoding编码与ptr底层数据结构），然后再来详细介绍它们之间的关系。
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
```
总结：通过encoding属性来设定对象所使用的编码，而不是为特定类型的对象关联一种固定的编码，极大提升了redis的灵活性和效率。例如：上面演示的zaddfruit-price添加列表元素，redis使用压缩列表作为列表对象的底层实现，因为压缩列表比链表更节约内存，并且在元素数量较少时，在内存中以连续块方式保存的压缩列表比链表可以更快被载入到缓存中。但随着列表对象元素越来越多时，这种压缩优势就会消失，此时对象就会将底层实现从压缩列表转向链表。 其它类型的对象也会通过使用多种不同的编码来进行类似的优化。
使用对象key通过Type命令查看value值的对象类型，通过object encoding命令查看value值的底层数据结构。
```
## MongoDB技术

[[MongoDB非关系型数据库开发手册](https://www.cnblogs.com/yueshutong/p/11491106.html)]
