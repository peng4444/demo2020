package cn.pbj.demo2020.book.springinaction4.chapter9;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @pClassName: SecurityConfig1
 * @author: pengbingjiang
 * @create: 2020/6/28 17:46
 * @description: TODO 拦截请求,认证用户,保护视图
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //拦截请求
        http
                .authorizeRequests()
                .antMatchers("/spitters/me").authenticated()
                .antMatchers(HttpMethod.POST, "/spittles").authenticated()
                .anyRequest().permitAll();
        http
                .authorizeRequests()
                .antMatchers("/spitters/me").hasAuthority("ROLE_SPITTER")//.antMatchers("/spitters/me").hasRole("SPITTER")
                .antMatchers(HttpMethod.POST, "/spitters").hasAuthority("ROLE_SPITTER")
                .anyRequest().permitAll();
        //认证用户
        http
                .formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/spitter/me").hasRole("SPITTER")
                .antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")
                .anyRequest().permitAll()
                .and()
                .rememberMe()//Remember-me功能，你只要登录过一次，应用就会记住你，当再次回到应用的时候你就不需要登录了。
                .and()
                .logout()//用户会退出应用，所有的Remember-me token都会被清除掉。
                .logoutSuccessUrl("/")//用户被重定向到其他的页面
                .and()
                .requiresChannel()
                .antMatchers("/spitter/form").requiresSecure();
        //保护视图
    }

}
