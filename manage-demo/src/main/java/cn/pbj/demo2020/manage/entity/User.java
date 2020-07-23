package cn.pbj.demo2020.manage.entity;

/**
 * @pClassName: User
 * @author: pengbingjiang
 * @create: 2020/7/23 14:30
 * @description: TODO
 */
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
