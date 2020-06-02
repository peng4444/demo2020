# ElasticSearch学习
## 参考视频及资料

[参考视频1：详细ElasticSearch技术讲解](https://www.bilibili.com/video/av66259861)

[参考视频2：ElasticSearch6入门教程](E:\学习视频\004.ElasticSearch6入门教程（62集）)

【随笔分类 - ElasticSearch】
[ElasticSearch](https://www.cnblogs.com/supersnowyao/category/1236567.html)  

[ElasticSearch](https://www.cnblogs.com/shoufeng/category/1332134.html)

[随笔分类 - [06].搜索引擎](https://www.cnblogs.com/jajian/category/1280015.html)

[ElasticSearch6.X实战](https://www.cnblogs.com/yulinfeng/category/1505154.html)

### ElasticSearch简介
[ElasticSearch简介](https://www.cnblogs.com/haixiang/p/11078875.html)
```markdown
Elasticsearch是一个高度可扩展的开源全文搜索和分析引擎。它允许您快速，近实时地存储，搜索和分析大量数据。
它通常用作底层引擎、技术，为具有复杂搜索功能和要求的应用程序提供支持。
Elasticsearch是通过Lucene的倒排索引技术实现比关系型数据库更快的过滤。特别是它对多条件的过滤支持非常好。
Elasticsearch也使用Java开发并使用Lucene作为其核心来实现所有索引和搜索的功能，但是它的目的是通过简单的RESTfulAPI来隐藏Lucene的复杂性，从而让全文搜索变得简单。
优点
    - 具备横向可扩展性：只需要增加一台服务器，做些配置，启动 ES 进程就可以快速并入集群。'
    - 分片机制：同一个索引分成多个分片(sharding)，类似于 redis 中的分片，采取分而治之的思想来更好地解决问题。
    - 高可用：提供复制机制，一个分片可以设置多个复制，使得某台服务器宕机的话，集群依旧可以正常运行，并会把丢失的复制恢复到其它可用节点上。
缺点
    节点数据的一致性问题：其默认的机制是通过多播机制，同步元数据信息，但是在比较繁忙的集群中，可能会由于网络的阻塞，
    或者节点处理能力达到饱和导致各节点元数据不一致——也就是所谓的脑裂问题，这样会使集群处于不一致状态。
    目前并没有一个彻底的解决方案来解决这个问题，但是可以通过将工作节点与元数据节点分开的部署方案来缓解这种情况。
    没有细粒度的权限管理，没有像MySQL那样的分各种用户，每个用户又有不同的权限。
解决的问题
    - 更快的在大量数据中检索相关数据，性能远优于传统数据库
    - 结合分词器，根据关键词返回统计结果
```
### 七个生产案例告诉你BATJ为何选择ElasticSearch！应用场景和优势！
[七个生产案例告诉你BATJ为何选择ElasticSearch！应用场景和优势！](https://www.cnblogs.com/liuyanling/p/13023251.html)
```markdown
ElasticSearch的应用场景和优势，包括：
    - 日志实时分析
    - 搜索服务
    - 数据分析
    - 数据监控
    - 查询服务
    - 后端存储
ElasticSearch在腾讯的应用
ElasticSearch在腾讯的应用非常广泛，主要有三：日志实时分析场景、搜索服务、时序数据分析。
    搜索服务：例如像腾讯文档基于ES做全文检索，电商客户拼多多、蘑菇街等大量的商品搜索都是基ES。
    日志分析：这是ES应用最广泛的领域，支持全栈的日志分析，包括各种应用日志、数据库日志、用户行为日志、网络数据、安全数据等等。
        ES拥有一套完整的日志解决方案，可以秒级实现从采集到展示。
    时序分析：典型的场景是监控数据分析，比如云监控，整个腾讯云的监控都是基于ES的。
        此外还包括物联网场景，也有大量的时序数据。时序数据的特点是写入吞吐量特别高，ES支持的同时也提供了丰富的多维统计分析算子。
ElasticSearch在京东的应用
    通过京东的案例，聊一聊ES在查询、检索、数据分析方面的应用场景
    - 补充关系型数据库的结构化数据查询
        主要应用的业务是商品、促销、优惠券、订单、收银台、物流、对账、评论等大数据量查询。此场景的核心诉求是高性能、稳定性和高可用性，
        部分场景会有检索要求，通常用于加速关系型数据库，业务系统通过 binlog 同步或业务双写完成数据同步。
    - 全文检索功能
        主要的应用场景是应用、安全、风控、交易等操作日志，以及京东部分品类商品搜索。此类日志化场景对写要求很高，查询性能及高可用等要求相对较低，大的业务写会达到数千万/秒，存储以PB为单位来计算。
        这些场景对磁盘、内存有比较高的要求，因此，京东也做了相应优化，用于减少内存消耗，提升磁盘整体使用率，使用更廉价的磁盘来降低成本等等。
    - 实时数据分析引擎，形成统计报表
        主要应用的业务是物流单的各种分析、订单数据分析、用户画像等。因为业务数据分析纬度较多，flink、storm 等
        流式分析对于某些报表场景不太适用，批处理实时性又成为问题，所以近实时分析的Elasticsearch就成为了这些业务的选择。
ElasticSearch在去哪儿的应用
    - Elasticsearch分布式搜索储存集群的引入，就是为了解决订单数据的存储与搜索的问题。
    - 对订单模型进行抽象和分类，将常用搜索字段和基础属性字段剥离。DB做分库分表，存储订单详情；Elasticsearch存储搜素字段。
什么时候应该用ElasticSearch?
1、典型搜索场景：闭着眼用它！
2、典型日志分析场景：闭着眼用它！
3、关系型数据库查询有瓶颈：考虑下用它！为啥是考虑？ES的优点在于查询，然而实践证明，在被作为数据库来使用，即写完马上查询会有延迟。
4、数据分析场景：考虑下用它！为啥是考虑？简单通用的场景需求可以大规模使用，但在特定业务场景领域，还是要选择更加专业的数据产品，
    如复杂聚合，ClickHouse相比 Elasticserach 做亿级别数据深度聚合需求会更加合适。
ElasticSearch有什么优势呢？
1、很简便的横向扩容，分布式的架构，可以轻松地对资源进行横向纵向扩缩容，可以满足不同数据量级及查询场景对硬件资源的需求。
    能由数百台到万台机器搭建满足PB级的快速搜索，也能搭建单机版服务小公司。
2、查询速度快：ES底层采用Lucene作为搜索引擎，并在此之上做了多重优化，保证了用户对数据查询数据的需求。
    可"代替"传统关系型数据库，也可用于复杂数据分析，海量数据的近实时处理等。
3、相关性高：ES内部提供了完善的评分机制，会根据分词出现的频次等信息对文档进行相关性排序，保证相关性越高的文档排序越靠前。
    另外还提供了包括模糊查询，前缀查询，通配符查询等在内的多种查询手段，帮助用户快速高效地进行检索。
4、功能点多但使用比较简便，开箱即用，性能优化比较简单
5、生态圈丰富，社区活跃，适配多种工具。如下图，处理日志和输出到Elasticsearch，您可以使用日志记录工具，如Logstash（www.elastic.co/products/logstash），
    搜索和可视化界面分析这些日志，你可以使用Kibana（www.elastic.co/产品/kibana），即传说中的ELK技术栈。另外当前主流的大数据框架也几乎都支持ES，比如Flink和ES就是个完美搭档。
```


### 
[ES查询语句](https://www.cnblogs.com/lifengdi/p/11514463.html)

[[SpringBoot操作ES进行各种高级查询](https://www.cnblogs.com/keatsCoder/p/11341835.html)]

[基于MySQLBinlog的 Elasticsearch数据同步实践](https://www.cnblogs.com/mfwtech/p/11187516.html)

[ElasticSearch核心概念和文档的CRUD](https://www.cnblogs.com/haixiang/p/11181711.html)

[[关于Elasticsearch文档的描述以及如何操作文档的详细总结](https://www.cnblogs.com/lifengdi/p/11544169.html)]

[[【docker Elasticsearch】Rest风格的分布式开源搜索和分析引擎Elasticsearch初体验](https://www.cnblogs.com/lomtom/p/12584956.html)]

[Elasticsearch Java API 很全的整理以及架构剖析](https://www.cnblogs.com/laoqing/p/11693144.html)

[Elasticsearch系列文章](https://www.cnblogs.com/huangying2124/category/1626782.html)

[手把手教你搭建一个Elasticsearch集群](https://www.cnblogs.com/tianyiliang/p/10291305.html)
