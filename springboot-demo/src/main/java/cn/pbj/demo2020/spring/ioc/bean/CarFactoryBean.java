package cn.pbj.demo2020.spring.ioc.bean;

import cn.pbj.demo2020.spring.ioc.pojo.Car;
import org.springframework.beans.factory.FactoryBean;

/**
 * @pClassName: CarFactoryBean
 * @author: pengbingjiang
 * @create: 2020/6/28 11:38
 * @description: TODO
 */
public class CarFactoryBean implements FactoryBean<Car> {
    private String carInfo;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] infos = carInfo.split(",");
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.valueOf(infos[1]));
        car.setPrice(Double.valueOf(infos[2]));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    @Override
    public String toString() {
        return "CarFactoryBean{" +
                "carInfo='" + carInfo + '\'' +
                '}';
    }
}
