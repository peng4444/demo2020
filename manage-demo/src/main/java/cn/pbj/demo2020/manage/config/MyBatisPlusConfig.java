package cn.pbj.demo2020.manage.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @pClassName: MyBatisPlusConfig
 * @author: pengbingjiang
 * @create: 2020/7/24 10:00
 * @description: TODO MyBatisPlus 分页注解
 */
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
