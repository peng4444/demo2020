# OKHTTP使用
[一次使用OKHTTP的心痛历程](https://www.cnblogs.com/yanzhenyidai/p/13042130.html)
```markdown
使用场景
本次场景是将上传到测试环境的文件信息，下载到本地，然后再上传到生产环境。
解决过程如下：
    将错误数据从数据库表中粘贴到本地新建的一个Excel文件中。（毕竟直接连接数据库风险更大）
    读取Excel内的信息，获取文件地址。
    请求文件地址，获取到流文件信息。
    拿到流文件信息，拼接上传数据，上传到新的生产环境中。
    上传完成后，获取到生产环境文件地址。
    获取到生产文件地址的同时，生成更新的SQL语句。
    到数据库中执行SQL语句。
```
[极速入门 Retrofit + OkHttp 网络框架到实战，这一篇就够了！](https://www.cnblogs.com/yuanhao-1999/p/12095983.html)
```markdown
Retrofit是一个针对Java和Android的设计的REST客户机。它通过基于REST的web服务检索和上传JSON(或其他结构化数据)变得相对容易。
在使用中，您可以配置用于数据序列化的转换器。对于JSON，通常使用Gson，但是可以添加自定义转换器来处理 XML 或其他协议。Retrofit对HTTP请求使用OkHttp库。
```
