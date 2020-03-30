package cn.pbj.demo2020.everyday;

/**
 * @ClassName: Demo20200330
 * @Author: pbj
 * @Date: 2020/3/30 16:42
 * @Description: TODO 20200330
 * [if-else代码优化的八种方案](https://www.cnblogs.com/jay-huaxiao/p/12586598.html)
 */
public class Demo20200330 {

    //if-else代码优化的八种方案
    //优化方案一：提前return，去除不必要的else
    public void demo1() {
        boolean condition = true;
//        if (condition) {
//            //do Something
//        } else {
//            return;
//        }
        if (!condition) {
            return;
        }
        //do something
    }

    //优化方案二：使用条件三目运算符
    public void demo2() {
        boolean condition = true;
//        int price = 10;
//        if (condition) {
//            price = 20;
//        } else {
//            price = 1;
//        }
        int price = condition? 20:1;
    }

}
