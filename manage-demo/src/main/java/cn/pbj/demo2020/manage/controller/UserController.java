package cn.pbj.demo2020.manage.controller;

import cn.pbj.demo2020.manage.common.DataResult;
import cn.pbj.demo2020.manage.entity.User;
import cn.pbj.demo2020.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @pClassName: UserController
 * @author: pengbingjiang
 * @create: 2020/7/23 18:50
 * @description: TODO
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/selectAll")
    public DataResult<User> selectALL() {
        return userService.selectAll();
    }

    @RequestMapping("/selectAllByPage")
    @ResponseBody
    public DataResult<User> selectALLByPage(Integer page, Integer limit) {
        return userService.selectAllByPage(page, limit);
    }

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }
}
