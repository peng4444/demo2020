package cn.pbj.demo2020.myblog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @pClassName: AccountProfile
 * @author: pengbingjiang
 * @create: 2020/11/12 15:30
 * @description: TODO 封装可以返回的信息
 */
@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

}
