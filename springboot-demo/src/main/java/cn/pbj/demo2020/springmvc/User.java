package cn.pbj.demo2020.springmvc;

import java.util.List;

/**
 * @pClassName: User
 * @author: pengbingjiang
 * @create: 2020/7/15 14:50
 * @description: TODO
 */
public class User {
    private String name;
    private Integer age;
    private List<Contact> contactList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", contactList=" + contactList +
                '}';
    }
}
