  # Linux
[linux服务器负载问题排查](https://www.cnblogs.com/chenfangzhi/p/9981614.html)


[Keepalived 原理与实战](https://www.cnblogs.com/christopherchan/p/12953230.html)
##
###
## Linux相关博客
### [分析Linux进程的6个方法，我全都告诉你](http://mp.weixin.qq.com/s?__biz=Mzg2NzA4MTkxNQ==&mid=2247487818&idx=3&sn=a7f1dbff16e0f2ff3aa0e5af69a62f1c&chksm=ce405a9ef937d3886a7a3eb7fce31e57ec533b18d80b12559d449ece5c49ec58563398293dd8&mpshare=1&scene=23&srcid=&sharer_sharetime=1590881539719&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
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
