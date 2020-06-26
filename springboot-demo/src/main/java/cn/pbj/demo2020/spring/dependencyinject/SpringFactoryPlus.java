package cn.pbj.demo2020.spring.dependencyinject;

/**
 * @pClassName: SpringFactoryPlus
 * @author: pengbingjiang
 * @create: 2020/6/26 08:57
 * @description: TODO 简单工厂:利用反射动态创建对象，根据需求生产对象。
 * Spring利用工厂将对象的创建和使用进行隔离，还可以定制一些bean对象的创建逻辑。
 * Spring利用反射创建对象，并将创建好的对象放入一个大工厂，实现了对象创建和使用的解耦。
 * 需要使用的时候可以方便的通过BeanFactory.getBean()获取。在此之上还扩展了对注解的支持，使用注解就可以注入对象。这就是传说中的DI(Dependency Inject)依赖注入
 */
public class SpringFactoryPlus {
    static class XiaoMi {
        void call() {
            System.out.println("小米手机打电话。。。。");
        }
    }

    static class Meizu  {
        void call() {
            System.out.println("魅族手机打电话。。。。");
        }
    }

    static class HuaWei  {
        void call() {
            System.out.println("华为手机打电话。。。。");
        }
    }

    public <T> T getPhone(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        SpringFactoryPlus s = new SpringFactoryPlus();
        s.getPhone(XiaoMi.class).call();
        s.getPhone(Meizu.class).call();
        s.getPhone(HuaWei.class).call();
    }
}
