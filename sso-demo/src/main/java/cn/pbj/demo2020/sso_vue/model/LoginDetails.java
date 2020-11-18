package cn.pbj.demo2020.sso_vue.model;

/* *
 * 功能描述:
 * @param:
 * @return:
 * @auther: pbj
 * @date: 2019/12/5 17:48
 */
public class LoginDetails {
    private Boolean rememberMe;
    private String verifyCode;

    public LoginDetails(Boolean rememberMe, String verifyCode) {
        this.rememberMe = rememberMe;
        this.verifyCode = verifyCode;
    }

    public LoginDetails() {}

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
