package com.cancer.security.core.social.wechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.wechat.api
 * @Description: 实现返回用户信息接口
 * @date 2018/7/615:02
 */
public class WeChatImpl extends AbstractOAuth2ApiBinding implements Wechat {

    /**
     *
     */
    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    /**
     * @param accessToken
     */
    public WeChatImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     * 使用utf-8 替换默认的ISO-8859-1编码
     */
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }

    @Override
    public WeChatUserInfo getUserInfo(String openId) {

        String userInfo = String.format(URL_GET_USER_INFO, openId);
        try {
            Wechat wechat = objectMapper.readValue(userInfo, Wechat.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取用户信息失败");
        }
        return null;
    }
}
