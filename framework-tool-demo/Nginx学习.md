# Nginx学习

## Nginx
[搞懂Nginx一篇文章](https://blog.csdn.net/yujing1314/article/details/107000737)
[「查缺补漏」巩固你的Nginx知识体系](https://www.cnblogs.com/kkzhilu/p/13637077.html)
#### 1.Nginx简介
```markdown
Nginx是一个高性能的HTTP和反向代理服务器，特点是占用内存少，并发能力强，事实上Nginx的并发能力确实在同类型的网页服务器中表现较好。
主要的优点是：
    1.支持高并发连接，尤其是静态界面，官方测试Nginx能够支撑5万并发连接
    2.内存占用极低
    3.配置简单，使用灵活，可以基于自身需要增强其功能，同时支持自定义模块的开发
    4.使用灵活：可以根据需要，配置不同的负载均衡模式，URL地址重写等功能
    5.稳定性高，在进行反向代理时，宕机的概率很低
    6.支持热部署，应用启动重载非常迅速
Nginx在架构体系中的作用
    1.网关 （面向客户的总入口）
        网关：可以简单的理解为用户请求和服务器响应的关口，即面向用户的总入口
        网关可以拦截客户端所有请求，对该请求进行权限控制、负载均衡、日志管理、接口调用监控等，因此无论使用什么架构体系，都可以使用Nginx作为最外层的网关
    2.虚拟主机（为不同域名 / ip / 端口提供服务。如：VPS虚拟服务器）
        虚拟主机是一种特殊的软硬件技术，它可以将网络上的每一台计算机分成多个虚拟主机，每个虚拟主机可以独立对外提供www服务，这样就可以实现一台主机对外提供多个web服务，每个虚拟主机之间是独立的，互不影响的。
    3.路由（正向代理 / 反向代理）
    4.静态服务器
    5.负载集群（提供负载均衡）
```
#### 正向代理与反向代理
```markdown
- 正向代理
    先搭建一个属于自己的代理服务器
    1、用户发送请求到自己的代理服务器
    2、自己的代理服务器发送请求到服务器
    3、服务器将数据返回到自己的代理服务器
    4、自己的代理服务器再将数据返回给用户
作用：正向代理隐藏了用户，用户的请求被代理服务器接收代替，到了服务器，服务器并不知道用户是谁。
用途：当你用浏览器访问国外的网站时，被block(拒绝)时，你可以在国外搭建一个代理服务器，这样就可以正常访问了
- 反向代理
    1、用户发送请求到服务器（访问的其实是反向代理服务器，但用户不知道）
    2、反向代理服务器发送请求到真正的服务器
    3、真正的服务器将数据返回给反向代理服务器
    4、反向代理服务器再将数据返回给用户
作用：用户请求过多，服务器会有一个处理的极限。所以使用反向代理服务器接受请求，再用均衡负载将请求分布给多个真实的服务器。既能提高效率还有一定的安全性。
用途：如果不采用代理，用户的IP、端口号直接暴露在Internet（尽管地址转换NAT），外部主机依然可以根据IP、端口号来开采主机安全漏洞，所以在企业网，一般都是采用代理服务器访问互联网。
```
#### 负载均衡
```markdown
负载均衡:增加服务器的数量，构建集群，将请求分发到各个服务器上，将原来请求集中到单个服务器的情况改为请求分发到多个服务器。
负载均衡算法：
    轮询
    加权轮询
    随机算法
    一致性Hash
```
#### 动静分离
```markdown
为了加快网站的解析速度，可以把动态页面和静态页面交给不同的服务器来解析，加快解析的速度，降低由单个服务器的压力。
```
#### 常见的限流算法
[一文搞懂高频面试题之限流算法，从算法原理到实现，再到对比分析](https://www.cnblogs.com/exzlc/p/13639452.html)
```markdown
常见的限流算法有计数器、漏桶和令牌桶算法。
    计数器固定窗口算法
    计数器滑动窗口算法
    漏斗算法
    令牌桶算法
```
#### Linux安装Nginx和Nginx常用命令
```markdown
Nginx 如何在 Linux 安装:https://blog.csdn.net/yujing1314/article/details/97267369
查看版本：
./nginx -v
启动：
./nginx
关闭（有两种方式，推荐使用 ./nginx -s quit）：
 ./nginx -s stop
 ./nginx -s quit
重新加载 Nginx 配置：
./nginx -s reload

```
### 1.Nginx基础
[一文带你了解nginx基础](https://www.cnblogs.com/xiaoxiaotank/p/12967132.html)
```markdown
nginx是一个高性能的HTTP和反向代理Web服务器，还支持正向代理、透明代理、负载均衡、HTTP缓存等功能。
nginx始于2004年10月4日，使用C语言编写，2013年4月24日，nginx发布了v1.4.0稳定版，建议大家使用的版本高于此版本。
免费开源的nginx能够在众多同类产品中脱颖而出，是因为它具备低内存、高并发的优势，且配置简单，支持URL重写、GZIP，内置健康检查，
能自动检测集群服务器状态，跳过宕机服务器。
nginx常用功能和配置：
    一、限流（三种方式）
        * limit_conn_zone(限制连接数，针对客户端，即单一ip限流)
        * limit_req_zone(限制请求数，针对客户端，即单一ip限流)
        * ngx_http_unpstream_module（推荐，针对后台，如：有两台服务器，服务器A最大可并发处理10W条请求，服务器B最大可并发处理5W条请求，
        这样当12W请求按照负载均衡规则应当被分配给服务器A时，nginx为了防止A挂掉，所以将另外的2W分配给B）。
    二、安全配置
        * 版本安全  隐藏HTTP Response消息头Server中的版本号
        * IP安全 白名单配置（适用于授权IP较少的情况），可配置在http、server、location中
             黑名单配置（适用于授权IP较多的情况），可配置在http、server、location中
        * 文件安全
        * 连接安全（HTTPS）
    三、进程数、并发数、系统优化
        * 配置nginx.conf，增加并发量
        * 调整内核参数
            查看所有的属性值 ulimit -a
            临时设置硬限制（重启后失效） ulimit -Hn 100000
            临时设置软限制（重启后失效） ulimit -Sn 100000
            持久化设置（重启后仍生效） vim /etc/security/limits.conf
    四、GZIP
        * 作用：启用gzip后，服务器将响应报文进行压缩，有效地节约了带宽，提高了响应至客户端的速度。当然，压缩会消耗nginx所在电脑的cpu
        * 配置范围：http、server、location
    五、状态监控
        * 配置访问地址
        * 安装插件并重启
    六、负载均衡
        * 轮询
        * 加权轮询
        * 最少连接
        * 加权最少连接 性能更好的服务器权重应更高；
        * IP Hash
            算法：根据客户端ip进行Hash得到一个数值，然后使用该数值对服务器个数取模，得到的结果就是映射的服务器序号。
            （在服务器个数不变的情况下）可保证同一ip地址的请求始终映射到同一台服务器，解决了session共享问题。
        * uri Hash
            （在服务器个数不变的情况下）可保证同一uri始终映射到同一台服务器
            nginx在1.7.2之后支持uri_hash
    七、access日志切割
    八、动静分离
        * 概念：将动态请求和静态请求分开
        * 实现方式：
            （推荐）将静态文件存放在专门的服务器上，使用单独的域名
            另一种是将动态和静态文件放在一起，使用nginx区分
```
[Nginx 处理一个 HTTP 请求的全过程](https://www.cnblogs.com/iziyang/p/12933565.html)
```markdown

```
#### [ 从入门到精通-Nginx，图文并茂、负载均衡、动静分离、虚拟主机 附案例源码](https://www.cnblogs.com/chenyanbin/p/12521296.html)

#### 2.[使用 Nginx 部署前后端分离项目，解决跨域问题](https://www.cnblogs.com/lenve/p/11576581.html)

[Nginx 从入门到实践，万字详解！](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247486730&idx=1&sn=2031330f25c91be1b1bbb4b48aeba63e&chksm=cea242c1f9d5cbd7896d2f3ccdc474afcba389e1f469bda8e125ee5e9cac3d68588eeb675dd6&mpshare=1&scene=23&srcid=&sharer_sharetime=1588724499740&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)

[SpringBoot利用nginx实现生产环境的伪热更新](https://www.cnblogs.com/fishpro/p/spring-boot-study-hotstart.html)

[Nginx极简入门教程！](https://www.cnblogs.com/lenve/p/10977548.html)
#### Nginx负载均衡【2+】
[Nginx的负载均衡](https://www.cnblogs.com/death00/p/11611672.html)

[nginx负载均衡](https://www.cnblogs.com/helloxiaoduan/p/12586307.html)
```markdown
负载平衡（Load balancing）是一种在多个计算机（网络、CPU、磁盘）之间均匀分配资源，以提高资源利用的技术。
使用负载均衡可以最大化服务吞吐量，可能最小化响应时间，同时由于使用负载均衡时，会使用多个服务器节点代单点服务，也提高了服务的可用性。
负载均衡的实现可以软件可以硬件，硬件如大名鼎鼎的F5负载均衡设备，软件如NGINX中的负载均衡实现，又如SpringcloudRibbon组件中的负载均衡实现。
负载均衡算法：
    1. 随机访问
    2. 轮训访问
    3. 轮训加权
    4. 随机加权
    5. IP-Hash
```
### 2.Nginx配置文件
[一篇文章掌握Nginx核心文件结构](https://www.cnblogs.com/dtyy/p/14123785.html)
```markdown
#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root   html;
            index  index.html index.htm;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}
```
### docker 安装Nginx
[Docker笔记（七）：常用服务安装——Nginx、MySql、Redis](https://www.cnblogs.com/spec-dog/p/11320513.html)
```markdown
拉取镜像:docker pull nginx  ## 不加标签默认会拉取最新的（latest）镜像
创建目录:  mkdir -p ~/docker/nginx/html  ~/docker/nginx/logs  ~/docker/nginx/conf
先不指定映射路径启动一个容器
    $ docker run -d -p 80:80 --name nginx nginx  ## 这里如果直接指定映射路径运行会报错，
将运行容器的配置文件复制到宿主机目录下:
    $ docker cp Nginx容器id:/etc/nginx/nginx.conf  ~/docker/nginx/conf/
删除容器并重新运行
    ~$ docker stop Nginx容器id
    ~$ docker rm Nginx容器id
    ~$ docker run -d -p 80:80 --name nginx \
        -v ~/docker/nginx/html:/usr/share/nginx/html \
        -v ~/docker/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
        -v ~/docker/nginx/logs:/var/log/nginx nginx;
更新配置后重新加载
    ~$ docker kill -s HUP nginx  # 类似于 nginx -s reload
```
### 