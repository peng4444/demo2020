package cn.pbj.demo2020.blog.java;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName: Demo1
 * @Author: pbj
 * @Date: 2020/6/16 19:41
 * @Description: TODO 1.使用HashSet判断主键是否存在
 */
public class Demo1 {
    /** 查找第一个重复字符 */
    public static Character findFirstRepeatedChar(String string) {
        // 检查空字符串
        if (Objects.isNull(string) || string.isEmpty()) {
            return null;
        }

        // 查找重复字符
        char[] charArray = string.toCharArray();
        Set charSet = new HashSet<>(charArray.length);
        for (char ch : charArray) {
            if (charSet.contains(ch)) {
                return ch;
            }
            charSet.add(ch);
        }

        // 默认返回为空
        return null;
    }

    @Test
    public void TestDemo() {
        findFirstRepeatedChar("abcacb1");
    }
}
