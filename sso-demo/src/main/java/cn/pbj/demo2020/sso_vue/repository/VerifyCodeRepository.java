package cn.pbj.demo2020.sso_vue.repository;

/**
 */
public interface VerifyCodeRepository {

    void save(String key, String code);

    String find(String key);

    void remove(String key);
}
