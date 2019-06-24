package com.cancer.security.core.social.wechat.connet;

import com.cancer.security.core.social.wechat.api.WeChatImpl;
import com.cancer.security.core.social.wechat.api.Wechat;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.wechat.connet
 * @Description:
 * @date 2018/7/615:26
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<Wechat> {


    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * @param appId
     * @param appSecret
     */
    public WeChatServiceProvider(String appId, String appSecret) {
        super(new WeChatOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }


    /**
     * @Description:
     * @throws
     * @author yangpan
     */
    @Override
    public Wechat getApi(String accessToken) {
        return new WeChatImpl(accessToken);
    }

}
