package cn.pbj.demo2020.ssm.controller;

import cn.pbj.demo2020.ssm.entity.Person;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @pClassName: ValidateController
 * @author: pengbingjiang
 * @create: 2020/12/15 11:16
 * @description: TODO
 */
@RestController
public class ValidateController {

    @PostMapping("/person")
    public Map<String, Object> validatePerson(@Validated @RequestBody Person person, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        if (result.hasErrors()) {
            List<String> res = new ArrayList<>();
            result.getFieldErrors().forEach(error -> {
                String field = error.getField();
                Object value = error.getRejectedValue();
                String msg = error.getDefaultMessage();
                res.add(String.format("错误字段 -> %s 错误值 -> %s 原因 -> %s", field, value, msg));
            });
            map.put("msg", res);
            return map;
        }
        map.put("msg", "success");
        System.out.println(person);
        return map;
    }

    @PostMapping("/persons")
    public Map<String, Object> validatePerson(@Valid @RequestBody Person person) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "success");
        System.out.println(person);
        return map;
    }
}

