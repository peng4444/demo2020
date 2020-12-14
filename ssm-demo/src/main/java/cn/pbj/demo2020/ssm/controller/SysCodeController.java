package cn.pbj.demo2020.ssm.controller;

import cn.pbj.demo2020.ssm.entity.SysCode;
import cn.pbj.demo2020.ssm.service.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @pClassName: SysCodeController
 * @author: pengbingjiang
 * @create: 2020/12/14 10:24
 * @description: TODO
 * [Spring Boot2(三)：使用Spring Boot2集成Redis缓存](https://www.cnblogs.com/niaobulashi/p/spring-boot-redis.html)
 */
@RestController
public class SysCodeController {

    @Autowired
    private SysCodeService sysCodeService;

    /**
     * 查询所有数字字典
     * @return
     */
    @RequestMapping("/getCodeAll")
    private List<SysCode> getCodeAll() {
        Long startTime = System.currentTimeMillis(); //开始时间
        List<SysCode> codeList = sysCodeService.queryCodeAll();
        Long endTime = System.currentTimeMillis(); //结束时间
        System.out.println("共耗时：" + (endTime - startTime) + "毫秒"); //1007毫秒
        return codeList;
    }
}
