package cn.pbj.demo2020.java;

/**
 * @pClassName: ByteMove
 * @author: pengbingjiang
 * @create: 2020/11/28 17:08
 * @description: TODO 位移运算 tips:注意负数的无符号右移
 */
public class ByteMove {
    public static void main(String[] args) {
        int a = 35;
        System.out.println("35右移："+(a>>1));// 17
        System.out.println(" 35左移："+(a<<1));// 70
        System.out.println("35无符号右移："+(a>>>1));// 17
        int b = -35;
        System.out.println("-35右移："+(b>>1));// -18
        System.out.println(" -35左移："+(b<<1));// -70
        System.out.println(Integer.toBinaryString(-35));//都是32位数进行操作
        System.out.println("-35无符号右移："+Integer.toBinaryString(b>>>1));//-35无符号右移：2147483630
        int c = 99;
        System.out.println("99右移："+(c>>1));// 49
        System.out.println(" 99左移："+(c<<1));// 198
        int d = -99;
        System.out.println("-99右移："+(d>>1));// -50
        System.out.println(" 99左移："+(d<<1));// -198


        // Java中对byte进行位移操作，byte会先转换为int类型，再进行位移操作
        byte s = -35;
        byte j = 35;
        System.out.println(Integer.toBinaryString(s));
        System.out.println(Integer.toBinaryString(j));
        System.out.println(s>>>1);
        System.out.println(j>>>1);
        System.out.println(Integer.toBinaryString(s>>>1));
        System.out.println(Integer.toBinaryString(j>>>1));
    }
}
