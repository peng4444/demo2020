package cn.pbj.demo2020.myblog.controller;

import cn.hutool.crypto.SecureUtil;
import cn.pbj.demo2020.myblog.common.DataResult;
import cn.pbj.demo2020.myblog.entity.User;
import cn.pbj.demo2020.myblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peng4444
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //注解：需要登录访问
    @RequiresAuthentication
    @GetMapping("/index")
    public DataResult index() {
        User user = userService.getById(1L);
        return DataResult.succ(user);
    }

    /**
     * 测试实体校验
     * @param user
     * @return
     */
    @PostMapping("/save")
    public DataResult testUser(@Validated @RequestBody User user) {
        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
        user.setCreated(now);
        user.setPassword(SecureUtil.md5(user.getPassword()));
        userService.saveOrUpdate(user);
        return DataResult.succ(user);
    }

}
