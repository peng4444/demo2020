package cn.pbj.demo2020.redis.one;

import lombok.Data;

import java.io.Serializable;

/**
 * @pClassName: User
 * @author: pengbingjiang
 * @create: 2020/12/16 19:21
 * @description: TODO
 */
@Data
public class User implements Serializable {
    private String uid;
    private String username;
    private String name;
    private Integer age;
}
