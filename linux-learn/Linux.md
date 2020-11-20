 # Linux
[linux服务器负载问题排查](https://www.cnblogs.com/chenfangzhi/p/9981614.html)


[Keepalived 原理与实战](https://www.cnblogs.com/christopherchan/p/12953230.html)
##
###
## Linux相关博客
### [1.分析Linux进程的6个方法，我全都告诉你](http://mp.weixin.qq.com/s?__biz=Mzg2NzA4MTkxNQ==&mid=2247487818&idx=3&sn=a7f1dbff16e0f2ff3aa0e5af69a62f1c&chksm=ce405a9ef937d3886a7a3eb7fce31e57ec533b18d80b12559d449ece5c49ec58563398293dd8&mpshare=1&scene=23&srcid=&sharer_sharetime=1590881539719&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
```markdown
ps:  --列出当前系统进程的快照。
   # ps -ef  -- 列出系统内经常信息 
   # ps -ef|grep intresting  -- 第一列PID代表进程号，
strace: --跟踪进程内部的系统调用和信号
    # strace ./time_test  -- strace后面跟着启动一个进程，可以跟踪启动后进程的系统调用和信号
pstack: -- 打印出运行中程序的堆栈信息
    # pstack pid -- 能看到当前线程运行中的堆栈信息
pstree: -- 按树形结构打印运行中进程结构信息
    # pstree -p 11811
gdb: -- 可以查看程序的堆栈、设置断点、打印程序运行时信息，甚至还能调试多线程程序
```
### Linux相关面试
#### 如何查找特定的文件
```markdown
语法：find path [options] params  --在指定目录下查找文件
find -name "text.md"  -- 在当前目录下递归查找text.md文件
find / -name "text.md" -- 从根目录开始递归查找text.md文件
find ~ -name "aa*"  --去父目录下递归查找aa开头的文件
find ~ -name "aa*"  --去父目录下递归查找aa开头的文件(不区分文件名大小写去查找文件)
man find: 更多关于find指令的使用说明
```
#### 检索文件内容
```markdown
语法：grep [options] pattern file  作用：查找文件里符合条件的字符串
grep "moo" target*  -- 去当前目录下的target开头文件中查找包含moo的字符
管道操作符 | 将指令连接起来，前一个指令的输出作为后一个指令的输入
find -xxx | grep "abc"
    使用管道注意的要点：
        - 只处理前一个命令正确输出，不处理错误输出
        - 右边命令必须能够接受标准输入流，否则传递过程中数据会被抛弃
        - sed,awk,grep,cut,head,top,less,more,wc,join,sort,split等等
```
#### 对文件内容做统计
```markdown
语法： awk [options] 'cmd' file 一次读取一行，按输入分隔符进行切片
    awk '{print $1,$4}' test.txt 取出test.txt文件中第一个和第四个切片
    awk '$1=="tcp" && $2==1{print $0}' test.txt 取出test.txt文件中第一个和第四个切片
```
#### 批量替换文件内容
```markdown
语法: sed [option] 'sed command' filename  流编辑器 适合对文本的行内容进行处理
    sed -i 's/^Str/String/' text.txt -- 将文件中的Str替换为String并且写入文件（一行文件只替换首次出现的）
    sed -i 's/\.$/\;/'  text.txt -- 将文件中末尾的.替换为;并且写入文件
    sed -i 's/^Str/String/g' text.txt -- 将文件中的Str替换为String并且写入文件（全文替换）
    sed -i '/Integer/d' text.txt --将文件中包含Integer的文件行删除
```
### [Linux 性能分析之内存篇](https://www.cnblogs.com/bakari/p/10486818.html)
```markdown
内存信息
    1.cat /proc/meminfo 查看文件记录着比较详细的内存配置信息
    2.free
    3.dmidecode -t memory
    4.vmstat l 用于进行系统全局分析和CPU分析
进程内存使用情况分析
    1.top/htop:按照 CPU 利用率进行排序的
    2.ps aux --sort=rss | head -n 查看 Top n 进程占用内存情况
    3.pmap -x pid  查看进程在哪些地方用了多少内存
    
```
###
[linux系统下扩展磁盘空间](https://blog.csdn.net/weixin_41229271/article/details/80476648)
[LVM : 扩展文件系统的容量](https://www.cnblogs.com/sparkdev/p/10142629.html)
[如何构建“高性能”“大小无限”（磁盘）队列？](https://www.cnblogs.com/yougewe/p/10988194.html)
##
[centos创建新用户](https://blog.csdn.net/nieji3057/article/details/79421874)