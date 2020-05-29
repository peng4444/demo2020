# SQL&NoSQL等等
## MySQL

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
### Redis分布式锁,延时队列,位图bitmap,HyperLogLog,布隆过滤器

## HBase
[再谈全局网HBase八大应用场景](https://yq.aliyun.com/articles/558255?utm_content=m_45690)
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
