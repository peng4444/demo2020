package cn.pbj.demo2020.ssm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @pClassName: FilterController
 * @author: pengbingjiang
 * @create: 2020/12/14 21:58
 * @description: TODO
 * [Spring Boot2(七)：拦截器和过滤器](https://www.cnblogs.com/niaobulashi/p/springboot-interceptor-filter.html)
 * [SpringBoot第六集：整合监听器/过滤器和拦截器](https://www.cnblogs.com/xsge/p/13915775.html)
 */
@RestController
public class FilterController {

    @GetMapping("/filter")
    public String testFilter(){
        return "filter is ok";
    }

    @RequestMapping("/interceptor")
    public String home(){
        return "interceptor is ok";
    }
}
