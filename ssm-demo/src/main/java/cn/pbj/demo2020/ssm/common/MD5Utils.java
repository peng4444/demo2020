package cn.pbj.demo2020.ssm.common;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @pClassName: MD5Utils
 * @author: pengbingjiang
 * @create: 2020/12/15 10:51
 * @description: TODO
 */
public class MD5Utils {

    private static final String SALT = "niaobulashi";

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String pwd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pwd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String encrypt(String username, String pwd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pwd, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static void main(String[] args) {
        System.out.println("MD5加密后的密文为：" + MD5Utils.encrypt("root", "root"));
    }
}
