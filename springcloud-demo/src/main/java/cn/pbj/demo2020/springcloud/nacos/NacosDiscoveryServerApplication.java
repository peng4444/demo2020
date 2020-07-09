package cn.pbj.demo2020.springcloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @pClassName: NacosDiscoveryServerApplication
 * @author: pengbingjiang
 * @create: 2020/7/9 16:37
 * @description: TODO
 */
@SpringBootApplication
public class NacosDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryServerApplication.class, args);
    }
    @RestController
    static class TestController {
        @GetMapping("/hello")
        public String hello(@RequestParam String name) {
            return "hello,nacos discovery! " + name;
        }
    }
}
