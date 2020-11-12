package cn.pbj.demo2020.myblog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @pClassName: MybatisPlusConfig
 * @author: pengbingjiang
 * @create: 2020/11/12 14:01
 * @description: TODO
 */
@Configuration
@EnableTransactionManagement
@MapperScan("cn.pbj.demo2020.myblog.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}

