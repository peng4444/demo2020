package cn.pbj.demo2020.mycat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DynamicDataSource
 * @Author: pbj
 * @Date: 2020/3/31 17:57
 * @Description: TODO
 */
@Component
@Primary
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Autowired
    @Qualifier("selectDataSource")
    private DataSource selectDataSource;

    @Autowired
    @Qualifier("updateDataSource")
    private DataSource updateDataSource;

    /**
     * 返回生效的数据源名称
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDbType();
    }

    /**
     * 配置数据源信息
     */
    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> map = new HashMap<Object, Object>(16);
        map.put("selectDataSource", selectDataSource);
        map.put("updateDataSource", updateDataSource);
        setTargetDataSources(map);
        setDefaultTargetDataSource(updateDataSource);
        super.afterPropertiesSet();
    }
}
