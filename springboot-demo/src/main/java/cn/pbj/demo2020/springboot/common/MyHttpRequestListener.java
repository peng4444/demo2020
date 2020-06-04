package cn.pbj.demo2020.springboot.common;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: MyHttpRequestListener
 * @Author: pbj
 * @Date: 2020/6/4 20:14
 * @Description: TODO 监听请求的监听器
 */
public class MyHttpRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request 监听器被销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String requestURI = req.getRequestURI();
        System.out.println(requestURI+"--"+"被调用");
    }
}
