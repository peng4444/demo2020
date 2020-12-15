package cn.pbj.demo2020.ssm.entity;

/**
 * @pClassName: User
 * @author: pengbingjiang
 * @create: 2020/12/14 13:40
 * @description: TODO
 */
public class ExampleUser {
    private String name;
    private int age;
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pass='" + pass + '\'' +
                '}';
    }

    public ExampleUser(String name, int age, String pass) {
        this.name = name;
        this.age = age;
        this.pass = pass;
    }
}
