package cn.pbj.demo2020.spring.bean;


import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @pClassName: FactoryBeanDemo
 * @author: pengbingjiang
 * @create: 2020/6/26 09:09
 * @description: TODO
 */
public class FactoryBeanDemo implements FactoryBean<FactoryBeanDemo.CustomBean> {


    public FactoryBeanDemo() {
        System.out.println("实现FactoryBean接口的类自身被放在IOC一级缓存的容器里面，getObject的对象是在另一个缓存对象中");
    }

    @Override
    public CustomBean getObject() {
        return new CustomBean();
    }

    @Override
    public Class<?> getObjectType() {
        return CustomBean.class;
    }

    static class CustomBean {
        public CustomBean() {
            System.out.println("自定义bean");
        }
    }

    @Test
    public void testTransa() {
        BeanFactory context = new AnnotationConfigApplicationContext("configclass.class");
        System.out.println("factoryBean : " + context.getBean("fb"));
        System.out.println("&factoryBean : " + context.getBean("&fb"));
    }

}
