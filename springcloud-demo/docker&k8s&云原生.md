# Docker&K8S&云原生
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
[随笔分类 - 测试高级进阶技能系列 - Docker](https://www.cnblogs.com/poloyy/category/1870863.html)
[Docker安装]( https://blog.csdn.net/laughing1997/article/details/84305615 ) [Centos系统下docker的安装与卸载]( https://blog.csdn.net/a527219336/article/details/50800181 )

[Centos 7 安装最新 Docker-compose 的正确姿势]( https://blog.csdn.net/cookily_liangzai/article/details/82496934 )

[[Docker笔记（三）：Docker安装与配置](https://www.cnblogs.com/spec-dog/p/11194521.html)]

[ [【新】Docker实战总结](https://www.cnblogs.com/leozhanggg/p/12039953.html) ]【****】

  [[中小团队基于Docker的Devops实践](https://www.cnblogs.com/37Y37/p/11216915.html)]【***】

 [[Docker+Maven+Jenkins在Devops中完整应用](https://www.cnblogs.com/pluto4596/p/11216825.html)]【***】

 [[Flume+Kafka收集Docker容器内分布式日志应用实践](https://www.cnblogs.com/wuxj/p/11261250.html)]

[[一键部署 Spring Boot 到远程 Docker 容器，就是这么秀！](https://www.cnblogs.com/lenve/p/11434074.html)]

[[Spring Boot 和 Docker 实现微服务部署](https://www.cnblogs.com/fengzheng/p/10329097.html)]
[还在手动启动springboot项目？docker部署不香吗？](https://www.toutiao.com/i6843391272229536267)

[[DOCKER 学习笔记5 Springboot+nginx+mysql 容器编排](https://www.cnblogs.com/ChromeT/p/12289177.html)]
[Docker笔记](https://www.cnblogs.com/Hui4401/p/13758443.html)
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


## K8S
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
[Kubernetes+Docker+Istio 容器云实践](https://www.cnblogs.com/yixinjishu/p/11691932.html)
[[k8s 开船记-首航：博客站点从 docker swarm 切换到 k8s](https://www.cnblogs.com/cmt/p/12033446.html)]

![K8S架构](https://www.funtl.com/assets1/Lusifer_20190531065907.png)


## 云平台 、云原生

[云原生的新思考，为什么容器已经无处不在了]( https://yq.aliyun.com/articles/699757?spm=a2c4e.11157919.spm-cont-list.262.591727ae00w0Df )

[[云原生开发环境初探](https://www.cnblogs.com/code-craftsman/p/12033656.html)]

[[什么是云原生](https://kb.cnblogs.com/page/647666/)]()

![云原生](http://assets.processon.com/chart_image/5df3580ee4b051b174b280fb.png)

[ Service Mesh 超大规模落地揭秘]( https://yq.aliyun.com/articles/739113?spm=a2c4e.11153940.bloghomeflow.22.2b9f291aaxV1Kk )

[优秀DevOps工程师必会的33个面试题](https://www.cnblogs.com/xuelong3/p/12587932.html)

[Knative 实战：基于 Knative Serverless 技术实现天气服务-上篇](https://yq.aliyun.com/articles/719486?spm=a2c4e.11157919.spm-cont-list.85.5917f204xM6Od1)

##
