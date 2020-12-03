package cn.pbj.demo2020.blog.java;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @pClassName: CollectionsMove
 * @author: pengbingjiang
 * @create: 2020/11/29 14:53
 * @description: TODO 集合并集交集差集
 */
public class CollectionsMove {
    public static void main(String[] args) {
        List<Integer> integers1 = new ArrayList<>();
        integers1.add(1);
        integers1.add(2);

        List<Integer> integers2 = new ArrayList<>();
        integers2.add(1);
        integers2.add(2);
        integers2.add(3);
        // 并集
        List<Integer> union = ListUtils.union(integers1, integers2);
        System.out.println(union);
        // 交集
        List<Integer> intersection = ListUtils.intersection(integers1, integers2);
        System.out.println(intersection);
        // 差集
        List<Integer> subtract = ListUtils.subtract(integers1, integers2);
        System.out.println(subtract);
    }
}
