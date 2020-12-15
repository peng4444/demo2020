package cn.pbj.demo2020.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/**
 * @pClassName: SSMApplication
 * @author: pengbingjiang
 * @create: 2020/12/13 21:52
 * @description: TODO
 */
@SpringBootApplication
// 开启定时任务注解 @EnableScheduling
@MapperScan("cn.pbj.demo2020.ssm.dao")
public class SSMApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSMApplication.class, args);
        System.out.println("恭喜！！！项目启动成功！！！"+new Date());
    }
}
