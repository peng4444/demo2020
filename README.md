# 学习新知识代码和博客阅读实操

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

[Elasticsearch Java API 很全的整理以及架构剖析](https://www.cnblogs.com/laoqing/p/11693144.html)

[Elasticsearch系列文章](https://www.cnblogs.com/huangying2124/category/1626782.html)

## Nginx技术
[Nginx 从入门到实践，万字详解！](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247486730&idx=1&sn=2031330f25c91be1b1bbb4b48aeba63e&chksm=cea242c1f9d5cbd7896d2f3ccdc474afcba389e1f469bda8e125ee5e9cac3d68588eeb675dd6&mpshare=1&scene=23&srcid=&sharer_sharetime=1588724499740&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)

[Spring Boot 利用 nginx 实现生产环境的伪热更新](https://www.cnblogs.com/fishpro/p/spring-boot-study-hotstart.html)

[[Nginx 极简入门教程！](https://www.cnblogs.com/lenve/p/10977548.html)]

[[Nginx的负载均衡](https://www.cnblogs.com/death00/p/11611672.html)]

[[nginx负载均衡](https://www.cnblogs.com/helloxiaoduan/p/12586307.html)]

[ 从入门到精通-Nginx，图文并茂、负载均衡、动静分离、虚拟主机 附案例源码](https://www.cnblogs.com/chenyanbin/p/12521296.html)

## RocketMQ技术

【随笔分类 - RocketMQ】[RocketMQ](https://www.cnblogs.com/a526583280/category/1516277.html)  

[MQ消息队列](https://www.cnblogs.com/qdhxhz/category/1221076.html)

[【Rocketmq】通过 docker 快速搭建 rocketmq 环境](https://www.cnblogs.com/kiwifly/p/11546008.html)

[今日头条在消息服务平台和容灾体系建设方面的实践与思考](https://www.cnblogs.com/lishangzhi/p/11773756.html)

[[SpringBoot如何优雅的使用RocketMQ](https://www.cnblogs.com/SimpleWu/p/12112351.html)]

## Docker技术
```markdown
docker :centos101,centos102上安装了docker，参照安装参照>>千锋教育-李卫民
[docker安装参照>>史上最详细的Docker安装手册](https://www.cnblogs.com/zhizihuakai/p/12633724.html)
>> docker启动命令
# systemctl start docker -- 启动docker
# systemctl enable docker -- 设置docker自动启动
# systemctl daemon-reload  -- 重新启动守护进程
# systemctl restart docker -- 重启docker
```
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
[docker配置mysql主从与django实现读写分离](https://www.cnblogs.com/yscl/p/11992175.html)
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
[Knative 实战：基于 Knative Serverless 技术实现天气服务-上篇](https://yq.aliyun.com/articles/719486?spm=a2c4e.11157919.spm-cont-list.85.5917f204xM6Od1)

## 算法，机器学习

[解密淘宝推荐实战，打造 “比你还懂你” 的个性化APP]( https://yq.aliyun.com/articles/739057?spm=a2c4e.11153940.bloghomeflow.31.2b9f291aaxV1Kk )
