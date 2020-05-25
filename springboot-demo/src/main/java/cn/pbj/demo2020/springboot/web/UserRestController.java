package cn.pbj.demo2020.springboot.web;

import cn.pbj.demo2020.springboot.common.ResultBody;
import cn.pbj.demo2020.springboot.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserRestController
 * @Author: pbj
 * @Date: 2020/5/25 12:17
 * @Description: TODO
 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
    @GetMapping("/user")
    public ResultBody findByUser(User user) {
        System.out.println("用户查询接口请求的参数:"+user);
        ResultBody resultBody = new ResultBody();
        List<User> userList =new ArrayList<>();
        User user2=new User();
        user2.setId(1L);
        user2.setName("xuwujing");
        user2.setAge(18);
        userList.add(user2);
        resultBody.setCode("0");
        resultBody.setResult(userList.toString());
        System.out.println("用户查询接口响应的参数:"+resultBody);
        return resultBody;
    }

    @GetMapping("/test")
    public ResultBody testDemo() {
        ResultBody resultBody = new ResultBody();
        resultBody.setCode("1");
        resultBody.setMessage("返回成功");
        resultBody.setResult("你要的结果。");
        return resultBody;
    }
}
