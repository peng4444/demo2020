package cn.pbj.demo2020.book.springinaction4.chapter2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @pClassName: CDPlayerConfig
 * @author: pengbingjiang
 * @create: 2020/6/25 23:03
 * @description: TODO
 */
@Configuration
@ComponentScan//显式配置一下 Spring开启组件扫描 等同于XML文件 <context:component-scan base-package="cn.pbj.demo2020.book.springinaction4.chpater2" />
public class CDPlayerConfig {

}
