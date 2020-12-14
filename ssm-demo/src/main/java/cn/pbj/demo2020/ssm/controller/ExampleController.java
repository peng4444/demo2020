package cn.pbj.demo2020.ssm.controller;

import cn.pbj.demo2020.ssm.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @pClassName: ExampleController
 * @author: pengbingjiang
 * @create: 2020/12/14 13:39
 * @description: TODO
 */
@Controller
public class ExampleController {

    @RequestMapping("/string")
    public String string(ModelMap map) {
        map.addAttribute("userName", "niaobulashi");
        return "string";
    }

    @RequestMapping("/if")
    public String ifunless(ModelMap map) {
        map.addAttribute("flag", "yes");
        return "if";
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }

    @RequestMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("type", "link");
        map.addAttribute("pageId", "springcloud/2017/09/11/");
        map.addAttribute("img", "http://ww1.sinaimg.cn/large/a3d658ably1g1atmg3tpoj20k00dq0ta.jpg");
        return "url";
    }

    @RequestMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "neo");
        map.addAttribute("age", 30);
        map.addAttribute("flag", "yes");
        return "eq";
    }

    @RequestMapping("/switch")
    public String switchcase(ModelMap map) {
        map.addAttribute("sex", "woman");
        return "switch";
    }

    @RequestMapping("/me")
    public String kownMe(Map<String,Object> map) {
        List<String> list = new ArrayList<String>();
        list.add("鸟不拉屎：一个正在努力Coding的未来架构师");
        list.add("记录菜鸟的成长");
        list.add("个人博客：https://niaobulashi.com");
        list.add("github博客：https://niaobulashi.github.io");
        map.put("msg", "Yoyoyoyoyo");
        map.put("images", "static/images/wechat-niaobulashi.jpg");
        map.put("lists", list);
        return "me";
    }


    public List<User> getUserList() {
        List<User> list = new ArrayList<User>();
        User user1 = new User("大牛", 12, "123456");
        User user2 = new User("小牛", 6, "123563");
        User user3 = new User("鸟不拉屎", 66, "666666");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }
}
