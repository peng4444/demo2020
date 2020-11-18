package cn.pbj.demo2020.sso_vue.exception;

import org.springframework.security.core.AuthenticationException;

//返回登陆请求过于频繁的异常

public class LoginCountToManyException extends AuthenticationException {

    public LoginCountToManyException(String msg, Throwable t) {
        super(msg, t);
    }

    public LoginCountToManyException(String msg) {
        super(msg);
    }
}
