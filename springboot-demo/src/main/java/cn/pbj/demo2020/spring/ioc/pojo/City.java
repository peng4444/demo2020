package cn.pbj.demo2020.spring.ioc.pojo;

/**
 * @pClassName: City
 * @author: pengbingjiang
 * @create: 2020/6/28 11:05
 * @description: TODO
 */
public class City {
    private Long id;
    private String name;

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

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
