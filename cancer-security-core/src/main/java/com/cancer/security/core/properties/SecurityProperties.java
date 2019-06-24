package com.cancer.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.properties
 * @Description:
 * @date 2018/7/210:19
 */
@ConfigurationProperties(prefix = "cancer.secutiry")
@PropertySource(value = "classpath:application.properties")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private Oauth2Properties oauth2 =  new Oauth2Properties();


    SocialProperties social = new SocialProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public Oauth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(Oauth2Properties oauth2) {
        this.oauth2 = oauth2;
    }
}
