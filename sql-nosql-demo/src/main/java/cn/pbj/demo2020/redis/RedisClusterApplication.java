package cn.pbj.demo2020.redis;

import org.springframework.boot.SpringApplication;

/**
 * @ClassName: SpringBootApplication
 * @Author: pbj
 * @Date: 2020/5/25 12:09
 * @Description: TODO
 * [springboot整合redis集群详解](https://blog.csdn.net/hp_yangpeng/article/details/89002788)
 * [SpringBoot整合Redis使用Restful风格实现CRUD功能](https://www.cnblogs.com/xuwujing/p/10835571.html)
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class RedisClusterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisClusterApplication.class, args);
        System.out.println("==Springboot代码启动成功==");
    }
}
