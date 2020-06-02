# SQL&NoSQL等等


## Redis
>> 参考书籍-[《Redis深度历险》]()
>> 参考博客-[敖丙在蘑菇街的技术分享](https://mp.weixin.qq.com/s?__biz=MzAwNDA2OTM1Ng==&mid=2453142767&idx=1&sn=940fb466ac45396c71ad71ebddb0bf40&chksm=8cf2de6cbb85577a469579c22a26639bb690399a83e8e4113be72486e40421b641480980f758&mpshare=1&scene=23&srcid=&sharer_sharetime=1590628507176&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
>> Redis主要作用就是缓存 五种基础类型
>> 高级用法,基础数据类型底层的，SDS动态字符，链表，字典，跳跃表,相关的底层衍生比如字典的渐进式hash，集合的升级降级。
### Redis可以做什么？
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
### 后端开发应该掌握的 Redis 基础
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
### 
##
