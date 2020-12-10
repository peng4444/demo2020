# Docker&K8S&云原生

## Docker基础知识
[Docker学习参考博客：](https://www.funtl.com/zh/docs-docker/)
[docker安装参照>>史上最详细的Docker安装手册](https://www.cnblogs.com/zhizihuakai/p/12633724.html)
### Docker简介
[还不懂Docker？一个故事安排的明明白白！](https://www.cnblogs.com/xuanyuan/p/14003524.html)
```markdown
Docker：Build once，Run anywhere
Docker使用Google公司推出的Go语言进行开发实现，基于Linux内核的cgroup，namespace，以及AUFS类的Union FS等技术，
    对进程进行封装隔离，属于操作系统层面的虚拟化技术。由于隔离的进程独立于宿主和其它的隔离的进程，因此也称其为容器。
    最初实现是基于LXC，从0.7版本以后开始去除LXC，转而使用自行开发的libcontainer，从1.11开始，则进一步演进为使用runC和containerd。
chroot & pivot_root:两个函数，可以修改进程和系统的根目录到一个新的位置。
    用操作系统镜像文件挂载到容器进程的根目录下，变成容器的rootfs，和真实系统目录一模一样，足可以以假乱真：“伪造”一个文件系统来欺骗容器中的进程。
namespace:通过它可以划定一个个的命名空间，然后把进程划分到这些命名空间中。
    每个命名空间都是独立存在的，命名空间里面的进程都无法看到空间之外的进程、用户、网络等等信息。
    将进程的“视野”锁定在容器规定的范围内，如此一来，容器内的进程彷佛被施上了障眼法，再也看不到外面的世界。
CGroup:通过它可以划定一个个的分组，然后限制每个分组能够使用的资源，比如内存的上限值、CPU的使用率、硬盘空间总量等等。
    系统内核会自动检查和限制这些分组中的进程资源使用量。
```
### Docker Ubuntu安装&启动&测试  -- 一般使用非root用户
```markdown
$ sudo apt-get -y update
$ sudo apt-get -y install docker-ce  # 安装docker社区版稳定版
$ sudo systemctl enable docker  # 允许开机启动
$ sudo systemctl start docker   # 运行Docker守护进程
$ sudo systemctl stop docker #停止Docker守护进程
$ systemctl restart docker #重启Docker守护进程
$ systemctl status docker #查看Docker的运行状态
$ sudo groupadd docker # 建立 docker 用户组
$ sudo usermod -aG docker $USER # 将当前用户加入 docker 组
-- 退出当前终端并重新登录，进行如下测试
$ docker run hello-world # 测试 Docker 是否安装正确  docker会创建一个hello-world的镜像
执行：docker info 查看Docker服务信息
执行：docker version 查看版本号
```
### Docker CentOS安装
[Docker CentOS安装](https://www.funtl.com/zh/docs-docker/CentOS-%E5%AE%89%E8%A3%85-Docker.html#%E4%BD%BF%E7%94%A8-yum-%E5%AE%89%E8%A3%85)
```markdown
可以先删除旧版本，使用yum安装依赖包和添加国内的yum软件源
$ sudo yum makecache fast  # 更新 yum 软件源缓存
$ sudo yum install docker-ce # 安装 docker-ce
$ sudo systemctl enable docker # 启动 Docker CE
$ sudo systemctl start docker
$ sudo groupadd docker # 建立 docker 组
$ sudo usermod -aG docker $USER # 将当前用户加入 docker 组
-- 退出当前终端并重新登录，进行如下测试。
$ docker run hello-world  # 测试 Docker 是否安装正确  docker会创建一个hello-world的镜像
```
### Docker 镜像加速器 Ubuntu 16.04+、Debian 8+、CentOS 7 
```markdown
Ubuntu 16.04+、Debian 8+、CentOS 7 
对于使用 systemd 的系统，请在 /etc/docker/daemon.json 中写入如下内容（如果文件不存在请新建该文件）
    {
      "registry-mirrors": [
        "https://registry.docker-cn.com"
      ]
    }
注意，一定要保证该文件符合 json 规范，否则 Docker 将不能启动。
之后重新启动服务。
    $ sudo systemctl daemon-reload
    $ sudo systemctl restart docker
检查加速器是否生效  $ docker info
    如果从结果中看到了如下内容，说明配置成功 Registry Mirrors:https://registry.docker-cn.com/
```
### Docker镜像获取&管理&原理
```markdown
1.从仓库获取镜像
    从Docker镜像仓库获取镜像的命令是docker pull。具体的选项可以通过 docker pull --help 命令看到
    其命令格式为：docker pull [选项] [Docker Registry 地址[:端口号]/]仓库名[:标签]
    - Docker镜像仓库地址：地址的格式一般是 <域名/IP>[:端口号]。默认地址是Docker Hub。
    - 库名：仓库名是两段式名称，即<用户名>/<软件名>。对于Docker Hub，如果不给出用户名，则默认为library，也就是官方镜像。
    $ docker pull ubuntu:16.04  # 获取官方镜像library/ubuntu仓库中标签为16.04的镜像
    $ docker run -it --rm \ ubuntu:16.04 \ bash  # 运行容器，启动里面的bash并且进行交互式操作
        -it：是两个参数，一个是-i交互式操作，一个是-t终端。我们这里打算进入bash执行一些命令并查看返回结果，因此我们需要交互式终端。
        --rm：这个参数是说容器退出后随之将其删除。默认情况下，为了排障需求，退出的容器并不会立即删除，除非手动docker rm。
            我们这里只是随便执行个命令，看看结果，不需要排障和保留结果，因此使用--rm可以避免浪费空间。
        ubuntu:16.04：这是指用ubuntu:16.04镜像为基础来启动容器。
        bash：放在镜像名后的是命令，这里我们希望有个交互式Shell，因此用的是bash。
    进入容器后，我们可以在Shell下操作，执行任何所需的命令。
    执行了cat /etc/os-release，这是Linux常用的查看当前系统版本的命令，从返回的结果可以看到容器内是Ubuntu 16.04.4 LTS系统。
    通过exit退出了这个容器。
2.管理本地主机上的镜像
    $ docker image ls # 列出已经下载下来的镜像，列表包含了仓库名、标签、镜像ID、创建时间以及所占用的空间。
        默认的docker image ls列表中只会显示顶层镜像，如果希望显示包括中间层镜像在内的所有镜像的话，需要加-a参数。
        $ docker image ls 仓库名|仓库名:标签  # 查看镜像
        $ docker image ls -f since|before=镜像名  # 过滤器参数--filter，或者简写-f查找在该镜像之前或者之后创建的镜像
    $ docker system df # 便捷的查看镜像、容器、数据卷所占用的空间。
    $ docker image rm [选项] <镜像1> [<镜像2> ...] # 删除本地的镜像，可以使用docker image rm命令
        其中，<镜像> 可以是 镜像短ID、镜像长ID、镜像名或者镜像摘要。
3.介绍镜像实现的基本原理
    $ docker diff 命令看到具体的改动。
    $ docker commit [选项] <容器ID或容器名> [<仓库名>[:<标签>]] # 可以将容器的存储层保存下来成为镜像，尽量不要使用
    $ docker diff 容器名 # 查看容器文件的变化
```
### Docker Dockerfile定制镜像
```markdown

```
### Docker操作容器
```markdown
1.启动容器
    1.1.基于镜像新建一个容器并启动: $ docker run ubuntu:14.04
      $ docker run -t -i ubuntu:14.04 /bin/bash  # 启动一个bash终端，允许用户进行交互。
        -t选项让Docker分配一个伪终端（pseudo-tty）并绑定到容器的标准输入上，-i则让容器的标准输入保持打开。
      当利用docker run来创建容器时，Docker在后台运行的标准操作包括：
        - 检查本地是否存在指定的镜像，不存在就从公有仓库下载
        - 利用镜像创建并启动一个容器
        - 分配一个文件系统，并在只读的镜像层外面挂载一层可读写层
        - 从宿主主机配置的网桥接口中桥接一个虚拟接口到容器中去
        - 从地址池配置一个 ip 地址给容器
        - 执行用户指定的应用程序
        - 执行完毕后容器被终止
    1.2.将在终止状态（stopped）的容器重新启动: $ docker container start # 将一个已经终止的容器启动运行
        $ docker container restart # 命令会将一个运行态的容器终止，然后再重新启动它。
2.Docker守护态运行，使用了-d参数运行容器
    $ docker run -d ubuntu:17.10 /bin/sh -c "while true; do echo hello world; sleep 1; done"
    此时容器会在后台运行并不会把输出的结果(STDOUT)打印到宿主机上面(输出结果可以用docker logs查看)。
    $ docker container ls # 查看容器信息
    $ docker container logs # 获取容器的输出信息
3.Docker终止容器
    $ docker container stop # 终止一个运行中的容器，当Docker容器中指定的应用终结时，容器也自动终止。
    用户通过exit命令或Ctrl+d来退出终端时，所创建的容器立刻终止。终止状态的容器可以用docker container ls -a命令看到。
4.Docker进入容器
    $ docker attach命令或docker exec命令，推荐大家使用docker exec命令 # 进入容器进行操作
    $ docker exec -it 容器id bash # 进入容器，在其中exit也不会导致容器停止，这就是为什么推荐大家使用docker exec的原因
5.Docker导出和导入容器
    容器导出：$ docker export 容器id > xxxx.tar
    容器导入：$ cat ubuntu.tar | docker import - test/ubuntu:v1.0
    通过指定URL或者某个目录来导入 $ docker import http://example.com/exampleimage.tgz example/imagerepo
6.Docker删除容器
    $ docker container rm 容器名 # 删除一个处于终止状态的容器
    $ docker container ls -a # 查看所有已经创建的包括终止状态的容器
    $ docker container prune  # 清理掉所有处于终止状态的容器
```
### Docker Compose
```markdown
Docker Compose是Docker官方编排（Orchestration）项目之一，负责快速的部署分布式应用。 https://github.com/docker/compose
    允许用户通过一个单独的docker-compose.yml模板文件（YAML格式）来定义一组相关联的应用容器为一个项目（project）。
Docker Compose安装：
    1.（从 官方 GitHub Release 处直接下载编译好的二进制文件即可）
    $ sudo curl -L https://github.com/docker/compose/releases/download/1.17.1/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
    $ sudo chmod +x /usr/local/bin/docker-compose
    bash 补全命令
    $ curl -L https://raw.githubusercontent.com/docker/compose/1.8.0/contrib/completion/bash/docker-compose > /etc/bash_completion.d/docker-compose
卸载：二进制包方式安装的，删除二进制文件即可 $ sudo rm /usr/local/bin/docker-compose
检验安装结果：docker-compose --version
```

## Docker技术
【Docker】[docker笔记](https://www.cnblogs.com/spec-dog/tag/docker/)
[随笔分类 - 测试高级进阶技能系列 - Docker](https://www.cnblogs.com/poloyy/category/1870863.html)
[Docker安装]( https://blog.csdn.net/laughing1997/article/details/84305615 ) 
[Centos系统下docker的安装与卸载]( https://blog.csdn.net/a527219336/article/details/50800181 )
[Centos 7 安装最新 Docker-compose 的正确姿势]( https://blog.csdn.net/cookily_liangzai/article/details/82496934 )
[Docker笔记（三）：Docker安装与配置](https://www.cnblogs.com/spec-dog/p/11194521.html)
[【新】Docker实战总结](https://www.cnblogs.com/leozhanggg/p/12039953.html) 
[中小团队基于Docker的Devops实践](https://www.cnblogs.com/37Y37/p/11216915.html)
[Docker+Maven+Jenkins在Devops中完整应用](https://www.cnblogs.com/pluto4596/p/11216825.html)
[Flume+Kafka收集Docker容器内分布式日志应用实践](https://www.cnblogs.com/wuxj/p/11261250.html)
[一键部署 Spring Boot 到远程 Docker 容器，就是这么秀！](https://www.cnblogs.com/lenve/p/11434074.html)
[Spring Boot 和 Docker 实现微服务部署](https://www.cnblogs.com/fengzheng/p/10329097.html)
[使用 Docker 部署 Spring Boot 项目，带劲！！](https://www.cnblogs.com/javastack/p/14034812.html)
[详解docker部署SpringBoot及如何替换jar包](https://www.cnblogs.com/toutou/p/docker_springboot.html)
[还在手动启动springboot项目？docker部署不香吗？](https://www.toutiao.com/i6843391272229536267)
[DOCKER 学习笔记5 Springboot+nginx+mysql 容器编排](https://www.cnblogs.com/ChromeT/p/12289177.html)
[Docker笔记](https://www.cnblogs.com/Hui4401/p/13758443.html)
```markdown
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
