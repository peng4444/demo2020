package cn.pbj.demo2020.spring.ioc.pojo;

/**
 * @pClassName: User
 * @author: pengbingjiang
 * @create: 2020/6/28 11:03
 * @description: TODO
 */
public class User {
    private Long id;
    private String name;
    private String password;
    private int age;
    private City city;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", city=" + city +
                '}';
    }
}
