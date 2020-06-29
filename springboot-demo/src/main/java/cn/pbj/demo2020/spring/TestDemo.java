package cn.pbj.demo2020.spring;

import cn.pbj.demo2020.spring.ioc.bean.CarFactoryBean;
import cn.pbj.demo2020.spring.ioc.pojo.Car;
import cn.pbj.demo2020.spring.ioc.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @pClassName: TestDemo
 * @author: pengbingjiang
 * @create: 2020/6/28 11:07
 * @description: TODO Spring代码参数类
 */
public class TestDemo {

    //2.测试获取在XML中定义的bean
    @Test
    public void factoryBeanTest() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("bean-definition.xml");
        Car car = beanFactory.getBean("car", Car.class);
        System.out.println(car);//cn.pbj.demo2020.spring.ioc.pojo.Car@67b467e9
        CarFactoryBean carFactoryBean = beanFactory.getBean("&car", CarFactoryBean.class);
        System.out.println(carFactoryBean);//CarFactoryBean{carInfo='超级跑车,400,2000000'}
        //beanName前面加上&获取的是FactoryBean本身，不加获取的getObject()返回的对象。
    }

    //1.测试Spring BeanDefinition
    @Test
    public void beanDefinitionTest() {
        //声明bean工厂，然后通过指定的XML文件加载bean的定义元信息，最后通过bean工厂获取bean。
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("bean-definition.xml");
        User user = beanFactory.getBean("user", User.class);
        System.err.println(user);//User{id=1, name='leisurexi', password='azxcfs', age=18, city=City{id=1, name='beijing'}}
    }
}
