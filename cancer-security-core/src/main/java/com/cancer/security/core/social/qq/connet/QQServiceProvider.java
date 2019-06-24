package com.cancer.security.core.social.qq.connet;

import com.cancer.security.core.social.qq.api.QQ;
import com.cancer.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

import java.io.IOException;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.qq.connet
 * @Description:
 * @date 2018/7/516:40
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String  appId;
    private static final String authorizeUrl = "https://graph.qq.com/oauth2.0/authorize";
    private static final String accessTokenUrl = "https://graph.qq.com/oauth2.0/token";

    /**
     * @Description: OAuth2Template 传入accessTokenUrl   authorizeUrl 框架帮我们获取 accessToken
     * authorizeUrl 吧用户导向认证服务, accessTokenUrl 获取令牌的url
     * @throws
     * @author yangpan
     */
    public QQServiceProvider(String appId,String clientSecret) {
//        super(new OAuth2Template(appId,clientSecret,authorizeUrl, accessTokenUrl));
        super(new QQOAuth2Template(appId,clientSecret,authorizeUrl, accessTokenUrl));
        this.appId=appId;
    }

    /**
     *
     * @param accesstoken
     * @return  获取用户qq信息
     */
    @Override
    public QQ getApi(String accesstoken) {
        try {
            return new QQImpl(accesstoken,appId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
