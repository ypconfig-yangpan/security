package com.cancer.security.core.properties;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.properties
 * @Description:
 * @date 2018/7/316:53
 */
public class SMSCodeProperties {

    private int length =4;
    private int expireIn = 60; //默认过期时间
    private String url ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
