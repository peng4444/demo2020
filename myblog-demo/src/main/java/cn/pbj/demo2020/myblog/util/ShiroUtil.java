package cn.pbj.demo2020.myblog.util;

import cn.pbj.demo2020.myblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @pClassName: ShiroUtil
 * @author: pengbingjiang
 * @create: 2020/11/12 15:29
 * @description: TODO
 */
public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
