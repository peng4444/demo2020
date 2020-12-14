# kafka学习总结

## kafka
[Apache Kafka源码](https://github.com/apache/kafka)
[参考书籍：Kafka权威指南](https://www.baidu.com)
[源码分析 Kafka](https://www.cnblogs.com/dingwpmz/category/1624446.html)
### Kafka简介
```markdown
Apache Kafka是由Apache开源的消息引擎系统。逐渐演变成现在分布式的流处理平台。
    分布式消息中间件，分布式日志处理中间件。
关键功能：
    发布和订阅记录流，类似于消息队列或者企业消息传递系统
    以容错的持久方式存储记录流
    处理记录流
    很适合用来从多个前端系统收集数据，并以统一的格式对外提供数据。
```
### Kafka安装配置
[Kafka常用命令](https://blog.csdn.net/qq_33689414/article/details/81046502)
[Kafka实战](https://blog.csdn.net/u013291394/article/details/50231681)
```markdown
- 安装zookeeper
tar -zxvf zookeeper-3.4.14.tar.gz -C /usr/local/
- 安装目录 /usr/local/zookeeper
- 数据目录 /var/lib/zookeeper
mv zookeeper-3.4.14 zookeeper
cd /zookeeper/conf/kafka
cp zoo_sample.cfg zoo.cfg
vi zoo.cfg
dataDir=/var/lib/zookeeper
- 启动
./bin/zkServer.sh start
- 安装 kafka
tar -zxvf kafka_2.11-2.1.1.tgz -C /usr/local/
mv kafka_2.11-2.1.1.tgz kafka
mkdir /tmp/kafka-logs   #kafka日志文件目录
-  启动kafka
 cd kafka
 ./bin/kafka-server-start.sh -daemon /usr/local/kafka/config/server.properties
- 验证kafka安装
    jps
- 配置broker配置
```
### kafka的topic相关命令
```markdown
# 创建topic
$ ./bin/kafka-topics.sh --zookeeper 192.168.101.206:2181 --create --topic pbj-topic-1 --replication-factor 1 --partitions 3 
# 删除topic
$ ./bin/kafka-topics.sh --zookeeper 192.168.101.206:2181 --delete --topic pbj-topic-1
# 修改topic的分区，注意：分区数量只能增加，不能减少
$ ./bin/kafka-topics.sh --zookeeper 192.168.101.206:2181 --alter --topic pbj-topic-1 --partitions 5
# 查看所有topic
$ ./bin/kafka-topics.sh --zookeeper 192.168.101.206:2181 --list
# 查看所有topic的详细信息
$ ./bin/kafka-topics.sh --zookeeper 192.168.101.206:2181 --describe
```
### kafka基本架构
[深入分析Kafka架构（一）：工作流程、存储机制、分区策略](https://blog.csdn.net/qq_26803795/article/details/105489068)
[深入分析Kafka架构（二）：数据可靠性、故障处理](https://blog.csdn.net/qq_26803795/article/details/105515161)
[深入分析Kafka架构（三）：消费者消费方式、三种分区分配策略、offset维护](https://blog.csdn.net/qq_26803795/article/details/105562691)
[深入分析Kafka工作流程、存储机制、分区策略](https://ropledata.blog.csdn.net/article/details/109265052)
```markdown
Broker：一个kafka节点就是一个broker，多个broker组成一个kafka集群。一个broker可以是一个单机器kafka服务器。
Topic：存放消息的主题，相当于一个队列。可以理解为存放消息的分类，比如你可以有前端日志的Topic，后端日志的Topic。可以理解为MySQL里的表。
Partition：一个topic可以划分为多个partition，每个partition都是一个有序队列。把topic主题中的消息进行分拆，均摊到kafka集群中不同机器上。partition是topic的进一步拆分。
Replica：副本消息。kafka可以以partition为单位，保存多个副本，分散在不同的broker上。副本数是可以设置的。
Segment: 一个Partition被切分为多个Segment，每个Segment包含索引文件和数据文件。
Message：kafka里最基本消息单元。
一个kafka集群可以由多个broker组成，每个broker是一个节点，你创建一个topic，这个topic可以划分为多个partition，每个partition可以存储在不同的broker上，每个partition存放一部分数据。
一些kafka术语：
    主题（Topic）：发布订阅的对象是主题（Topic），可以为每个业务、每个应用甚至是每类数据都创建专属的主题。
    生产者（Producer）：向主题发布消息的客户端应用程序称为生产者（Producer），生产者程序通常持续不断地向一个或多个主题发送消息。
    消费者（Consumer）：订阅这些主题消息的客户端应用程序就被称为消费者（Consumer），消费者也能够同时订阅多个主题的消息。
    客户端（Clients）：把生产者和消费者统称为客户端（Clients）。可以同时运行多个生产者和消费者实例，这些实例会不断地向Kafka集群中的多个主题生产和消费消息。
    服务器端：Kafka的服务器端由被称为Broker的服务进程构成，即一个Kafka集群由多个Broker组成，Broker负责接收和处理客户端发送过来的请求，以及对消息进行持久化。
    备份机制（Replication）：备份的思想很简单，就是把相同的数据拷贝到多台机器上，而这些相同的数据拷贝在Kafka中被称为副本（Replica）。
        Kafka定义了两类副本：领导者副本（Leader Replica）和追随者副本（Follower Replica）。
        副本的工作机制：生产者总是向领导者副本写消息；而消费者总是从领导者副本读消息。
    分区（Partitioning）：Kafka中的分区机制指的是将每个主题划分成多个分区（Partition），每个分区是一组有序的消息日志。
    消息位移：Offset表示分区中每条消息的位置信息，是一个单调递增且不变的值。
    消费者位移：Consumer Offset表征消费者消费进度，每个消费者都有自己的消费者位移。
    消费者组：Consumer Group多个消费者实例共同组成的一个组，同时消费多个分区以实现高吞吐。
    重平衡：Rebalance消费者组内某个消费者实例挂掉后，其他消费者实例自动重新分配订阅主题分区的过程。Rebalance是Kafka消费者端实现高可用的重要手段。
    Kafka的消息层次都分为两层：消息集合（message set）以及消息（message）。一个消息集合中包含若干条日志项（record item），而日志项才是真正封装消息的地方。
    Kafka底层的消息日志由一系列消息集合日志项组成。Kafka通常不会直接操作具体的一条条消息，它总是在消息集合这个层面上进行写入操作。
```
### 1.Kafka是否可以脱离zookeeper使用
```markdown
Kafka不能脱离zookeeper单独使用，因为kafka使用zookeeper管理和协调kafka的节点服务器。
```
### 2.kafka有几种数控保留策略
```markdown
kafka有两种数据保留策略：按照过期时间保留和按照存储的消息大小保留。
```
### Kafka的文件存储机制
```markdown
Kafka中消息是以topic进行分类的，生产者通过topic向Kafka broker发送消息，消费者通过topic读取数据。
    然而topic在物理层面又能以partition为分组，一个topic可以分成若干个partition。partition还可以细分为segment，
    一个partition物理上由多个segment组成，segment文件由两部分组成，分别为“.index”文件和“.log”文件，分别表示为segment索引文件和数据文件。
    这两个文件的命令规则为：partition全局的第一个segment从0开始，后续每个segment文件名为上一个segment文件最后一条消息的offset值。
```
### Kafka如何保证可靠性
```markdown
如果我们要往Kafka对应的主题发送消息，我们需要通过Producer完成。Kafka主题对应了多个分区，每个分区下面又对应了多个副本；
    为了让用户设置数据可靠性，Kafka在Producer里面提供了消息确认机制。也就是说我们可以通过配置来决定消息发送到对应分区的几个副本才算消息发送成功。
    可以在定义Producer时通过acks参数指定。这个参数支持以下三种值：
    1.acks=0：意味着如果生产者能够通过网络把消息发送出去，那么就认为消息已成功写入Kafka。
        在这种情况下还是有可能发生错误，比如发送的对象无能被序列化或者网卡发生故障，但如果是分区离线或整个集群长时间不可用，那就不会收到任何错误。
        在acks=0模式下的运行速度是非常快的（这就是为什么很多基准测试都是基于这个模式），你可以得到惊人的吞吐量和带宽利用率，不过如果选择了这种模式，一定会丢失一些消息。
    2.acks=1：意味若Leader在收到消息并把它写入到分区数据文件（不一定同步到磁盘上）时会返回确认或错误响应。
        在这个模式下，如果发生正常的Leader选举，生产者会在选举时收到一个LeaderNotAvailableException异常，如果生产者能恰当地处理这个错误，
        它会重试发送悄息，最终消息会安全到达新的Leader那里。不过在这个模式下仍然有可能丢失数据，比如消息已经成功写入Leader，但在消息被复制到follower副本之前Leader发生崩溃。
    3.acks=all（这个和request.required.acks=-1 含义一样）：意味着Leader在返回确认或错误响应之前，会等待所有同步副本都收到悄息。
        如果和min.insync.replicas参数结合起来，就可以决定在返回确认前至少有多少个副本能够收到悄息，生产者会一直重试直到消息被成功提交。
        不过这也是最慢的做法，因为生产者在继续发送其他消息之前需要等待所有副本都收到当前的消息。
```
### Kafka消息是采用Pull模式，还是Push模式
```markdown
Kafka最初考虑的问题是，customer应该从brokes拉取消息还是brokers将消息推送到consumer，也就是pull还push。
    在这方面，Kafka遵循了一种大部分消息系统共同的传统的设计：
        producer将消息推送到broker，consumer从broker拉取消息。
    push模式下，当broker推送的速率远大于consumer消费的速率时，consumer恐怕就要崩溃了。
    最终Kafka还是选取了传统的pull模式。
    Pull模式的另外一个好处是consumer可以自主决定是否批量的从broker拉取数据。
    Pull有个缺点是，如果broker没有可供消费的消息，将导致consumer不断在循环中轮询，直到新消息到t达。
    为了避免这点，Kafka有个参数可以让consumer阻塞知道新消息到达。
```
### Kafka是如何实现高吞吐率的
```markdown
1.顺序读写：kafka的消息是不断追加到文件中的，这个特性使kafka可以充分利用磁盘的顺序读写性能
2.零拷贝：跳过“用户缓冲区”的拷贝，建立一个磁盘空间和内存的直接映射，数据不再复制到“用户态缓冲区”
3.文件分段：kafka的队列topic被分为了多个区partition，每个partition又分为多个段segment，所以一个队列中的消息实际上是保存在N多个片段文件中
4.批量发送：Kafka允许进行批量发送消息，先将消息缓存在内存中，然后一次请求批量发送出去
5.数据压缩：Kafka还支持对消息集合进行压缩，Producer可以通过GZIP或Snappy格式对消息集合进行压缩
```
### Kafka判断一个节点还活着的两个条件
```markdown
1.节点必须可以维护和ZooKeeper的连接，Zookeeper通过心跳机制检查每个节点的连接
2.如果节点是个follower,他必须能及时的同步leader的写操作，延时不能太久
```
### Kafka生产者消费者
[kafka实战篇（一）：Producer消息发送实战](https://blog.csdn.net/qq_26803795/article/details/105682276)
[kafka实战篇（二）：消息消费实战](https://blog.csdn.net/qq_26803795/article/details/105731900)
#### Kafka生产者
![image-20201123213740535](https://raw.githubusercontent.com/peng4444/picgo/main/img/20201123213740.png)
```markdown
根据KafkaProducer类上的注释上来看KafkaProducer具有如下特征：
    1.KafkaProducer是线程安全的，可以被多个线程交叉使用。
    2.KafkaProducer内部包含一个缓存池，存放待发送消息，即ProducerRecord队列，与此同时会开启一个IO线程将ProducerRecord对象发送到Kafka集群。
    3.KafkaProducer的消息发送API send方法是异步，只负责将待发送消息ProducerRecord发送到缓存区中，立即返回，并返回一个结果凭证Future。
    4.acks用来定义消息“已提交”的条件(标准)，就是Broker端向客户端承偌已提交的条件。0，all,1
    .....
发送消息主要的三种方式
	- 发送并忘记，
    - 同步发送，
    - 异步发送。
```
#### Kafka生产者发送消息
```java
// org.apache.kafka.clients
import java.util.Properties;
public class Demo{

    //创建一个生产者
    // 新建 Properties 对象。
    private Properties kafkaProps = new Properties();
    kafkaProps.put("bootstrap.servers","broker1:9092,broker2:9092")
    // 因为我们打算把键和值定义成字符串类型，所以使用内置的 StringSerializer
    kafkaProps.put("key.serializer","org.apache.kafka.common.serializaion.StringSerializer");
    kafkaProps.put("value.serializer","org.apache.kafka.common.serializaion.StringSerializer");
    // 在这里我们创建了一个新的生产者对象，并为键和值设置了恰当的类型，然后把Properties对象传给它。
    producer = new KafkaProducer<String,String>(kafkaPorps);


    //发送消息到Kafka,并不关心消息是否正常到达
    ProducerRecord<String ,String > record = new ProducerRecord<>("CustomerCountry","Precision Products","France");
    try{
        producer.send(recird);
    }catch(Exception e;){
        e.printStackTrace();
    }
    //同步发送消息到Kafka,使用send()方法发送消息，它会返回一个Future对象，调用get()方法进行等待，就可以指定消息是否发送成功。
    ProducerRecord<String ,String > record = new ProducerRecord<>("CustomerCountry","Precision Products","France");
    try{
        producer.send(recird).get();
    }catch(Exception e;){
        e.printStackTrace();
    }
    //异步发送消息到Kafka，调用send()，并指定一个回调函数，服务器在返回响应时调用该函数。
    private class DemoProducerCallback implements Callback{
        @Override
        public void onCompletion(RecordMetdata ercordMetadata,Excetion e){
            if(e != null){
                e.printStackTrace();
            }
        }
    }
    ProducerRecord<String ,String > record = new ProducerRecord<>("CustomerCountry","Biomedical Materials","USA");
    producer.send(record , new DemoProducerCallback(){
});
}
```
#### Kafka消费者
```markdown
Kafka 消费者从属于消费者群组。一个群组里的消费者订阅的是同 个主题，每个消费者接收主题一部分分区的消息。
```
#### Kafka消费者接收消息
```java
public class KafkaConsumer{
    
}
```
### Kafka消息丢失
```markdown
Kafka到底在什么情况下才能保证消息不丢失呢？
    一句话概括，Kafka只对“已提交”的消息（committed message）做有限度的持久化保证。
    这句话里面有两个核心要素，我们一一来看。
        第一个核心要素是“已提交的消息”。什么是已提交的消息？当Kafka的若干个Broker成功地接收到一条消息并写入到日志文件后，
            它们会告诉生产者程序这条消息已成功提交。此时，这条消息在Kafka看来就正式变为“已提交”消息了。
        第二个核心要素就是“有限度的持久化保证”，也就是说Kafka不可能保证在任何情况下都做到不丢失消息。
    总结一下，Kafka是能做到不丢失消息的，只不过这些消息必须是已提交的消息，而且还要满足一定的条件。
“消息丢失”案例:
    - 案例 1：生产者程序丢失数据。
        目前Kafka Producer是异步发送消息的，也就是说如果你调用的是producer.send(msg) 这个API，那么它通常会立即返回，但此时你不能认为消息发送已成功完成。
        解决此问题的方法非常简单：Producer永远要使用带有回调通知的发送API，也就是说不要使用producer.send(msg)，而要使用producer.send(msg, callback)。
        callback（回调）能准确地告诉你消息是否真的提交成功了。一旦出现消息提交失败的情况，你就可以有针对性地进行处理。
    - 案例 2：消费者程序丢失数据
        Consumer端丢失数据主要体现在Consumer端要消费的消息不见了。Consumer程序有个“位移”的概念，表示的是这个Consumer当前消费到的Topic分区的位置。
        Kafka中Consumer端的消息丢失就是这么一回事。
        要对抗这种消息丢失，办法很简单：维持先消费消息（阅读），再更新位移（书签）的顺序即可。这样就能最大限度地保证消息不丢失。
        对于Kafka而言，这就好比Consumer程序从Kafka获取到消息后开启了多个线程异步处理消息，而Consumer程序自动地向前更新位移。
        假如其中某个线程运行失败了，它负责的消息没有被成功处理，但位移已经被更新了，因此这条消息对于Consumer而言实际上是丢失了。\
        解决方案也很简单：如果是多线程异步处理消费消息，Consumer程序不要开启自动提交位移，而是要应用程序手动提交位移。
最佳实践:
    - 1.不要使用producer.send(msg)，而要使用producer.send(msg, callback)。记住，一定要使用带有回调通知的send方法。
    - 2.设置acks = all。acks是Producer的一个参数，代表了你对“已提交”消息的定义。如果设置成all，则表明所有副本Broker都要接收到消息，该消息才算是“已提交”。这是最高等级的“已提交”定义。
    - 3.设置retries为一个较大的值。retries同样是Producer的参数，对应前面提到的Producer自动重试。
        当出现网络的瞬时抖动时，消息发送可能会失败，此时配置了retries>0的Producer能够自动重试消息发送，避免消息丢失。
    - 4.设置unclean.leader.election.enable=false。这是Broker端的参数，它控制的是哪些Broker有资格竞选分区的Leader。
        如果一个Broker落后原先的Leader太多，那么它一旦成为新的Leader，必然会造成消息的丢失。故一般都要将该参数设置成false，即不允许这种情况的发生。
    - 5.设置replication.factor>=3。Broker端的参数。其实这里想表述的是，最好将消息多保存几份，毕竟目前防止消息丢失的主要机制就是冗余。
    - 6.设置min.insync.replicas>1。Broker端参数，控制的是消息至少要被写入到多少个副本才算是“已提交”。设置成大于1可以提升消息持久性。在实际环境中千万不要使用默认值1。
    - 7.确保replication.factor>min.insync.replicas。如果两者相等，那么只要有一个副本挂机，整个分区就无法正常工作了。
        我们不仅要改善消息的持久性，防止数据丢失，还要在不降低可用性的基础上完成。推荐设置成replication.factor=min.insync.replicas+1。
    - 8.确保消息消费完成再提交。Consumer端有个参数enable.auto.commit，最好把它设置成false，并采用手动提交位移的方式。
```
### Kafka拦截器
[kafka自定义拦截器｜案例实战](https://ropledata.blog.csdn.net/article/details/105768429)
```markdown
Kafka拦截器分为生产者拦截器和消费者拦截器。
    生产者拦截器允许你在发送消息前以及消息提交成功后植入你的拦截器逻辑；
    消费者拦截器支持在消费消息前以及提交位移后编写特定逻辑。
Kafka拦截器的设置方法是通过参数配置完成的。生产者和消费者两端有一个相同的参数，名字叫interceptor.classes，它指定的是一组类的列表，每个类就是特定逻辑的拦截器实现类。
一定要注意的是，指定拦截器类时要指定它们的全限定名，即full qualified name。通俗点说就是要把完整包名也加上，不要只有一个类名在那里，并且还要保证你的Producer程序能够正确加载你的拦截器类。
典型使用场景:
    Kafka拦截器可以应用于包括客户端监控、端到端系统性能检测、消息审计等多种功能在内的场景。
```
### Spring 对Apache Kafka的支持与集成
[Spring 对Apache Kafka的支持与集成](https://www.cnblogs.com/liululee/p/14042652.html)
[SpringBoot整合Kafka和Storm](https://www.cnblogs.com/xuwujing/p/9021561.html)