package cn.pbj.demo2020.sso_vue.service;


import cn.pbj.demo2020.sso_vue.domain.LoginResultDetails;

/**
 * @author Pu Zhiwei {@literal puzhiweipuzhiwei@foxmail.com}
 * create          2019-12-02 18:50
 */
public interface LoginService {
    /**
     * @return 返回登陆信息
     * */
    LoginResultDetails get();
}
