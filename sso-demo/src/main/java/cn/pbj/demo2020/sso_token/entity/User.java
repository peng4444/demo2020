package cn.pbj.demo2020.sso_token.entity;

import lombok.Data;

/**
 * @ClassName: User
 * @Author: pbj
 * @Date: 2019/12/1 19:28
 * @Description: TODO  登录用户实体
 */
@Data
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
