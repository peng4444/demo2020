# 学习新知识代码和博客阅读实操
## 分布式事务解决方案
[参考视频1：分布式事务解决方案](https://www.bilibili.com/video/av64323822)

[参考视频2：阿里分布式事务框架Seata原理解析](https://www.bilibili.com/video/av50531999)

[参考视频3：阿里如何解决分布式事务](https://www.bilibili.com/video/av40630844)

[分布式事务](https://www.cnblogs.com/xiaobingblog/p/11540341.html)

[SpringBoot的事物Transaction使用的教程](https://www.cnblogs.com/xuwujing/p/11184162.html)

[终于跑通分布式事务框架tcc-transaction的示例项目](https://www.cnblogs.com/bigdataZJ/p/tcc-transaction-sample.html)

[TCC 分布式事务](https://www.cnblogs.com/jajian/p/10014145.html)

[浅谈分布式事务与TX-LCN](https://www.cnblogs.com/tanshaoshenghao/p/11684727.html)

[分布式事务解决方案：TCC与最终一致](https://www.cnblogs.com/sw008/p/11054277.html)

[阿里分布式事务seata入门（采坑）](https://www.cnblogs.com/sky-chen/p/11419942.html)

[Spring Cloud Alibaba | 微服务分布式事务之Seata](https://www.cnblogs.com/babycomeon/p/11504210.html)

[Spring Cloud同步场景分布式事务怎样做？试试Seata](https://www.cnblogs.com/zlt2000/p/11525417.html)

[[扛住阿里双十一高并发流量，Sentinel是怎么做到的？](https://www.cnblogs.com/caison/p/11673047.html)]

```markdown
原理：两阶段提交
分布式事务解决方案：
	1.消息队列(RocketMQ)
	2.AT -- 业务无侵入   远程调用提交前wait    TxManager
	3.TCC -- 业务有侵入  try -- commit -- cacel
	4.
```

## Dubbo原理与源码分析

[参考视频1：深刻了解dubbo底层源码](https://www.bilibili.com/video/av58338686)

[参考视频2：Dubbo底层原理与面试经验](https://www.bilibili.com/video/av53428315/)

[从零开始认识Dubbo](https://www.cnblogs.com/alterem/p/11211728.html)

[dubbo](https://www.cnblogs.com/xxbiao/tag/dubbo/)

[手写RPC框架注释代码](https://www.cnblogs.com/mseddl/p/11531465.html)

[一文带你实现RPC框架](https://www.cnblogs.com/endless-code/p/11235624.html)

[微服务调用为啥用RPC框架，http不更简单吗？](https://zhuanlan.zhihu.com/p/61364466)

[Dubbo服务注册与发现](https://www.cnblogs.com/mzq123/p/11221570.html)

[springboot2.x纯注解整合dubbo](https://www.cnblogs.com/chywx/p/11180719.html)

[Dubbo 与 Spring Cloud 完美结合](https://www.cnblogs.com/babycomeon/p/11546737.html)

[[Dubbo面试八连问，这些你都能答上来吗？](https://www.cnblogs.com/javazhiyin/p/11966271.html)]

[[Zookeeper+Dubbo项目demo搭建](https://www.cnblogs.com/iUtopia/p/11653098.html)]

## ElasticSearch技术

[参考视频1：详细ElasticSearch技术讲解](https://www.bilibili.com/video/av66259861)

[参考视频2：ElasticSearch6入门教程](E:\学习视频\004.ElasticSearch6入门教程（62集）)

【随笔分类 - ElasticSearch】[ElasticSearch](https://www.cnblogs.com/supersnowyao/category/1236567.html)  [ElasticSearch](https://www.cnblogs.com/shoufeng/category/1332134.html)

【随笔分类 - ElasticSearch】[ElasticSearch6.X实战](https://www.cnblogs.com/yulinfeng/category/1505154.html)

[ES查询语句](https://www.cnblogs.com/lifengdi/p/11514463.html)

[[SpringBoot操作ES进行各种高级查询](https://www.cnblogs.com/keatsCoder/p/11341835.html)]

[基于 MySQL Binlog 的 Elasticsearch 数据同步实践 原](https://www.cnblogs.com/mfwtech/p/11187516.html)

[ElasticSearch核心概念和文档的CRUD](https://www.cnblogs.com/haixiang/p/11181711.html)

[[关于Elasticsearch文档的描述以及如何操作文档的详细总结](https://www.cnblogs.com/lifengdi/p/11544169.html)]

[[【docker Elasticsearch】Rest风格的分布式开源搜索和分析引擎Elasticsearch初体验](https://www.cnblogs.com/lomtom/p/12584956.html)]

## Zookeeper技术

[如何用 Zookeeper 实现分布式锁？（附源码）](https://mp.weixin.qq.com/s?__biz=MzUxOTc4NjEyMw==&mid=2247484568&idx=1&sn=d2ae43f697a01d4f4a0a05c3b0e48649&chksm=f9f51f7cce82966a55e8bb51d54f78094112252cba489e77c7aa272c98c24ecbb4b004737af9&mpshare=1&scene=23&srcid=#rd)

[如何用 Zookeeper 实现分布式锁？（附源码）](https://mp.weixin.qq.com/s?__biz=MzUxOTc4NjEyMw==&mid=2247484568&idx=1&sn=d2ae43f697a01d4f4a0a05c3b0e48649&chksm=f9f51f7cce82966a55e8bb51d54f78094112252cba489e77c7aa272c98c24ecbb4b004737af9&mpshare=1&scene=23&srcid=#rd)

[基于缓存或zookeeper的分布式锁实现](https://www.cnblogs.com/jmcui/p/11186224.html)

[zookeeper源码](https://www.cnblogs.com/sunshine-2015/category/1450046.html)

```markdown

```

## Kafka技术

[图解kafka解析设计原理](https://www.cnblogs.com/lbzhello/p/kafka-20190708.html) 

[[再过半小时，你就能明白kafka的工作原理了](https://www.cnblogs.com/sujing/p/10960832.html)]

[[基于Kafka的实时计算引擎如何选择？Flink or Spark？](https://www.cnblogs.com/smartloli/p/10963221.html)]

[[源码分析 Kafka 消息发送流程(文末附流程图)](https://www.cnblogs.com/dingwpmz/p/12153036.html)]

```markdown
[Kafka常用命令](https://blog.csdn.net/qq_33689414/article/details/81046502)
```

## Flink技术

【Flink】[实时计算引擎FLink](https://www.cnblogs.com/zhisheng/p/11332529.html)

[[flink入门实战总结](https://www.cnblogs.com/davidwang456/p/11256748.html)]

[[如何进行Flink项目构建,快速开发Flink应用程序?](https://www.cnblogs.com/bigdata1024/p/11938727.html)]

[[Flink 灵魂两百问，这谁顶得住？](https://www.cnblogs.com/zhisheng/p/11254773.html)]

[[Flink基本的API](https://www.cnblogs.com/duma/p/10964985.html)]

```markdown

```

## K8S技术

[K8S安装](参考书k8s权威指南)

[高可用的K8S集群部署方案](https://www.cnblogs.com/ants/p/11489598.html)

[[简单了解一下K8S，并搭建自己的集群](https://www.cnblogs.com/detectiveHLH/p/12048795.html)]

[[6 个 K8s 日志系统建设中的典型问题，你遇到过几个？](https://www.cnblogs.com/alisystemsoftware/p/11544392.html)]

[[spring-cloud-kubernetes官方demo运行实战](https://www.cnblogs.com/bolingcavalry/p/11445732.html)]

[[从零开始入门 K8s| 详解 Pod 及容器设计模式](https://www.cnblogs.com/alisystemsoftware/p/11551525.html)]

[[入门级实操教程！从概念到部署，全方位了解K8S Ingress！](https://www.cnblogs.com/rancherlabs/p/12034075.html)]

[[超长干货丨Kubernetes网络快速入门完全指南](https://www.cnblogs.com/rancherlabs/p/12101762.html)]

```markdown
Kubernetes 是容器集群管理系统，是一个开源的平台，可以实现容器集群的自动化部署、自动扩缩容、维护等功能。使用 Kubernetes 我们可以：
快速部署应用
快速扩展应用
无缝对接新的应用功能
节省资源，优化硬件资源的使用
Kubernetes 的目标是促进完善组件和工具的生态系统，以减轻应用程序在公有云或私有云中运行的负担。
```

[[k8s 开船记-首航：博客站点从 docker swarm 切换到 k8s](https://www.cnblogs.com/cmt/p/12033446.html)]

![K8S架构](https://www.funtl.com/assets1/Lusifer_20190531065907.png)

### K8S安装-必须要集群安装

```markdown

```

## Nginx技术

[Spring Boot 利用 nginx 实现生产环境的伪热更新](https://www.cnblogs.com/fishpro/p/spring-boot-study-hotstart.html)

[[Nginx 极简入门教程！](https://www.cnblogs.com/lenve/p/10977548.html)]

[[Nginx的负载均衡](https://www.cnblogs.com/death00/p/11611672.html)]

[[nginx负载均衡](https://www.cnblogs.com/helloxiaoduan/p/12586307.html)]

[ 从入门到精通-Nginx，图文并茂、负载均衡、动静分离、虚拟主机 附案例源码](https://www.cnblogs.com/chenyanbin/p/12521296.html)
```markdown

```

## RocketMQ技术

【随笔分类 - RocketMQ】[RocketMQ](https://www.cnblogs.com/a526583280/category/1516277.html)  [MQ消息队列](https://www.cnblogs.com/qdhxhz/category/1221076.html)

[【Rocketmq】通过 docker 快速搭建 rocketmq 环境](https://www.cnblogs.com/kiwifly/p/11546008.html)

[今日头条在消息服务平台和容灾体系建设方面的实践与思考](https://www.cnblogs.com/lishangzhi/p/11773756.html)

[[SpringBoot如何优雅的使用RocketMQ](https://www.cnblogs.com/SimpleWu/p/12112351.html)]

```markdown

```

## Docker技术

【Docker】[docker笔记](https://www.cnblogs.com/spec-dog/tag/docker/)

[Docker安装]( https://blog.csdn.net/laughing1997/article/details/84305615 ) [Centos系统下docker的安装与卸载]( https://blog.csdn.net/a527219336/article/details/50800181 )

[Centos 7 安装最新 Docker-compose 的正确姿势]( https://blog.csdn.net/cookily_liangzai/article/details/82496934 )

[[Docker笔记（三）：Docker安装与配置](https://www.cnblogs.com/spec-dog/p/11194521.html)]

[ [【新】Docker实战总结](https://www.cnblogs.com/leozhanggg/p/12039953.html) ]【****】

  [[中小团队基于Docker的Devops实践](https://www.cnblogs.com/37Y37/p/11216915.html)]【***】

 [[Docker+Maven+Jenkins在Devops中完整应用](https://www.cnblogs.com/pluto4596/p/11216825.html)]【***】

 [[Flume+Kafka收集Docker容器内分布式日志应用实践](https://www.cnblogs.com/wuxj/p/11261250.html)]

[[一键部署 Spring Boot 到远程 Docker 容器，就是这么秀！](https://www.cnblogs.com/lenve/p/11434074.html)]

[[Spring Boot 和 Docker 实现微服务部署](https://www.cnblogs.com/fengzheng/p/10329097.html)]

[[SpringBoot如何优雅的使用RocketMQ](https://www.cnblogs.com/SimpleWu/p/12112351.html)]

[[DOCKER 学习笔记5 Springboot+nginx+mysql 容器编排](https://www.cnblogs.com/ChromeT/p/12289177.html)]

```bash
$ docker system info ##查看docker存储驱动类型
$ docker image ls  ## 查看所有的docker镜像
$ docker image rm  镜像编号 ##删除docker镜像  docker image rm alpine:latest
$ docker image pull ##下载镜像   docker image pull alpine:latest
$ docker image inspect  ##展示镜像的细节包括镜像层数据和元数据
$ docker image history  ## 查看构建镜像过程中都执行了那些指令
$ docker image build ## 读取Dockerfile文件，并且将应用程序容器化
$ docker container run <options> <image>:<tag> <app> ##指定要启动的镜像已及要运行的应用
$ docker container stop
$ docker container start
$ docker container rm
$ docker container run -it ubuntu:latest /bin/bash ##运行容器版本的Ubuntu Linux
$ docker container ls ##查看容器正在运行的容器列表
$ docker-compose up ##启动应用 -d后台启动
$ docker-compose down ##关闭应用
$ docker-compose ps  ##查看应用状态
$ docker-compose top ##列出各个服务内运行的进程
$ docker-compose stop ##停止应用
$ docker-compose restart ##重启应用
$ docker network ls ##Docker主机上的网络
$ docker network inspect bridge
```

## MongoDB技术

[[MongoDB非关系型数据库开发手册](https://www.cnblogs.com/yueshutong/p/11491106.html)]

## Netty

[[支撑百万级并发，Netty如何实现高性能内存管理](https://www.cnblogs.com/caison/p/12121029.html)]

[[基于Netty和SpringBoot实现一个轻量级RPC框架-协议篇](https://www.cnblogs.com/throwable/p/12185142.html)]

## 云平台 、云原生

[云原生的新思考，为什么容器已经无处不在了]( https://yq.aliyun.com/articles/699757?spm=a2c4e.11157919.spm-cont-list.262.591727ae00w0Df )

[[云原生开发环境初探](https://www.cnblogs.com/code-craftsman/p/12033656.html)]

[[什么是云原生](https://kb.cnblogs.com/page/647666/)]()

![云原生](http://assets.processon.com/chart_image/5df3580ee4b051b174b280fb.png)

[ Service Mesh 超大规模落地揭秘]( https://yq.aliyun.com/articles/739113?spm=a2c4e.11153940.bloghomeflow.22.2b9f291aaxV1Kk )

[优秀DevOps工程师必会的33个面试题](https://www.cnblogs.com/xuelong3/p/12587932.html)

## 算法，机器学习

[解密淘宝推荐实战，打造 “比你还懂你” 的个性化APP]( https://yq.aliyun.com/articles/739057?spm=a2c4e.11153940.bloghomeflow.31.2b9f291aaxV1Kk )
