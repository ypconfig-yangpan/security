package com.cancer.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.properties
 * @Description:
 * @date 2018/7/614:53
 */
public class WeChatProperties extends SocialProperties{

    private String providerId = "weChat";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }


}
