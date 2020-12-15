package cn.pbj.demo2020.ssm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @pClassName: Role
 * @author: pengbingjiang
 * @create: 2020/12/15 09:26
 * @description: TODO
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = -1767327914553823741L;

    private Integer id;

    private String role;

    private String desc;
}
