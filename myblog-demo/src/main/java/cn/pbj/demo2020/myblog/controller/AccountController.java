package cn.pbj.demo2020.myblog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.pbj.demo2020.myblog.common.DataResult;
import cn.pbj.demo2020.myblog.common.dto.LoginDto;
import cn.pbj.demo2020.myblog.entity.User;
import cn.pbj.demo2020.myblog.service.UserService;
import cn.pbj.demo2020.myblog.util.ImageCode.CreateImageCode;
import cn.pbj.demo2020.myblog.util.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @pClassName: AccountController
 * @author: pengbingjiang
 * @create: 2020/11/12 16:31
 * @description: TODO
 */
@RestController
public class AccountController {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public DataResult login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return DataResult.fail("密码错误！");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return DataResult.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public DataResult logout() {
        SecurityUtils.getSubject().logout();
        return DataResult.succ(null);
    }

    /**
     * 用户注册
     *
     * @param code
     * @param user
     * @return
     */
    @PostMapping("/register")
    public DataResult register(String code, String key, @RequestBody User user, HttpServletRequest request) {
        DataResult result = new DataResult();
        //log.info("接收的验证码: " + code);
        //log.info("接收的验证码的key: " + key);
        //log.info("接收到user对象: " + user);
        //验证验证码
        String keyCode = (String) request.getServletContext().getAttribute(key);
        //log.info(keyCode);
        try {
            //不考虑大小写比较生成的验证码和输入的验证码是否相同
            if (code.equalsIgnoreCase(keyCode)) {
                //注册用户
                userService.save(user);
                result.setMsg("注册成功!!!");
            } else {
                throw new RuntimeException("验证码错误!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 生成验证码
     * @throws IOException
     */
    @GetMapping("/getImage")
    public Map<String, String> getImage(HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        //获取验证码
        String securityCode = createImageCode.getCode();
        //验证码存入session
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key, securityCode);
        //生成图片
        BufferedImage image = createImageCode.getBuffImg();
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key", key);
        result.put("image", string);
        return result;
    }

}
