package cn.pbj.demo2020.ssm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @pClassName: JpaConfig
 * @author: pengbingjiang
 * @create: 2020/12/14 10:36
 * @description: TODO
 */
@EnableJpaRepositories(basePackages = "cn.pbj.demo2020.ssm.dao")
@Configuration
public class JpaConfig {
}
