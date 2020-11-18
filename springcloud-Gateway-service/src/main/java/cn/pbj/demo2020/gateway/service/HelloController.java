package cn.pbj.demo2020.gateway.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @pClassName: HelloController
 * @author: pengbingjiang
 * @create: 2020/11/18 14:04
 * @description: TODO
 */
@RestController
public class HelloController {

    @GetMapping("/say")
    public String sayHello(){
        return "[Spring-cloud-gateway-service]:say Hello";
    }
}
