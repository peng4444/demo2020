package cn.pbj.demo2020.ssm.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @pClassName: SysUserEntity
 * @author: pengbingjiang
 * @create: 2020/12/13 22:03
 * @description: TODO
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    //用户ID
    private Long userId;

    //用户名
    private String username;

    //密码
    private String password;

    //盐
    private String salt;

    //邮箱
    private String email;

    //手机号
    private String mobile;

    //状态  0：禁用   1：正常
    private Integer status;

    //创建时间
    private Date createTime;
}

