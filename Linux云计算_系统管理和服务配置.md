# Linux云计算_系统管理和服务配置
>> [参考视频：千峰教育Linux]()

[TOC]


## 1.Linux网络管理
>> linux网络管理部分主要是计算机网络中的知识。
## 2.Linux系统管理与服务配置
### 2.1.linux&CentOS介绍安装
>> *p1,p2,p3开始都是废话,p4p5Vmware.Centos安装，p6阿里云安装（重点）*
```markdown
系统配置：VMware，CentOS64_7
CentOS安装，硬盘分区
	/boot 1024M  一般是保存系统启动文件
	/swap 1024M  当内存不足时，进行内存交互，充当内存，但是速度慢
	/             剩下的硬盘空间
网卡地址： /etc/systemconfig/network-scripts/ifcfg-ens33
** vim快捷键,命令行
Alt + . 引出上一个命令的最后一个参数
history 查看历史命令   ！ number  调用历史命令
别名：alias 111='ls-la'  unalias 111 取消别名   alias查看已经命名的别名
type：查看命令类型 type -a ls 
date [-u | --utc | --universal ] [时间格式]      #修改时间
man手册：Linux帮助手册
man man    man ls
```
### 2.Linux目录结构
![Linux目录结构](http://dl.iteye.com/upload/picture/pic/115222/e063287e-07a0-333e-b4f5-9e418ec52ce1.png)
####  比较重要的目录 
```markdown
在Linux系统中，有几个目录是特别需要注意的，以下提供几个需要注意的目录，以及预设相关的用途：　
/etc：这个目录相当重要，如前所述，你的开机与系统数据文件均在这个目录之下，因此当这个目录被破坏，那你的系统大概也就差不多该死掉了！
而在往后的文件中，你会发现我们常常使用这个目录下的/etc/rc.d/init.d这个子目录，因为这个init.d子目录是开启一些Linux系统服务的scripts的地方。而在/etc/rc.d/rc.local这个文件是开机的执行档。　      
/bin,/sbin,/usr/bin,/usr/sbin：这是系统预设的执行文件的放置目录，例如root常常使用的userconf,netconf,perl,gcc,c++等等的数据都放在这几个目录中，
所以如果你在提示字符下找不到某个执行档时，可以在这四个目录中查一查！其中,/bin,/usr/bin是给系统使用者使用的指令，而/sbin,/usr/sbin则是给系统管理员使用的指令！  　       
/usr/local：这是系统预设的让你安装你后来升级的套件的目录。例如，当你发现有更新的Web套件（如Apache）可以安装，而你又不想以rpm的方式升级你的套件，
则你可以将apache这个套件安装在/usr/local底下。安装在这里有个好处，因为目前大家的系统都是差不多的，所以如果你的系统要让别人接管的话，
也比较容易上手呀！也比较容易找的到数据喔！因此，如果你有需要的话，通常我都会将/usr/local/bin这个路径加到我的path中。　      
/home：这个是系统将有账号的人口的家目录设置的地方。    　
/var：这个路径就重要了！不论是登入、各类服务的问题发生时的记录、以及常态性的服务记录等等的记录目录，所以当你的系统有问题时，
 就需要来这个目录记录的文件数据中察看问题的所在啰！而mail的预设放置也是在这里，所以他是很重要的        
/usr/share/man,/usr/local/man：这两个目录为放置各类套件说明档的地方，例如你如果执行man man，则系统会自动去找这两个目录下的所有说明文件
```    
#### 文件种类
```markdown
Linux 的文件种类主要有 查看命令：file /etc/hosts  ll -d /etc/hosts
    正规文件( regular file )：就是一般类型的文件，在由 ls –al 所显示出来的属性方面，第一个属性为 [ - ]。
    另外，依照文件的内容，又大略可以分为两种文件种类：
    纯文字文件(ascii) ：这是 Unix 系统中最多的一种啰，几乎只要我们可以用来做为设定的文件都属于这一种；
    二进制文件(binary) ：通常执行档除了 scripts （文字型批次文件）之外，就是这一种文件格式；   
    目录 (directory)：就是目录！第一个属性为 [ d ]；
    连结档 (link)：就是类似 Windows 底下的快捷方式啦！第一个属性为 [ l ]；
    设备档 (device)：与系统周边相关的一些文件，通常都集中在 /dev 这个目录之下！通常又分为两种：
    区块 (block) 设备档 ：就是一些储存数据，以提供系统存取的接口设备，简单的说就是硬盘啦！例如你的一号硬盘的代码是/dev/hda1等等的文件啦！第一个属性为[b]；
    字符 (character) 设备档 ：亦即是一些串行端口的接口设备，例如终端，键盘、鼠标等等！第一个属性为 [ c ]。
```
[https://www.cnblogs.com/f-ck-need-u/p/10430642.html]: 搞懂Linux下的几种文件类型
#### vi编辑器
```markdown
vi编辑器的模式分类
	1.command mode（  All）
	2.edit mode(  INSERT)
	3.visual edit mode ( VISUAL)
	4.extend command mode ( :wq)
编辑器文本操作
	1.光标定位
		光标移动  h 左 j 上 k 下 l 右
		0 光标移动到行首 $ 光标移动到行尾			
		gg 第一行第一位 G  最后一行第一位
		3G 		 	进入第三行第一位
		/（tring） 输入字符串快速定位到某一行
		/^d			
		/txt$
    2.文本编辑
    	y 复制 yy 3yy ygg yG
    	d 删除 3dd dgg dG
    	p 粘贴
    	x 删除光标所在的字符
    	D 从光标出删除到行尾
    	u undo撤销
    	^r redo 重做
    	r 可以用来修改一个字符
    3.进入插入模式  i a o A
    	: 进入行末模式
    	v 进入可视模式
    	^v 进入可视块模式
    	V  进入可视行模式 
    	R  进入替换模式
    4.扩展命令模式 ：
    	a.保存退出
    		：10 进入第十行
    		：w 保存
    		:w /root/file.txt 另存为
    		: q 退出
    		:wq 保存退出
    		：w！ 强制保存
    		：q！ 不保存平并退出
    		：wq! 强制保存退出
    		：x 保存并退出  ZZ
    	b. 查找替换
    		：范围 s/old/new/选项
    		：1,5 s/abc/sss/ 1到5行 sss替换abc
    	vimdiff /etc/hosts hosts1 两个窗口打开，区分两个文件的内容
    5.Linux文件时间
    stat /etc/hosts    查看文件的详细属性
    type 查看命令类型  命令是alias 还是内置命令，还是某一个文件
    file 查看文件类型  例如文本文件，二进制文件。。。
    stat 查看文件的属性 文件的名称，大小，权限，time
```
### 3.用户管理
```markdown
用户
    对于支持多任务的Linux系统来说，用户就是获取资源的凭证。
    groupadd ,groupdel
    useradd ,userdel
    passward , chage
权限
    权限用来控制用户对计算机资源(CPU、内存、文件等)的访问，一般会分为认证和授权两步。比如用户先经过认证机制(authentication)登录系统，
    然后由授权系统(authorization)对用户的操作进行授权。
进程
    进程是任何支持多道程序设计的操作系统中的基本概念。通常把进程定义为程序执行时的一个实例。因此，如果有10个用户同时运行vi，
    就会有10个独立的进程(尽管它们共享同一份可执行代码)。
    实际上，是进程在帮助我们完成各种任务。进程就是用户访问计算机资源的代理，用户执行的操作其实是带有用户身份信息的进程执行的操作。
进程权限
    既然是进程在为用户执行具体的操作，那么当用户要访问系统的资源时就必须给进程赋予权限。
    也就是说进程必须携带发起这个进程的用户的身份信息才能够进行合法的操作。
```
[细说Linux系统用户/组管理](https://www.cnblogs.com/f-ck-need-u/p/7011460.html )
### 4.基本权限
```markdown
文件权限
-rw-rw-r--. 1 pbj pbj  14 Feb 23 21:29 a.txt
			  属主 属组
文件类型： -文件    d :文件夹   l :软连接
          -|---|---|---     rwx 421
	        用户 组  其他组	
	   UGO设置权限
	   1.更改文件的属主，属组
	   chown root a.txt  修改属主权限
	   chown root.root a.txt  修改属主和属组权限
	   chown 	.root a.txt 	修改属组的权限
	   chgrp it a.txt 修改文件属组
	   chgrp -R it a.txt 修改文件属组(递归修改，包括文件夹中的文件） 
	   2.修改权限      chmod 7777 a.txt 最高级权限
	   chmod u+x a.txt 属主添加执行权限
	   chmod u-x a.txt 属主减去执行权限
	   chmod a=rwx a.txt 所有人等于读写执行权限  chmod 
	   chmod a=- a.txt 表示所有人都没有权限
	   chmod ug=rw,o=r a.txt 属主属组等于读写，其他人等于只读
	   ll a.txt 以长模式方式查看文件权限
	   chmod 664 a.txt 属主属组读写权限，其他人读权限
	   练习： 1. 对目录没有W权限，对文件有任何权限   p36
	   		 2.对目录有W权限，对文件没有任何权限
	   		 总结：对目录有w权限，可以在目录中创建新文件，可以删除目录中的文件（跟文件权限无关）
	   		 	  对目录没有w权限，不可以在目录中创建新文件，不可以删除目录中的文件
	   		 注意事项：文件  小心给予执行权限
	   		 		 目录   小心给予写权限
	   FACL基本权限 访问控制列表
       		getfacl a.txt              查询权限
       		setfacl -m u:pbj:rw a.txt    设置权限
       		setfacl -x .........        删除权限
       查看帮助  man setfacl
       复制权限： grep file1 |setfacl --set-file=- file2
       3.ACL 高级特性
       		mask：用于临时用户或者组的权限（出属主和其他人）的权限,mask决定他们的最高权限
       			：方便管理文件权限，其他人的权限值为空 setfacl -m o::- a.txt
       			setfacl -m mask::--- a.txt
       		default：继承
       			setfacl -m u:pbj:rwx /file2 赋予pbj对/file2的rwx权限
       			setfacl -m default:u:pbj:rwx /file2 赋予pbj对以后的在file2下新建的文件有rwx权限    			
```
### 5.高级权限，文件属性，进程掩码 
```markdown
高级权限
suid:   ll /usr/bin/passwd   root以外的用户修改密码可以这个文件
		-rwsr-xr-x. 1 root root 27832 Jun 10  2014 /usr/bin/passwd
sgid: chmod g+s /home/hr   属组加上高级权限
sticky：
文件谁可以删除：
			root
			文件所有者
			文件的目录的所有者
```
[特殊权限 SUID、SGID、Sticky]( https://www.cnblogs.com/Q--T/p/7864795.html)
```markdown
文件属性：lsattr:列出文件属性
		chattr:修改文件属性        root可能无法删除文件
		mask umask:
		umask: 进程，新建文件，目录的默认权限会受到umask的影响，umask表示要减去的权限
```
[Linux umask](https://www.cnblogs.com/sparkdev/p/9651890.html)
### 6.进程
```markdown
程序：二进制文件 静态  /bin/date
进程;是程序运行的工程 动态 ，有生命周期及运行状态
PID,PPID
静态查看进程： ps
			ps aux
			ps aux|less  分页查看
[root@bjpeng01 file]# ps aux|less
USER        PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
```
![1551008620475](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551008620475.png)
```markdown
ps aux --sort %cpu |less  查看进程按CPU使用排序
ps aux --sort -%cpu |less
ps -ef
ps axo user,pid,ppid      自定义显示字段
查看指定进程的pid
cat /run/sshd.pid
ps aux |grep sshd
pgrep -l sshd
pgrep sshd
pidof sshd
查看进程树：pstree
```
```markdown
动态查看进程：top
			top -d 1 一秒刷新一次查看进程
top - 19:59:46 up  9:15,  2 users,  load average: 0.00, 0.01, 0.05
Tasks: 105 total,   1 running, 102 sleeping,   2 stopped,   0 zombie
%Cpu(s):  0.0 us,  0.0 sy,  0.0 ni,100.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
KiB Mem :  1863224 total,  1411464 free,   256640 used,   195120 buff/cache
KiB Swap:  2097148 total,  2097148 free,        0 used.  1417384 avail Mem 
 top -d 1 -p 10126 查看指定进程的动态信息
 top -d 1 -p 10126,1 查看多个指定进程的动态信息
 top -d 1 -u apache  查看指定用户的进程
 top -d 1 -b -n 2 > top.txt 将2次top信息写入到文件
```
```
信号控制进程：
	kill -l
```
![1551010331473](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551010331473.png)
```
 kill -1 9160  给进程发送重新加载信号
 pgrep
 pkill
```
```
进程优先级： nice
	查看进程优先级： top NI PR
				   ps aox pid,command,nice --sort=-nice |less
作业控制： jobs
	sleep 7000 & 后台进行
	sleep 8000   前台进行
	ps aux |grep sleep
	jobs
```
### 7.重定向和管道
[LINUX-IO重定向与管道](https://www.cnblogs.com/aioria13/p/7191002.html)
```markdown
Shell输入输出重定向
	1.标准输出重定向     只能重定向到文件中
		覆盖输出 >
		追加输出 >>
		ls -m > ls.txt     输出到文件（覆盖文件）
		ls -m >> ls.txt    输出到文件（追加文件）
		重定向多行到文件
			cat > c.txt <<-EOF      定义EOF结束符，-识别tab键
			111
			222
			333
			444
			EOF
	2. 错误输出重定向		错误输出时输出不存在
		覆盖输出 2>
		追加输出 2>>
	3. 合并标准输出与错误输出重定向
		覆盖输出 &>
		追加输出 &>>
	4.输入重定向
		输入重定向相比较而言，就比较简单了，而且用得相对较少
		一般用法：将文件作为输入重定向到命令
		命令 < 文件
		示例：将文件输入重定向至命令wc统计文件行数：	wc -l < a.txt
	5.输入重定向分界符：<<
		允许用户一直输入，直到输入的内容匹配<<指定的字符串
		# EOF可以用其它字符代替，习惯用EOF，end of file
		命令 << EOF
		示例：cat << EOF
			> 1.a
			> 2.b
			> 3.c
			> EOF
			1.a
			2.b
			3.c
```
[LINUX-IO重定向与管道](https://www.cnblogs.com/aioria13/p/7191002.html)
```markdown
	管道：管道用于连接多个命令（程序），将前一个命令的结果重定向，作为后一个命令的输入    
		 重定向到另外一个命令
		 ps aux | less  分页查看
		 ps aux | grep sshd 
	示例：将文件内容通过管道重定向到命令（其效果类似输入重定向） cat a.txt | wc -l
	以下几个命令经常与IO重定向（> >> 2> 2>> &> &>> ）及管道（|）结合使用
	tee
		read from standard input and write to standard output and files
		tee命令比较特殊：从标准输入读取、写入标准输出、写入文件（同时干了3件事，一箭三雕），IO重定向与tee更配哦。
		示例：tee命令（执行结果既输出到了指定文件，又输出到了terminal）
		ls -m | tee tee.txt
		date > date.txt
		date |tee date.txt
	tr - translate or delete characters		tr命令用于替换及删除字符
	cat ls.error | tr b B
	mkfifo c   创建管道文件
	prw-rw-r--. 1 pbj  pbj    0 Feb 25 12:53 c
```

![1551074354345](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551074354345.png)

![1551074520361](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551074520361.png)

![1551074730941](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551074730941.png)

![1551074932807](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551074932807.png)
### 8.储存，分区，文件
```markdown
基本分区，逻辑卷LVM ，EXT3/4/XFS 文件系统，磁盘阵列 RAID
MBR：    fdisk
GPT：	gdisk
基本分区： 无法扩容
逻辑卷： 可以扩容
基本分区（MBR|GPT） ---》 Filesystem ---》 mount
```
### 9.逻辑卷LVM，VG扩容
```markdown
创建LVM
VG夸张/缩小
LV扩容
文件系统扩容
逻辑卷 ---》 Filesystem ---> mount
交换分区swap：防止内存不足
lsof 恢复文件
```
### 10.文件查找
```markdown
grep:文件内容过滤
locate  ：
find    ：文件查找，针对文件名（遍历所有的文件）
find /etc -size +5M  查找大于5M的文件
find /etc -mtime +5  修改时间超过5天的文件
find / -maxdepth 4 -a -name "ifcfg-en" 查找文件根据层级
find . -perm 644 -ls  根据文件权限查找（. 当前目录）
文件打包和压缩  (最好针对文件夹）
	tar -czf etc1.tar.gz  /etc       //-z 调用gizp
	tar -cjf etc1.tar.gz  /etc		//-j  调用bzip2
	tar -cJf etc1.tar.gz  /etc		//-J 调用xz
解压
	tar -tf etc1.tar.gz
	tar -zxvf etc1.tar.gz	//指定解压工具
	tar -xvf etc1.tar.gz      //无指定解压工具，tar会自动判断
	tar -xvf etc1.tar.gz -C /tmp  //-C重定向到/tmp目录
	tar -xf etc1.tar.gz  //v 显示解压过程
	unzip xxx.zip
```
### 11.yum 软件包管理，安装
```markdown
yum -y install locate     安装软件包
yum provides locate		  查看软件包出处
yum  -y install mlocate   安装软件包
yum repolist  查看yum源
```
![1551085366639](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551085366639.png)
![1551085330756](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551085330756.png)
```
yum list wget 
```
### 12.RPM管理RPM包
```
```
### 13.周期性计划任务
[Linux下的计划任务at，batch，crontab](https://www.cnblogs.com/sijidou/p/10485983.html)
```markdown
作用：计划任务主要是做一些周期性的任务，目前主要是的用途是定期备份数据
一次性调度执行：at
	yum -y install at
	man at
	systemctl start atd
	systemctl enable atd
```
![1551150968712](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551150968712.png)
```
循环调度执行： cron     每分钟查看任务执行
	systemctl status crond
	ps aux |grep crond 
	ls /var/spool/cron/       用户级
	vi /etc/crontab          系统级1
	ls /etc/cron.d/*		系统级2
```
![1551151747250](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551151747250.png)
![1551152085010](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551152085010.png)
![1551152915426](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551152915426.png)
![1551152875982](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551152875982.png)
### 14.日志管理
```markdown
rsyslog:日志管理
	ps aux |grep rsyslogd
logrotate:日志轮转
```
[Linux日志管理]: https://www.cnblogs.com/lyq-biu/p/9638572.html
![1551164618312](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551164618312.png)
### 15.CentOS7 网络配置
```markdown
网卡：ens33
修改网络配置
1.NetworkManager网络管理
网络配置文件/etc/sysconfig/network-scripts/ifcfg-ens33
	TYPE=Ethernet
	PROXY_METHOD=none
	BROWSER_ONLY=no
	BOOTPROTO=dhcp
	DEFROUTE=yes
	IPV4_FAILURE_FATAL=no
	IPV6INIT=yes
	IPV6_AUTOCONF=yes
	IPV6_DEFROUTE=yes
	IPV6_FAILURE_FATAL=no
	IPV6_ADDR_GEN_MODE=stable-privacy
	NAME=ens33
	UUID=c5d1fbd9-7d5e-466a-8346-83f8f2c6b3c7
	DEVICE=ens33
	ONBOOT=yes
	IPV6_PRIVACY=no
nmcli connection reload
nmcli connection down ens33
nmcli cpnnection up ens33
2.
vi /etc/sysconfig/network-scripts/ifcfg-ens33
systemctl restart network
```
![1551167916474](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551167916474.png)
```
修改主机名； vi /etc/hostname
			reboot (重启生效)
配置名字解析： vi  /etc/hosts (主机名和IP解析)
DNS   cat /etc/resolv.conf
基本网络查看，测试工具
	ip a
	ip a show ens33
	ip route
	io neigh
	tracepath www.baidu.com
显示端口号： ss -tnlp
			ss -atn
```
### 16.FTP 服务器
```

```
```
lftp
wget
```

```
NAS存储：
		NFS：
		CIFS：
```

[CIFS与NFS]: https://www.cnblogs.com/wangzhigang/p/4344102.html
### 17.域名服务DNS
```
hosts文件：实现名字解析
域名服务DNS：实现名字解析（将主机名解析为IP）
```
### 18，19.Linux应用部署配置
```
HTTP Server
LAMP
Apache
```

