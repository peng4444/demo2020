package cn.pbj.demo2020.springdata.test;

import cn.pbj.demo2020.springdata.model.User;
import cn.pbj.demo2020.springdata.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @pClassName: TestDemo
 * @author: pengbingjiang
 * @create: 2020/6/26 10:48
 * @description: TODO
 */
public class TestDemo {

    @Test
    public void test1() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = ctx.getBean(UserService.class);
        User user = userService.getUserById(1L);
        System.out.println(user);
    }
}
