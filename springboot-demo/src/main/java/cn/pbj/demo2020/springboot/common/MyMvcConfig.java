package cn.pbj.demo2020.springboot.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: MyMvcConfig
 * @Author: pbj
 * @Date: 2020/6/4 19:39
 * @Description: TODO
 * 将自定义好的拦截器处理类进行注册，并通过addPathPatterns、excludePathPatterns等属性设置需要拦截或需要排除的URL。
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
    }
}
