package com.cancer.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.properties
 * @Description:
 * @date 2018/7/210:19
 */
public class BrowserProperties {

    private  String  loginPage = "/login.html";

    private String signUpUrl = "/signUp.html";

    private LoginResponseType loginType = LoginResponseType.JSON;

    private int rememeberMe = 3600;


    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }

    public int getRememeberMe() {
        return rememeberMe;
    }

    public void setRememeberMe(int rememeberMe) {
        this.rememeberMe = rememeberMe;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

}
