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

## 云平台 、云原生

[云原生的新思考，为什么容器已经无处不在了]( https://yq.aliyun.com/articles/699757?spm=a2c4e.11157919.spm-cont-list.262.591727ae00w0Df )

[[云原生开发环境初探](https://www.cnblogs.com/code-craftsman/p/12033656.html)]

[[什么是云原生](https://kb.cnblogs.com/page/647666/)]()

![云原生](http://assets.processon.com/chart_image/5df3580ee4b051b174b280fb.png)

[ Service Mesh 超大规模落地揭秘]( https://yq.aliyun.com/articles/739113?spm=a2c4e.11153940.bloghomeflow.22.2b9f291aaxV1Kk )

[优秀DevOps工程师必会的33个面试题](https://www.cnblogs.com/xuelong3/p/12587932.html)

[Knative 实战：基于 Knative Serverless 技术实现天气服务-上篇](https://yq.aliyun.com/articles/719486?spm=a2c4e.11157919.spm-cont-list.85.5917f204xM6Od1)

##
