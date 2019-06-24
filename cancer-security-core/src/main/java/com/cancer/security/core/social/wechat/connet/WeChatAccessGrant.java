package com.cancer.security.core.social.wechat.connet;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.wechat.connet
 * @Description:
 * @date 2018/7/615:11
 */
public class WeChatAccessGrant extends AccessGrant {


    private String openId;

    public WeChatAccessGrant(String accessToken) {
        super("");
    }

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
