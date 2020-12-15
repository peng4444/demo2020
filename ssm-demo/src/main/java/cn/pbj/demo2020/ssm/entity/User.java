package cn.pbj.demo2020.ssm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @pClassName: User
 * @author: pengbingjiang
 * @create: 2020/12/15 09:25
 * @description: TODO
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6056125703075132981L;

    private Integer id;

    private String account;

    private String password;

    private String username;
}

