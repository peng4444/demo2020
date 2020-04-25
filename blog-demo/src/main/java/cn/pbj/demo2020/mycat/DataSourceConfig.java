package cn.pbj.demo2020.mycat;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName: DataSourceConfig
 * @Author: pbj
 * @Date: 2020/3/31 17:55
 * @Description: TODO
 */
@Configuration
public class DataSourceConfig {
    /**
     * 创建可读数据源
     *
     * @return
     */
    @Bean(name = "selectDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.select")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建可写数据源
     *
     * @return
     */
    @Bean(name = "updateDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.update")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
}
