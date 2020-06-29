package cn.pbj.demo2020.spring.ioc.pojo;

/**
 * @pClassName: Car
 * @author: pengbingjiang
 * @create: 2020/6/28 11:39
 * @description: TODO
 */
public class Car {

    private Integer maxSpeed;
    private String brand;
    private Double price;

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
