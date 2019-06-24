package com.cancer.security.core.social.wechat.api;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.wechat
 * @Description:
 * @date 2018/7/614:50
 */
public interface Wechat {

    WeChatUserInfo getUserInfo(String openId);
}
