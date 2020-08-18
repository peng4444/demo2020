# Spring Boot+Vue.js+FastDFS 实现分布式图片服务器
[视频地址：Spring Boot+Vue.js+FastDFS 实现分布式图片服务器](https://www.bilibili.com/video/BV1HQ4y1M7w4?p=2)

[fastDFS-java客户端](https://github.com/happyfish100/fastdfs-client-java)

[从入门到精通(分布式文件系统架构)-FastDFS，FastDFS-Nginx整合，合并存储，存储缩略图，图片压缩，Java客户端](https://www.cnblogs.com/chenyanbin/p/12782615.html)
## 1.什么是文件系统
```markdown
文件系统是连接计算机操作系统和计算机硬件驱动之间的桥梁，操作系统通过文件系统提供的接口取存取文件，
用户通过操作系统访问磁盘上的文件。
```
## 2.分布式文件系统
```markdown
单个硬盘无法满足要求，分布式文件系统可以把多个地点的文件系统连接起来。实现备份。提供附近访问，提高访问速度。
```
## 3.主要分布式文件系统
```markdown
NFS:网络文件系统（机房文件系统）
GFS:google文件系统
HDFS：
主要分布式文件服务提供商： 阿里的OSS,七牛云存储，百度云存储。
```
## 4.fastDFS
```markdown
FastDFS是一个开源的轻量级分布式文件系统。它解决了大数据量存储和负载均衡等问题。特别适合以中小文件（建议范围：4KB<file_size<500MB）
为载体的在线服务，如相册网站、视频网站等等。在UC基于FastDFS开发向用户提供了：网盘，社区，广告和应用下载等业务的存储服务。
fastDFS非常适合图片等等小文件存储。
```
## 5.fastDFS工作原理
```markdown
FastDFS是一款开源的分布式文件系统，功能主要包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，解决了文件大容量存储和高性能访问的问题。
FastDFS特别适合以文件为载体的在线服务，如图片、视频、文档等等。
1.tracker server：跟踪服务器，主要做调度工作，起负载均衡的作用。在内存中记录集群中所有存储组和存储服务器的状态信息，是客户端和数据服务器交互的枢纽。
Tracker是FastDFS的协调者，负责管理所有的storage server和group，每个storage在启动后会连接Tracker，告知自己所属的group等信息，
并保持周期性的心跳，tracker根据storage的心跳信息，建立group==&gt;[storage server list]的映射表。
2.storage server：存储服务器（存储节点或数据服务器），文件和文件属性（meta data）都保存到存储服务器上。Storage server直接利用OS的文件系统调用管理文件。
Storage server以group为单位组织，一个group内包含多台storage机器，数据互为备份，存储空间以group内容量最小的storage为准，
所以建议group内的多个storage尽量配置相同，以免造成存储空间的浪费。以group为单位组织存储能方便的进行应用隔离、负载均衡、副本数定制（group内storage server数量即为该group的副本数）。
storage接受到写文件请求时，会根据配置好的规则，选择其中一个存储目录来存储文件。为了避免单个目录下的文件数太多，在storage第一次启动时，
会在每个数据存储目录里创建2级子目录，每级256个，总共65536个文件，新写的文件会以hash的方式被路由到其中某个子目录下，然后将文件数据直接作为一个本地文件存储到该目录中。
3.client：客户端，作为业务请求的发起方，通过专有接口，使用TCP/IP协议与跟踪器服务器或存储节点进行数据交互。FastDFS向使用者提供基本文件访问接口，
比如upload、download、append、delete等，以客户端库的方式提供给用户使用。
4.group ：组， 也可称为卷。 同组内服务器上的文件是完全相同的 ，同一组内的storage server之间是对等的， 文件上传、 删除等操作可以在任意一台storage server上进行 。
5.meta data ：文件相关属性，键值对（ Key Value Pair ）。
```
![集群架构图](http://58.62.207.50:13983/sznf/images/dfs1.jpg)
### 架构流程
```markdown
1.Storager会定期的告诉Tracker自己有存储空间，是否还可以继续存放图片，自己的是不是正常运行，有没有蹦。 
2.当client需要存放图片的时候，她会问Tracker:Storager是否有存储的空间，然后tracker就会看Storager是否有空间，如果有空间，则会告诉Client具体的ip和端口号。 
3.Client那这Ip和端口号去找Storager，然后我们就可以存储图片了。
```
### 上传下载
```markdown
上传
首先客户端请求Tracker服务获取到存储服务器的ip地址和端口，然后客户端根据返回的IP地址和端口号请求上传文件，存储服务器接收到请求后
生产文件，并且将文件内容写入磁盘并返回给客户端file_id、路径信息、文件名等信息，客户端保存相关信息上传完毕。
下载
客户端带上文件名信息请求Tracker服务获取到存储服务器的ip地址和端口，然后客户端根据返回的IP地址和端口号请求下载文件，
存储服务器接收到请求后返回文件给客户端。
```
## 6.fastDFS安装与配置
[参考资料：FastDFS安装教程直接复制实测，fastDFS6不能使用](https://my.oschina.net/wyn365/blog/3211722)
```markdown
1.分别在192.168.72.128和192.168.72.129上安装tracker
2.安装libevent yum –y install libevent libevent-devel
3.安装libfastcommon依赖环境
....
....
```
## 7.启动命令
```markdown
service fdfs_trackerd start -- 启动tracker
service fdfs_storaged start -- 启动storage
cd /usr/bin/ ./nginx  #启动Nginx
```
## 8.文件管理服务
```markdown
文件管理服务通过http方式上传文件，删除文件，查询文件的功能。
```
## 9.文件上传下载
```markdown
-- 拷贝一张图片baobao.png到Centos服务器上的 /root/目录下；
/usr/bin/fdfs_upload_file /etc/fdfs/client.conf /root/baobao.png
成功后返回：
group1/M00/00/00/wKgAA135BdKAEOs1ADW668UZmDM218.png
访问文件
http://192.168.72.128/group1/M00/00/00/wKgAA135BdKAEOs1ADW668UZmDM218.png
对应storage服务器上的/usr/local/fastdfs/storage/data/00/00/wKgAA135BdKAEOs1ADW668UZmDM218.png文件；
```

## 
[分布式文件系统之FastDFS](https://www.cnblogs.com/cailijia52o/p/10263514.html)

[设计一个文件系统，需要考虑哪些因素？](https://www.cnblogs.com/cxuanBlog/p/12517595.html)
[【FastDFS】FastDFS 分布式文件系统的安装与使用，看这一篇就够了！！](https://www.cnblogs.com/binghe001/p/13222361.html)
[一口气搞懂「文件系统」，就靠这 25 张图了](https://www.cnblogs.com/xiaolincoding/p/13499209.html)

