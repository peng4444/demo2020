package cn.pbj.demo2020.ssm.controller;

import cn.pbj.demo2020.ssm.common.MD5Utils;
import cn.pbj.demo2020.ssm.common.ResponseResult;
import cn.pbj.demo2020.ssm.common.StatusEnums;
import cn.pbj.demo2020.ssm.entity.User;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static org.apache.shiro.util.ThreadContext.getSubject;

/**
 * @pClassName: LoginController
 * @author: pengbingjiang
 * @create: 2020/12/15 09:45
 * @description: TODO
 * [Spring Boot2(十二)：手摸手教你搭建Shiro安全框架](https://www.cnblogs.com/niaobulashi/p/springboot-shiro.html)
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * 登录操作，系统访问入口
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody User user, HttpSession session) {
        Subject userSubject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
        try {
            // 登录验证
            userSubject.login(token);
            // 把用户信息存在 Session 对象中
            // session.setAttribute("user",user);
            return ResponseResult.success();
        } catch (UnknownAccountException e) {
            return ResponseResult.error(StatusEnums.ACCOUNT_UNKNOWN);
        } catch (DisabledAccountException e) {
            return ResponseResult.error(StatusEnums.ACCOUNT_IS_DISABLED);
        } catch (IncorrectCredentialsException e) {
            return ResponseResult.error(StatusEnums.INCORRECT_CREDENTIALS);
        } catch (Throwable e) {
            e.printStackTrace();
            return ResponseResult.error(StatusEnums.SYSTEM_ERROR);
        }
    }

    /**
     * 登录操作
     * @param account
     * @param password
     * @param rememberMe
     * @return
     */
    @PostMapping("/logins")
    @ResponseBody
    public ResponseResult login(String account, String password, Boolean rememberMe,String validateCode) {
        logger.info("登录请求-start");

        //1、检验验证码
        if(validateCode == null || validateCode == ""){
            return ResponseResult.error(StatusEnums.PARAM_NULL);
        }
        Session session = SecurityUtils.getSubject().getSession();
        //转化成小写字母
        validateCode = validateCode.toLowerCase();
        String v = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
        //session.removeAttribute("_come");
        if(!validateCode.equals(v)){
            return ResponseResult.error(StatusEnums.VALIDATECODE_ERROR);
        }

        password = MD5Utils.encrypt(account, password);
        Subject userSubject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password, rememberMe);
        try {
            // 登录验证
            userSubject.login(token);
            return ResponseResult.success();
        } catch (UnknownAccountException e) {
            return ResponseResult.error(StatusEnums.ACCOUNT_UNKNOWN);
        } catch (DisabledAccountException e) {
            return ResponseResult.error(StatusEnums.ACCOUNT_IS_DISABLED);
        } catch (IncorrectCredentialsException e) {
            return ResponseResult.error(StatusEnums.INCORRECT_CREDENTIALS);
        } catch (AuthenticationException e) {
            return ResponseResult.error(StatusEnums.AUTH_ERROR);
        } catch (Throwable e) {
            e.printStackTrace();
            return ResponseResult.error(StatusEnums.SYSTEM_ERROR);
        }
    }



    @GetMapping("/login")
    public ResponseResult login() {
        return ResponseResult.error(StatusEnums.NOT_LOGIN_IN);
    }

    @GetMapping("/auth")
    public String auth() {
        return "已成功登录";
    }

    @GetMapping("/role")
    @RequiresRoles("vip")
    public String role() {
        return "测试Vip角色";
    }

    @GetMapping("/permission")
    @RequiresPermissions(value = {"add", "update"}, logical = Logical.AND)
    public String permission() {
        return "测试Add和Update权限";
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    public ResponseResult logout() {
        getSubject().logout();
        return ResponseResult.success();
    }
}

