package cn.pbj.demo2020.springmvc;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @pClassName: SampleController
 * @author: pengbingjiang
 * @create: 2020/7/15 14:52
 * @description: TODO
 */
@RestController
public class SampleController {

    @GetMapping(value = "/model")
    public String model(Model model, ModelMap modelMap) {
//        log.info("{}", model == modelMap);
        return "success";
    }

    @PostMapping(value = "/cookie")
    public String cookie(@CookieValue(name = "JSESSIONID") String sessionId) {
        return sessionId;
    }

    //请求头的值主要通过@RequestHeader注解的参数获取
    @PostMapping(value = "/header")
    public String header(@RequestHeader(name = "Content-Type") String contentType) {
        return contentType;
    }

    @PostMapping(value = "/file3")
    public String file3(@RequestParam(name = "file1") MultipartFile multipartFile) {
        String content = String.format("name = %s,originName = %s,size = %d",
                multipartFile.getName(), multipartFile.getOriginalFilename(), multipartFile.getSize());
//        log.info(content);
        return content;
    }

    @PostMapping(value = "/file2")
    public String file2(MultipartFile file1) {
        String content = String.format("name = %s,originName = %s,size = %d",
                file1.getName(), file1.getOriginalFilename(), file1.getSize());
//        log.info(content);
        return content;
    }

    @PostMapping(value = "/file1")//文件上传
    public String file1(@RequestPart(name = "file1") MultipartFile multipartFile) {
        String content = String.format("name = %s,originName = %s,size = %d",
                multipartFile.getName(), multipartFile.getOriginalFilename(), multipartFile.getSize());
//        log.info(content);
        return content;
    }

    @GetMapping(value = "/user/{name}/{age}")
    public String findUser1(@PathVariable(value = "age") Integer age,
                            @PathVariable(value = "name") String name) {
        String content = String.format("name = %s,age = %d", name, age);
//        log.info(content);
        return content;
    }

    @PostMapping(value = "/user-2")
    public User saveUser2(@RequestBody User user) {
//        log.info(user.toString());
        return user;
    }

    @PostMapping(value = "/user")
    public User saveUser(User user) {
//        log.info(user.toString());
        return user;
    }

    @PostMapping(value = "/post")
    public String post(@RequestParam(name = "name") String name,
                       @RequestParam(name = "age") Integer age) {
        String content = String.format("name = %s,age = %d", name, age);
//        log.info(content);
        return content;
    }

    @GetMapping(path = "/get1")
    public void get1(@RequestParam(name = "name") String name,
                     @RequestParam(name = "age") Integer age) {
//        log.info("name:{},age:{}", name, age);
    }

    @GetMapping(path = "/get2")
    public void get2(UserVo vo) {
//        log.info("name:{},age:{}", vo.getName(), vo.getAge());
    }

    @GetMapping(path = "/get3")
    public void get3(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
//        log.info("name:{},age:{}", name, age);
    }

    public static class UserVo {

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
