package cn.pbj.demo2020.algorithm;

/**
 * @pClassName: BranchPrediction
 * @author: pengbingjiang
 * @create: 2020/11/23 20:00
 * @description: TODO
 */
public class BranchPrediction {
    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <1000; j ++) {
                for (int k = 0; k < 10000; k++) {
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("run1 Time spent is " + (end - start)+"ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j <1000; j ++) {
                for (int k = 0; k < 100; k++) {
                }
            }
        }
        end = System.currentTimeMillis();
        System.out.println("run2 Time spent is " + (end - start) + "ms");
    }
}
