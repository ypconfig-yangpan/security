package com.cancer.security.core.social.qq.api;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.qq.api
 * @Description:
 * @date 2018/7/516:31
 */
public class OpenId {

    String client_id;
    String openid;

    public OpenId(String client_id, String openid) {
        this.client_id = client_id;
        this.openid = openid;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}

