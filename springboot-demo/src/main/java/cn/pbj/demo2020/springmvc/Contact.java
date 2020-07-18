package cn.pbj.demo2020.springmvc;

/**
 * @pClassName: Contact
 * @author: pengbingjiang
 * @create: 2020/7/15 14:51
 * @description: TODO
 */
public class Contact {
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
