package cn.pbj.demo2020.myblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @pClassName: JwtToken
 * @author: pengbingjiang
 * @create: 2020/11/12 15:31
 * @description: TODO 自定义一个JwtToken，来完成shiro的supports方法
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
