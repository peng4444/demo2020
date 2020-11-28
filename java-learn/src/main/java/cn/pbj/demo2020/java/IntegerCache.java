package cn.pbj.demo2020.java;

/**
 * @pClassName: IntegerCache
 * @author: pengbingjiang
 * @create: 2020/11/28 16:21
 * @description: TODO
 */
public class IntegerCache {
    public static void main(String[] args) {
        //Integer缓存-128至127,low固定，high可以通过JVM修改
        Integer a = 100;
        Integer b = 100;
        System.out.println(a==b);// true
        Integer c = 130;
        Integer d = 130;
        System.out.println(c==d);// false
        // Boolean 缓存true和false
        Boolean a1 = true;
        Boolean b1 = true;
        System.out.println(a1==b1);// true
        Boolean c1 = false;
        Boolean d1 = false;
        System.out.println(c1==d1);// true
        // Character缓存’\u0000’和’\u007f’（含）之间的char类型的数值，需要对照ASCII码查询
        //[ASCII码查询](https://raw.githubusercontent.com/peng4444/picgo/main/img/20201121164250.png)
        Character a2 = 'a';
        Character b2 = 'a';
        System.out.println(a2==b2); // true
        Character c2 = '?';
        Character d2 = '?';
        System.out.println(c2==d2); // true

        Long a3 = 100L;
        Long b3 = 100L;
        System.out.println(a3==b3);

        Short c3 = 100;
        Short d3 = 100;
        System.out.println(c3==d3);
    }
}
