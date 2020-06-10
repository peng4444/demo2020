package cn.pbj.demo2020.es.controller;

import cn.pbj.demo2020.es.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: StudentController
 * @Author: pbj
 * @Date: 2020/6/10 09:03
 * @Description: TODO
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping("/test")
    public String test() {
        return "hello World";
    }
}
