# Netty学习代码
[参考阅读书籍：《Netty 实战(精髓)》](https://waylau.gitbooks.io/essential-netty-in-action/content/)

[参考视频：尚硅谷韩顺平Netty视频教程（2019发布）](https://www.bilibili.com/video/BV1DJ411m7NR)
##
###[netty 实现简单的rpc调用](https://www.cnblogs.com/yloved/p/12940619.html)
```markdown
netty 实现简单rpc准备
1.使用netty传输java bean对象，可以使用protobuf，也可以通过json转化。
2.客户端要将调用的接口名称，方法名称，参数列表的类型和值传输到服务端，可以用动态代理。
3.服务端要对接口和实现类进行映射（或者自定义名称与实现类映射），接收到客户端的数据，使用反射调用相关类的函数。
4.客户端使用callable返回调用的结果，先等待，有数据写回后唤醒线程，赋值返回。
基于netty编码实现 rpc 调用
大致流程：
    1.netty搭建rpc框架；
    2.创建服务消费者和服务提供者的公共接口和类；
    3.创建服务提供者，启动netty框架的服务端；
    4.创建服务消费者，启动netty框架的客户端，然后获取调用结果；    
```
