package com.cancer.security.core.properties;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.properties
 * @Description:
 * @date 2018/7/217:29
 */
public class ImageCodeProperties {

    private int width = 67;
    private int height = 23;
    private int length =4;
    private int expireIn = 60; //默认过期时间

    private String url;

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
