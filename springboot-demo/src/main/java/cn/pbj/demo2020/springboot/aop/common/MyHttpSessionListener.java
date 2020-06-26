package cn.pbj.demo2020.springboot.aop.common;



import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: MyHttpSessionListener
 * @Author: pbj
 * @Date: 2020/6/4 20:12
 * @Description: TODO 监听器 我们下面来统计当前在线人数
 */
public class MyHttpSessionListener implements HttpSessionListener {

    public static AtomicInteger userCount = new AtomicInteger(0);

    @Override
    public synchronized void sessionCreated(HttpSessionEvent se) {
        userCount.getAndIncrement();
        se.getSession().getServletContext().setAttribute("sessionCount", userCount.get());
//        log.info("【在线人数】人数增加为:{}",userCount.get());

        //此处可以在ServletContext域对象中为访问量计数，然后传入过滤器的销毁方法
        //在销毁方法中调用数据库入库，因为过滤器生命周期与容器一致
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent se) {
        userCount.getAndDecrement();
        se.getSession().getServletContext().setAttribute("sessionCount", userCount.get());
//        log.info("【在线人数】人数减少为:{}",userCount.get());
    }
}

