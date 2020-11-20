package cn.pbj.demo2020.myblog.shiro;

import cn.hutool.core.bean.BeanUtil;
import cn.pbj.demo2020.myblog.entity.User;
import cn.pbj.demo2020.myblog.service.UserService;
import cn.pbj.demo2020.myblog.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @pClassName: AccountRealm
 * @author: pengbingjiang
 * @create: 2020/11/12 15:30
 * @description: TODO AccountRealm是shiro进行登录或者权限校验的逻辑所在
 */
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    /*
    * @Description: 为了让realm支持jwt的凭证校验
    * @Param: [token]
    * @return: boolean
    * @Author: pengbingjiang
    * @Date: 2020/11/19 21:44
    */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
    
    /**
    * @Description: 权限校验
    * @Param: [principals]
    * @return: org.apache.shiro.authz.AuthorizationInfo
    * @Author: pengbingjiang
    * @Date: 2020/11/19 21:44
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    
    /**
    * @Description: 登录认证校验
    * @Param: [token]
    * @return: org.apache.shiro.authc.AuthenticationInfo
    * @Author: pengbingjiang
    * @Date: 2020/11/19 21:47
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) token;

        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        User user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }

        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定");
        }

        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);

        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}