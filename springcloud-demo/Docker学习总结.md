# Docker学习总结

## Docker基础知识
[Docker学习参考博客：](https://www.funtl.com/zh/docs-docker/)
[docker安装参照>>史上最详细的Docker安装手册](https://www.cnblogs.com/zhizihuakai/p/12633724.html)
https://www.cnblogs.com/jian0110/p/14139044.html
### 1.Docker简介
[还不懂Docker？一个故事安排的明明白白！](https://www.cnblogs.com/xuanyuan/p/14003524.html)
[Docker笔记（一）：什么是Docker](https://www.cnblogs.com/spec-dog/p/11186877.html)
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
总结Docker相对传统虚拟化技术的优势如下：
    - 更高的资源利用率：Docker不需要硬件虚拟与运行完整操作系统的开销，所以资源利用率更高，同样配置的主机，采用Docker往往可以运行更多数量的应用。
    - 更高效的使用体验：在操作系统上安装一些常用软件，如mysql，redis等，往往需要折腾好一阵，有些还要手动安装各种依赖，而采用Docker，可能几行命令就可以让一个服务快速运行起来。
    - 一致的运行环境：Docker镜像功能可以把程序运行需要的环境进行封装，确保程序在开发、测试、生产环境都能保持一致性，避免因环境不一致导致程序运行异常。
    - CI/CD支持：使用Docker可以定制镜像来实现持续集成、持续部署，如基于gitlab + jenkins pipeline + docker的自动化部署。
    - 更轻松的维护：因为Docker保证了运行环境的一致性，因此应用的迁移或缩放将变得很容易；Docker的分层存储与镜像技术，  
        也使得应用重复部分的复用变得更简单，基于基础镜像可以进一步扩展定义自己的镜像，也可以直接使用官方镜像来使用。
```
### 2.Docker的基本架构
![](http://blog.jboost.cn/assets/docker-arch.png)
```markdown
主要包括几部分：
    - 1.Docker daemon（Docker守护进程 dockerd）：Docker的执行引擎，负责监听处理Docker客户端请求与管理Docker相关对象，  
    如镜像、容器、网络、数据卷等。一个Docker守护进程可与其它Docker守护进程进行通信，作为Docker服务进行管理。
    - 2.Docker client（Docker客户端 docker）：Docker客户端（docker CLI命令）是大多数用户用来与Docker守护进程交互的方式，  
    比如你在命令行执行docker run，Docker客户端将发送该命令请求到Docker守护进程，由守护进程执行。  
    Docker客户端可通过REST API, UNIX Socket或网络接口来与Docker守护进程进行通信，并且可与多个Docker守护进程进行通信。
    - 3.Docker Registry（Docker注册中心）：用来存储Docker镜像的仓库，类似于Maven的Nexus。  
    Docker官方提供了一个公共镜像仓库Docker Hub（https://hub.docker.com/），docker相关命令默认会从Docker Hub上搜索与下载镜像，  
    我们可以配置一些国内镜像仓库地址来进行加速，甚至搭建自己的私有镜像仓库。
    - 4.Docker Objects：Docker管理的对象，主要包括镜像、容器、网络、数据卷等。
```
### 3.Docker管理的对象
[Docker笔记（二）：Docker管理的对象](https://www.cnblogs.com/spec-dog/p/11188629.html)
```markdown
1. 镜像
    Docker的镜像是一个特殊的文件系统，提供了运行时需要的程序、库、资源、配置等文件，还包含一些为运行时准备的配置参数   
（如环境变量、匿名数据卷、用户等），镜像不包含任何动态数据，其内容在构建之后也不会被改变。  
镜像的文件系统有一个分层存储的概念，采用的是Union FS技术，因此，镜像并不是简单地由一组文件组成，而是由多层文件系统叠加联合组成。  
2. 容器
    容器是一个动态的运行时的概念，容器可以被创建、启动、停止、删除等。容器运行实质上就是运行一个进程，但与那些直接在宿主机上运行的进程不同，  
容器运行在自己的独立的隔离的命名空间中——拥有自己的root文件系统、网络配置、进程空间，甚至自己的用户ID空间，因此虽然是以进程的形式运行，  
但好像是运行在一个独立的系统中一样，这样相比直接运行于宿主机的进程，容器的运行显得更为安全。  
3. 数据卷  
    数据卷是一个独立于容器，可供一个或多个容器使用的特殊目录，它绕过了Union FS，不会随容器的销毁而消亡。  
    数据卷具备如下特性：
        - 1.可以在容器之间共享和重用
        - 2.对数据卷的修改会立马生效
        - 3.数据卷的更新，不会影响到镜像
        - 4.数据卷默认会一直存在，不会随容器的删除而消亡  
4. 网络  
    在运行容器时，只需要指定容器服务端口与宿主机端口的映射，就可以通过宿主机IP与映射的端口访问容器服务了，  
因为Docker默认使用了Bridge的模式来实现容器与外部的通信。
    Docker的网络子系统通过使用一些驱动程序，是可插拔式的，默认提供了如下几种驱动：  
        - 1.bridge：默认的网络驱动。运行在容器中的应用程序一般是通过网桥与外部进行通信。  
        - 2.host：容器直接使用宿主机的网络通信。host只在基于Docker17.06或以上版本的Swarm服务中可用  
        - 3.overlay：overlay可将多个Docker daemon进程连接起来使得Swarm服务之间能相互通信，也可以将overlay用于Swarm服务与容器之间，  
            或运行在不同Docker daemon上的容器之间的通信，不需要操作系统层面的路由配置。
        - 4.macvlan：macvlan允许你分配一个mac地址给容器，让它像一台物理设备一样加入你的网络中。  
            Docker daemon通过mac地址将请求路由给容器，适用于那些希望直接连到物理网络的遗留应用。
        - 5.none：禁用所有网络。一般与一个自定义的网络驱动一起使用。none不能用于Swarm服务。
        - 6.其它第三方网络插件：可从Docker Hub或其它第三方供应商获取安装。
    总之，bridge适用于在同一台宿主机运行多个容器的场景；host适用于不应与宿主机进行网络隔离的场景；  
    overlay适用于运行在不同宿主机上的容器间通信，或多个应用通过Swarm服务来共同协作的场景；  
    macvlan适用于从虚拟机迁移配置或希望容器作为物理机一样使用网络的场景。
```
### 2.Docker Ubuntu安装&启动&测试  -- 一般使用非root用户
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
### 3.Docker CentOS安装卸载
[Docker CentOS安装](https://www.funtl.com/zh/docs-docker/CentOS-%E5%AE%89%E8%A3%85-Docker.html#%E4%BD%BF%E7%94%A8-yum-%E5%AE%89%E8%A3%85)
[Docker学习（二）安装Docker和卸载](https://blog.csdn.net/haiyangyiba/article/details/88805484)
[设置非root账号不用sudo直接执行docker命令](https://www.cnblogs.com/bolingcavalry/p/14136649.html)
[Docker安装]( https://blog.csdn.net/laughing1997/article/details/84305615) 
[Docker笔记（三）：Docker安装与配置](https://www.cnblogs.com/spec-dog/p/11194521.html)
```markdown
可以先删除旧版本，使用yum安装依赖包和添加国内的yum软件源
1.删除旧版本docker
    sudo yum remove docker \
                      docker-client \
                      docker-client-latest \
                      docker-common \
                      docker-latest \
                      docker-latest-logrotate \
                      docker-logrotate \
                      docker-selinux \
                      docker-engine-selinux \
                      docker-engine
2.安装依赖包
     sudo yum install -y yum-utils \
               device-mapper-persistent-data \
               lvm2
    添加yum软件源docker (鉴于国内网络问题，强烈建议使用国内源)
     sudo yum-config-manager \
        --add-repo \
        https://mirrors.ustc.edu.cn/docker-ce/linux/centos/docker-ce.repo
3.安装docker ce
    sudo yum makecache # 更新 yum 软件源缓存
    sudo yum install docker-ce # 安装 docker-ce
    sudo systemctl enable docker # 允许开机启动 Docker CE
    sudo systemctl start docker  # 启动 Docker CE
    sudo groupadd docker # 建立 docker 组
    sudo usermod -aG docker $USER # 将当前用户加入 docker 组
4.退出当前终端并重新登录，进行如下测试。
    docker run hello-world  # 测试 Docker 是否安装正确  docker会创建一个hello-world的镜像
```
### 4.Docker 镜像加速器 Ubuntu 16.04+、Debian 8+、CentOS 7 
[Docker 镜像加速器](https://www.funtl.com/zh/docs-docker/Docker-%E9%95%9C%E5%83%8F%E5%8A%A0%E9%80%9F%E5%99%A8.html#ubuntu-14-04%E3%80%81debian-7-wheezy)
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
[Docker笔记（四）：Docker镜像管理](https://www.cnblogs.com/spec-dog/p/11198723.html)
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
### Docker操作容器
[Docker笔记（六）：容器管理](https://www.cnblogs.com/spec-dog/p/11224467.html)
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
### Docker搭建私有仓库
[快速搭建Docker私有仓库](https://www.cnblogs.com/FLY_DREAM/p/14141776.html)
### Docker数据管理
[Docker笔记（八）：数据管理](https://www.cnblogs.com/spec-dog/p/11343794.html)
### Docker网络管理
[Docker笔记（九）：网络管理](https://www.cnblogs.com/spec-dog/p/11471346.html)
### Dockerfile详解与最佳实践
[Docker笔记（十一）：Dockerfile详解与最佳实践](https://www.cnblogs.com/spec-dog/p/11570394.html)
```markdown
Dockerfile是一个文本文件，包含了一条条指令，每条指令对应构建一层镜像，Docker基于它来构建一个完整镜像。
1. 理解构建上下文（build context）
    Docker镜像通过docker build指令构建，该指令执行时当前的工作目录就是docker构建的上下文，即build context，  
        上下文中的文件及目录都会作为构建上下文内容发送给Docker Daemon。  
    docker build --no-cache -t helloapp:v2 -f dockerfiles/Dockerfile context  
    如上–no-cache 表示镜像构建时不使用缓存，-f 指定Dockerfile文件位置， context 指定build context目录。  
最佳实践建议:  
    - 1.使用.dockerignore来排除不需要加入到build context中的文件，类似于.gitignore
    - 2.不要安装不必要的包，所有包含的东西都是镜像必须的，非必须的不要包含。
    - 3.解耦应用，如果应用有分层，解耦应用到多个容器，便于横向扩展，如web应用程序栈包含web服务应用，数据库，缓存等。
    - 4.最少化镜像层数：只有RUN、COPY、ADD指令会创建镜像层，其它指令创建临时的中间镜像，不会增大镜像构建的大小
    - 5.如果可能，尽可能使用多阶段构建，只复制你需要的组件到最终镜像，这使得你可以在中间构建阶段包含工具与debug信息，同时又不会增大最终镜像的大小。
    - 6.排序多行参数：将参数按字母排序，有利于避免包重复，及后续的维护与提高易读性
2.FROM
    FROM指定基础镜像，每一个定制镜像，必须以一个现有镜像为基础。因此一个Dockerfile中FROM是必须的指令，并且必须是第一条。  
    使用格式:  
        FROM <image>:<tag>
        # 注释以#开头。基础镜像的tag可不指定，默认使用latest
        # 示例：FROM mysql:5.7
    最佳实践建议:
        - 1.如果不想以任何镜像为基础，则可以使用FROM scratch
        - 2.尽量使用官方镜像作为基础镜像
        - 3.推荐使用Alpine镜像，因为它足够轻量级（小于5MB），但麻雀虽小五脏俱全，基本具有Linux的基础功能
3. RUN
    用来执行命令行命令，是最常用的指令之一。
4. COPY | ADD
    COPY从构建上下文的目录中复制文件/目录到镜像层的目标路径。  
5. CMD
    CMD指定容器的启动命令。容器实质就是进程，进程就需要启动命令及参数，CMD指令就是用于指定默认的容器主进程的启动命令的。  

```
### Docker Compose
[Docker Compose](https://www.funtl.com/zh/docs-docker/%E4%BB%80%E4%B9%88%E6%98%AF-Docker-Compose.html)
[Centos7安装最新 Docker-compose 的正确姿势](https://blog.csdn.net/cookily_liangzai/article/details/82496934 )
[Docker笔记（十二）：Docker Compose入门](https://www.cnblogs.com/spec-dog/p/11897009.html)
```markdown
Docker Compose是Docker官方编排（Orchestration）项目之一，负责快速的部署分布式应用。 https://github.com/docker/compose
    允许用户通过一个单独的docker-compose.yml模板文件（YAML格式）来定义一组相关联的应用容器为一个项目（project）。
Compose中两个重要的概念：
    - 1.服务（service）：包含多个运行相同镜像的容器实例
    - 2.项目（project）：由一组关联的应用容器（服务）组成一个完整的业务服务单元，在docker-compose.yml中定义 
Docker Compose安装：
    1.（从 官方 GitHub Release 处直接下载编译好的二进制文件即可）
    $ sudo curl -L https://github.com/docker/compose/releases/download/1.24.1/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
    $ sudo chmod +x /usr/local/bin/docker-compose
    卸载：二进制包方式安装的，删除二进制文件即可 $ sudo rm /usr/local/bin/docker-compose
    检验安装结果：docker-compose --version
Compose模板文件 
    模板文件是使用Compose的核心，定义了一组相关联的应用容器，使之构成一个项目，里面大部分指令跟docker run相关参数的含义是类似的。  
    默认的模板文件名称为docker-compose.yml，为YAML格式。
    version: '3'
    services:
        web:
            build: .
            depends_on:
                - db
                - redis
        redis:
            image: redis
        db:
            image: mysql
    Compose模板文件可以动态读取主机的系统环境变量与当前目录下.env文件中的变量，通过${xx}引用。 
Compose命令
    Compose命令默认是针对项目本身，也可以指定为项目中的服务或容器。docker-compose 命令的基本使用格式为：
        docker-compose [-f=<arg>...] [options] [COMMAND] [ARGS...]
        命令选项 
        -f, –file 指定模板文件，默认为docker-compose.yml，可多次指定
        -p, –project-name 指定项目名称，默认为所在目录名称
        –x-networking 使用Docker的可插拔网络特性
        –x-networking-driver 指定网络驱动，默认为bridge
        –verbose 输出更多调试信息
        -v, –version 打印版本信息
```
### Docker容器日志采集实践
[容器日志采集实践](https://www.cnblogs.com/spec-dog/p/12624470.html)


## Docker技术
【Docker】[docker笔记](https://www.cnblogs.com/spec-dog/tag/docker/)
[随笔分类 - 测试高级进阶技能系列 - Docker](https://www.cnblogs.com/poloyy/category/1870863.html)

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
### Docker命令
```markdown
$ docker system info ##查看docker存储驱动类型
$ docker search 应用名称  ## 查看应用镜像
$ docker pull 应用名称  ## 拉取应用镜像【默认是镜像库最新版本，可以在应用名称:版本号拉取指定版本】
$ docker image ls  ## 查看所有的docker镜像
$ docker image rm  镜像编号 ##删除docker镜像  docker image rm alpine:latest
$ docker image pull ##下载镜像   docker image pull alpine:latest
$ docker image inspect  ##展示镜像的细节包括镜像层数据和元数据
$ docker image history  ## 查看构建镜像过程中都执行了那些指令
$ docker image build ## 读取Dockerfile文件，并且将应用程序容器化
$ docker container run <options> <image>:<tag> <app> ##指定要启动的镜像已及要运行的应用
$ docker  stop   <容器名称>|<容器id>  .....
$ docker  start   <容器名称>|<容器id>  .....
$ docker container rm  <容器名称>|<容器id>  .....
$ docker container run -it ubuntu:latest /bin/bash ##运行容器版本的Ubuntu Linux
$ docker exec -it <容器名称>|<容器id> /bin/bash
$ docker attach <容器名称>|<容器id> ## 通过attach命令进入容器后通过exit退出，容器将会变为exited状态，exec不会
$ docker container ls ##查看容器正在运行的容器列表
$ docker container prune ## 删除所有的已停止的容器
$ docker export <容器名称>|<容器id> > exportUbuntu.tar  ## 用export命令都出容器快照
$ cat exportUbuntu.tar | docker import - pbj/ubuntu:v1  ## 用import命令从容器快照文件中再导入为镜像，导入到镜像 xu/centos:v1
    导入容器快照可以是一个路径，也可以是一个 URL
$ docker-compose up ##启动应用 -d后台启动
$ docker-compose down ##关闭应用
$ docker-compose ps  ##查看应用状态
$ docker-compose top ##列出各个服务内运行的进程
$ docker-compose stop ##停止应用
$ docker-compose restart ##重启应用
$ docker network ls ## Docker主机上的网络
$ docker network inspect bridge 
$ docker network create -d bridge test-net # 创建网络
    参数说明：
    -d：参数指定Docker网络类型，有bridge、overlay。其中overlay网络类型用于Swarm mode，此处忽略。
$ docker network rm test-net ## 删除网络
$ docker commit -m="add redis" -a="xu" fda2c65d7e02  pbj/centos:v1.0.1  ## 使用commit命令提交容器副本
    各个参数说明：
    -m: 提交的描述信息
    -a: 指定镜像作者
    fda2c65d7e02：容器 ID，可以通过 docker ps -a 命令查看
    pbj/cnetos:v1.0.1: 指定要创建的目标镜像名和标签 tag
```

### 
[docker配置mysql主从与django实现读写分离](https://www.cnblogs.com/yscl/p/11992175.html)



