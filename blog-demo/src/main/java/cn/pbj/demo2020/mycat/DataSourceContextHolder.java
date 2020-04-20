package cn.pbj.demo2020.mycat;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DataSourceContextHolder
 * @Author: pbj
 * @Date: 2020/3/31 17:56
 * @Description: TODO
 */
@Component
@Lazy(false)
public class DataSourceContextHolder {
    /**
     * 采用ThreadLocal 保存本地多数据源
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 设置数据源类型
     *
     * @param dbType
     */
    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    /**
     * 获取数据源类型
     */
    public static String getDbType() {
        return contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
