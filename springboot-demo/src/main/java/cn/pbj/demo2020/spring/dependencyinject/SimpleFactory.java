package cn.pbj.demo2020.spring.dependencyinject;

/**
 * @pClassName: SimpleFactory
 * @author: pengbingjiang
 * @create: 2020/6/26 08:55
 * @description: TODO 简单工厂:将对象的创建和使用隔离开来，达到解耦的目的。工厂负责创建对象，并且可以干预创建的逻辑
 * 这种写法的局限性。依靠phoneType来创建对象不利于扩展。如果现在的需求生产的对象不限定返回类型，给啥就创建啥，怎么做。
 */
public class SimpleFactory {
    abstract static class AbstractPhone {
        abstract void call();
    }

    static class XiaoMi extends AbstractPhone {
        @Override
        void call() {
            System.out.println("小米手机打电话。。。。");
        }
    }

    static class Meizu extends AbstractPhone {
        @Override
        void call() {
            System.out.println("魅族手机打电话。。。。");
        }
    }

    public AbstractPhone getPhone(String phoneType) {
        if ("小米".equals(phoneType)) {
            return new XiaoMi();
        } else if ("魅族".equals(phoneType)) {
            return new Meizu();
        }
        return null;
    }


    public static void main(String[] args) {
        SimpleFactory s = new SimpleFactory();
        s.getPhone("小米").call();
        s.getPhone("魅族").call();
    }
}
