package cn.pbj.demo2020.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @pClassName: SSMApplication
 * @author: pengbingjiang
 * @create: 2020/12/13 21:52
 * @description: TODO
 */
@SpringBootApplication
@MapperScan("cn.pbj.demo2020.ssm.dao")
public class SSMApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSMApplication.class, args);
    }
}
