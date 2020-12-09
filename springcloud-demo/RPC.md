# RPC
## RPC
>> Remote Procedure Call（RPC）：远程过程调用
### [如何设计一个RPC系统](https://www.cnblogs.com/qcloud1001/p/10213274.html)
```markdown
RPC是一种方便的网络通信编程模型，由于和编程语言的高度结合，大大减少了处理网络数据的复杂度，让代码可读性也有可观的提高。
但是RPC本身的构成却比较复杂，由于受到编程语言、网络模型、使用习惯的约束，有大量的妥协和取舍之处。
```
### [Java实现远程服务生产与消费(RPC)的4种方法-RMI,WebService,HttpClient,RestTemplate](https://www.cnblogs.com/tanshaoshenghao/p/10796319.html)
```markdown
rmi:    
    1.创建UserService接口.
    2.创建UserServiceImpl实现类,除了实现UserService接口外, 还要继承UnicastRemoteObject类。
    3.发布远程服务
    4.创建rmi-consumer项目
    5.远程服务消费者对远程服务发起调用.
    通过最后的输出我们看到获得的远程服务对象是动态代理产生的。
webService:
    1.首先创建远程服务接口UserService及其实现类UserServiceImpl.
    2.发布远程服务, 过程和rmi差不多, 需要提供远程服务的访问地址和具体的远程服务实现类, 使用Endpoint类的publish()方法进行发布, 这都是JDK封装好的.
        查看的方法是在浏览器中输入远程服务的访问地址加上?wdsl, 比如本案例中是http://localhost:9999/ws?wsdl
        注意, 在客户端调用远程方法时需要用工具对wdsl文档进行解析, 并获得调用远程方法的工具类. 具体操作见下一段.
        和rmi不同的是, WebService发布后, 调用者可以通过查看它的文档对远程服务发起调用.
    3.首先根据文档获得调用远程服务的工具类, JDK已经为我们封装好了获取的工具, 它在bin目录下, 名字是wsimport
    4.打开命令行, 在命令行中输入解析命令
    5. 可以看到命令执行完后, 指定的包中出现一堆相关的类, 最直接调用到的类是UserServiceImplService.   
HttpClient:
    在服务消费端使用HttpClient发送请求, 可以理解为模拟浏览器发送post/get请求.
     HttpClient为我们封装了拼接一个请求的细节, 使得发送一个请求变得容易.
RestTemplate:
    通过spring提供的RestTemplate实现远程服务的生产与消费。
```
### [看了这篇你就会手写RPC框架了](https://www.cnblogs.com/itoak/p/13370031.html)
```markdown
Remote Procedure Call（RPC）：远程过程调用。
    过程就是业务处理、计算任务，更直白理解，就是程序。（像调用本地方法一样调用远程的过程。）
    RPC采用Client-Server结构，通过Request-Response消息模式实现。
RPC的流程
    - 客户端处理过程中调用Client stub（就像调用本地方法一样），传递参数；
    - Client stub将参数编组为消息，然后通过系统调用向服务端发送消息；
    - 客户端本地操作系统将消息从客户端机器发送到服务端机器；
    - 服务端操作系统将接收到的数据包传递给Server stub;
    - Server stub解组消息为参数；
    - Server stub再调用服务端的过程，过程执行结果以反方向的相同步骤响应给客户端。
RPC流程中需要处理的问题
    - Client stub、Server stub的开发；
    - 参数如何编组为消息，以及解组消息；
    - 消息如何发送；
    - 过程结果如何表示、异常情况如何处理；
    - 如何实现安全的访问控制。
RPC协议
    - RPC调用过程中需要将参数编组为消息进行发送，接受方需要解组消息为参数，过程处理结果同样需要经编组、解组。
        消息由哪些部分构成及消息的表示形式就构成了消息协议。
    - RPC调用过程中采用的消息协议称为RPC协议
     RPC协议规定请求、响应消息的格式
     在TCP（网络传输控制协议）上可选用或自定义消息协议来完成RPC消息交互
     我们可以选用通用的标准协议（如：http、https），也也可根据自身的需要定义自己的消息协议。
RPC框架
    封装好参数编组、消息解组、底层网络通信的RPC程序开发框架，带来的便捷是可以直接在其基础上只需要专注于过程代码编写。
    Java领域：
    传统的webservice框架：Apache CXF、Apache Axis2、Java自带的JAX-WS等。webservice框架大多基于标准的SOAP协议。
    新兴的微服务框架：Dubbo、spring cloud、Apache Thrift等。
```
###
[手写RPC框架注释代码](https://www.cnblogs.com/mseddl/p/11531465.html)

[一文带你实现RPC框架](https://www.cnblogs.com/endless-code/p/11235624.html)

[微服务调用为啥用RPC框架，http不更简单吗？](https://zhuanlan.zhihu.com/p/61364466) 