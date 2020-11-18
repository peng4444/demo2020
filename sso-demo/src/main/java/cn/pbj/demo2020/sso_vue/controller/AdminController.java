package cn.pbj.demo2020.sso_vue.controller;


import cn.pbj.demo2020.sso_vue.config.WebSecurityConfig;
import cn.pbj.demo2020.sso_vue.domain.CustomData;
import cn.pbj.demo2020.sso_vue.domain.LoginResultDetails;
import cn.pbj.demo2020.sso_vue.domain.ResultDetails;
import cn.pbj.demo2020.sso_vue.service.LoginService;
import cn.pbj.demo2020.sso_vue.service.SystemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

/**
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final SystemDataService systemDataService;

    private final LoginService loginService;

    @Autowired
    public AdminController(SystemDataService systemDataService, LoginService loginService) {
        this.systemDataService = systemDataService;
        this.loginService = loginService;
    }

    @GetMapping("/loginJudge")
    public LoginResultDetails showPage() {
        return loginService.get();
    }

    @PostMapping("/data")
    public CustomData create(@RequestBody CustomData customData) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String data = customData.getData();
        data = HtmlUtils.htmlEscape(data);
        customData.setData("*这是管理员建的数据：" + data);
        return systemDataService.create(customData);
    }

    @DeleteMapping("/data/{id}")
    public ResultDetails delete(@PathVariable("id") String id) {
        return systemDataService.delete(id, WebSecurityConfig.ADMIN);
    }
}
