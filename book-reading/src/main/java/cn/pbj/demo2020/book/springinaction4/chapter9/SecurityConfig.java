package cn.pbj.demo2020.book.springinaction4.chapter9;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

/**
 * @pClassName: SecurityConfig
 * @author: pengbingjiang
 * @create: 2020/6/28 16:46
 * @description: TODO 启用Web安全性功能的最简单配置
 */
@Configuration
@EnableWebSecurity//使用Spring MVC开发的，那么就应该考虑使用@EnableWebMvcSecurity替代
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    //获取了用户的用户名、密码以及是否启用的信息，这些信息会用来进行用户认证。
    public static final String DEF_USERS_BY_USERNAME_QUERY =
            "select username, password, enabled " +
                    "from users " +
                    "where username = ?";
    //查询查找了用户所授予的权限，用来进行鉴权
    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
            "select username, authority " +
                    "from authorities " +
                    "where username = ?";
    //查找了用户作为群组的成员所授予的权限。
    public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY =
            "select g.id, g.group_name, ga.authority " +
                    "from groups g, group_members gm, group_authorities ga " +
                    "where gm.username = ? " +
                    "and g.id = ga.group_id " +
                    "and g.id = gm.group_id";

    //configure()方法中的AuthenticationManagerBuilder使用构造者风格的接口来构建认证配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //调用inMemoryAuthentication()就能启用内存用户存储
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").authorities("ROLE_USER", "ROLE_ADMIN");
        //调用withUser()方法为内存用户存储添加新的用户

        // 使用以JDBC为支撑的用户存储，可以使用jdbc-Authentication()方法
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, true " +
                                "from Spitter where username=?")
                .authoritiesByUsernameQuery(
                        "select username, 'ROLE_USER' from Spitter where username=?")
                .passwordEncoder(new StandardPasswordEncoder("123456"));

        //基于 LDAP进行认证
        auth
                .ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("{uid={0}}")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .passwordCompare()//配置密码比对
                .passwordEncoder(new Md5PasswordEncoder())//指定密码转码器
                .passwordAttribute("passcode");//在进行服务器端密码比对时
        //方法userSearchFilter()和groupSearchFilter()用来为基础LDAP查询提供过滤条件，它们分别用于搜索用户和组。
        //默认情况下，对于用户和组的基础查询都是空的，也就是表明搜索会在 LDAP 层级结构的根开始。
        //userSearchBase()属性为查找用户提供了基础查询。groupSearch-Base()为查找组指定了基础查询。
    }
}
