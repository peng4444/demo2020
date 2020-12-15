package cn.pbj.demo2020.ssm.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @pClassName: ShiroConfig
 * @author: pengbingjiang
 * @create: 2020/12/15 09:37
 * @description: TODO
 */
@Configuration
public class ShiroConfig {

    /**
     * 路径过滤规则
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 拦截器
        Map<String, String> map = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        // 对静态资源设置匿名访问
        map.put("/static/**", "anon");
        map.put("/css/**", "anon");
        map.put("/js/**", "anon");
        // 配置不会被拦截的链接 顺序判断
        map.put("/login", "anon");
        map.put("/index", "anon");
        // 允许 swagger 不用认证就可以访问
        map.put("/swagger-ui.html", "anon");
        map.put("/v2/**", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/webjars/springfox-swagger-ui/**", "anon");
        // 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        // 进行身份认证后才能访问
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        // user指的是用户认证通过或者配置了Remember Me记住用户登录状态后可访问
        map.put("/captcha/captchaImage**", "anon");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 自定义身份认证Realm（包含用户名密码校验，权限校验等）
     * @return
     */
    @Bean
    public AuthRealm authRealm() {
        return new AuthRealm();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm());
        return securityManager;
    }

    /**
     * 开启Shiro注解模式，可以在Controller中的方法上添加注解
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
