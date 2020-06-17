# Java

【Java高并发】[随笔分类 - java高并发系列](https://www.cnblogs.com/itsoku123/category/1503555.html)

【随笔分类 - Java编程思想】[ Java编程思想](https://www.cnblogs.com/Tan-sir/category/1251239.html)

【JDK源码系列】[JDK源码](https://www.cnblogs.com/Scramblecode/)

## Java趋势发展
[20 多年历史的 Java 正在焕发第二春](https://mp.weixin.qq.com/s?__biz=MjM5MDE0Mjc4MA==&mid=2651026031&idx=2&sn=920c70b08b8e3eb9fe619e0d72c020e0&chksm=bdbe8a3c8ac9032abac46612913433e53c4abd9c83cf2bd1202858e6fb1806912858adfe8f90&mpshare=1&scene=23&srcid=&sharer_sharetime=1588561166752&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)

## Java书籍
[《On Java 8》中文版是事实上的《Java 编程思想》第5版。](https://lingcoder.gitee.io/onjava8/#/)

[Java工程师该如何编写高效代码？](https://mp.weixin.qq.com/s?__biz=MzIzOTU0NTQ0MA==&mid=2247492922&idx=1&sn=129283d4c520da6204f696ce86c15b2c&chksm=e92ad835de5d5123280d0c527301728da7d05986e8d7ac814e388928ebd47994906267f42c01&mpshare=1&scene=23&srcid=&sharer_sharetime=1576629844354&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
```
举例说明如何编写高效代码
```
[Java - Java开发中的安全编码问题](https://www.cnblogs.com/shoufeng/p/12609498.html)

[硬肝4.4w字为你写成Java开发手册](https://www.cnblogs.com/cxuanBlog/p/12854535.html)
## 一些有趣的博客
### 1.[程序员羽化之路--假如需要一百万个对象](https://www.cnblogs.com/zhanlang/p/12550179.html)
```java
//做的是把对象重复使用，只要是对象重复问题，基本上可以利用一个对象出口来解决问题，类似于以下的对象初始化工厂，但是要注意线程安全问题，因为同时请求并初始化对象的线程会有多个。
public class UserStarFac{
        static object objLock = new object();
        static Dictionary<int, Star> UserStarMap = new Dictionary<int, Star>();
        public static Star GetUserStar(int level){
            //利用锁来防止实例化多次，当然这里可以优化
            lock (objLock){
                Star info = null; ;
                if(!UserStarMap.TryGetValue(level,info)) {
//                    info = new Star() { Color = 1; Level = 1; StarNumber = 1 ;};
                    UserStarMap.Add(level,info);
                }
                return info;
            }
        }
public static void Main(string[] args){
    int i = 0;
    List<User> userList = new List<User>();
    while (i < 100000) {
        // userList.Add(new User() {  StarInfo=new Star() {  Color=1, Level=1, StarNumber=1} });
        userList.Add(new User() {  StarInfo= UserStarFac.GetUserStar(1)});
        i++; 
    }
    Console.WriteLine("初始化完成");
    Console.Read();
    }
}
//享元模式，没有必要记住名字，但需要记住原理和场景，必须要提一句：注意不变的对象才可以哦
```

### 2.[任意1-10中的4个数字，使用加减乘除计算得出24结果的可能组合（java版），很多人小时候都玩过](https://www.cnblogs.com/lechengbo/p/10815016.html)

### 3.[高效code review指南](https://www.cnblogs.com/xybaby/p/12601471.html)

### 4.[面试官在“逗”你系列：数组去重你会几种呀？](https://www.cnblogs.com/justbecoder/p/12892675.html)



[求求你了，不要再自己实现这些逻辑了，开源工具类不香吗？](https://www.cnblogs.com/goodAndyxublog/p/12874388.html)

[IO 模型知多少 | 理论篇](https://www.cnblogs.com/sheng-jie/p/how-much-you-know-about-io-models.html)

[IO 模型知多少 | 代码篇](https://www.cnblogs.com/sheng-jie/p/how-much-you-know-about-the-io-models-demo.html)

### [这些Java8官方挖过的坑，你踩过几个？](https://www.cnblogs.com/madashu/p/13023193.html)
```markdown
一、Base64：你是我解不开的迷:
    JDK官方的Base64和sun的base64是不兼容的！不要替换！不要替换！不要替换！
二、被吞噬的异常：我不敢说出你的名字
    java.lang.ArrayStoreException: sun.reflect.annotation.TypeNotPresentExceptionProxy,请记得用这个方法定位具体问题。
三、日期计算：我想留住时间，让1天像1年那么长
    Duraction和Period，都表示一段时间的间隔，Duraction正常用来表示时、分、秒甚至纳秒之间的时间间隔，Period正常用于年、月、日之间的时间间隔。
四、List：一如你我初见，不增不减  
    Arrays.asList方法，该方法的输入只能是一个泛型变长参数。
五、Stream处理：给你，独一无二
    步骤：获取一个数据源（source）→ 数据转换→执行操作获取想要的结果，
```

### Java优化if-else语句
[if-else代码优化的八种方案](https://www.cnblogs.com/jay-huaxiao/p/12586598.html)
[不吹牛X，我真的干掉了if-else](https://www.cnblogs.com/Lyn4ever/p/12913670.html)
#### [面试官灵魂拷问：if语句执行完else语句真的不会再执行吗？](https://www.cnblogs.com/binghe001/p/12944844.html)
```java
// 输出 : 我是if语句的分支
//        我是else语句的分支
public class Test {
    public Test() {
    }

    public static void main(String[] args) {
        (new Test()).print(args == null || (new Test() {
            {
                Test.main((String[])null);
            }
        }).equals((Object)null));
    }

    public void print(boolean flag) {
        if (flag) {
            System.out.println("我是if语句的分支");
        } else {
            System.out.println("我是else语句的分支");
        }

    }
}
```
[别再写一摞if-else了！再写开除！两种设计模式带你消灭它！](https://www.cnblogs.com/liuyanling/p/13121484.html)
```markdown
结合模板方法模式以及工厂模式及发射消除代码里面的if-else;
```

## Java爬虫
[不会python?那就换一种姿势爬虫！Java爬虫技术总结](https://www.cnblogs.com/carloschan/p/10572788.html)

##
[50 个 Java 性能优化细节，你要不要看？](https://mp.weixin.qq.com/s?__biz=MzUxOTc4NjEyMw==&mid=2247485058&idx=1&sn=1075ec571f2391511fba05ef3cae4221&chksm=f9f51d66ce82947062a166abbecd5d16dcbffc1e4545ee0315e59825afe8f96cb757d2952dd1&mpshare=1&scene=23&srcid=&sharer_sharetime=1572097657629&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)

[Java规则引擎Easy Rules](https://www.cnblogs.com/cjsblog/p/13088017.html)

[Java 异常处理的十个建议](https://www.cnblogs.com/jay-huaxiao/p/13125194.html)

[原来热加载如此简单，手动写一个 Java 热加载吧](https://www.cnblogs.com/niumoo/p/11756703.html)

[大型车祸现场，电商秒杀超卖，这个锅到底有谁来背？](https://www.cnblogs.com/smallSevens/p/11691432.html)

