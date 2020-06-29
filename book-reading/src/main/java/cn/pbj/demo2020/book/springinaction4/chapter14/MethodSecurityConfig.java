package cn.pbj.demo2020.book.springinaction4.chapter14;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * @pClassName: MethodSecurityConfig
 * @author: pengbingjiang
 * @create: 2020/6/29 17:09
 * @description: TODO
 */
@Configuration
//@EnableGlobalMethodSecurity(jsr250Enable=true)
@EnableGlobalMethodSecurity(securedEnabled=true)//启用基于注解的方法安全性
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}

//在 Web 层的安全配置中设置认证
//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//  auth.inMemoryAuthentication()
//      .withUser("user").password("password").role("USER");
//}
