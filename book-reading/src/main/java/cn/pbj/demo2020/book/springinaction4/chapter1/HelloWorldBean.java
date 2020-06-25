package cn.pbj.demo2020.book.springinaction4.chapter1;

/**
 * @pClassName: HelloWorldBean
 * @author: pengbingjiang
 * @create: 2020/6/25 19:50
 * @description: TODO Spring不会在HelloWorldBean上有如何不合理的要求
 */
public class HelloWorldBean {
    //Spring的非侵入编程模型意味着这个类在Spring应用和非Spring应用中都可以发挥同样的作用。
    public String sayHello() {
        return "Hello World";
    }
}
