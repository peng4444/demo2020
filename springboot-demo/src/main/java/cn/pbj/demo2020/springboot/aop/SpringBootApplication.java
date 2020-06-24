package cn.pbj.demo2020.springboot.aop;

import org.springframework.boot.SpringApplication;

/**
 * @ClassName: SpringBootApplication
 * @Author: pbj
 * @Date: 2020/5/25 12:09
 * @Description: TODO [SpringBoot切面Aop的demo简单讲解](https://www.cnblogs.com/xuwujing/p/12927081.html)
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
        System.out.println("==Springboot代码启动成功==");
    }
}
