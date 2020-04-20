package cn.pbj.demo2020.mycat;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DataSourceOptionAop
 * @Author: pbj
 * @Date: 2020/3/31 17:58
 * @Description: TODO
 */
@Aspect
@Component
@Lazy(false)
@Order(0) // Order设定AOP执行顺序 使之在数据库事务上先执行
public class DataSourceOptionAop {
    /**
     * 可读数据源
     */
    private final static String DATASOURCE_TYPE_SELECT = "selectDataSource";
    /**
     * 可写数据源
     */
    private final static String DATASOURCE_TYPE_UPDATE = "updateDataSource";

    /**
     * 创建切面，根据方法类型选择不同的数据源
     *
     * @param joinPoint
     */
    @Before("execution(* cn.pbj.demo2020.mycat.*.*(..))")
    public void process(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.print("=========== " + methodName);
        if (methodName.startsWith("get") || methodName.startsWith("count") || methodName.startsWith("find")
                || methodName.startsWith("list") || methodName.startsWith("select") || methodName.startsWith("check")
                || methodName.startsWith("query")) {
            DataSourceContextHolder.setDbType(DATASOURCE_TYPE_SELECT);
            System.out.println("-----------------使用selectDataSource数据源-------------------");
        } else {
            DataSourceContextHolder.setDbType(DATASOURCE_TYPE_UPDATE);
            System.out.println("-----------------使用updateDataSource数据源-------------------");
        }
    }
}
