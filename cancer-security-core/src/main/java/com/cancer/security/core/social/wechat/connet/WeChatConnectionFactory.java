package com.cancer.security.core.social.wechat.connet;

import com.cancer.security.core.social.wechat.api.Wechat;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.wechat.connet
 * @Description:
 * @date 2018/7/615:34
 */
public class WeChatConnectionFactory extends OAuth2ConnectionFactory<Wechat> {

    /**
     * @param appId
     * @param appSecret
     */
    public WeChatConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeChatServiceProvider(appId, appSecret), new WeChatAdapter());
    }

    /**
     * 由于微信的openId是和accessToken一起返回的，
     * 所以在这里直接根据accessToken设置providerUserId即可，
     * 不用像QQ那样通过QQAdapter来获取
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if(accessGrant instanceof WeChatAccessGrant) {
            return ((WeChatAccessGrant)accessGrant).getOpenId();
        }
        return null;
    }

    /**
     *
     * @param accessGrant
     * @return
     */
    public Connection<Wechat> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<Wechat>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(),
                getApiAdapter(extractProviderUserId(accessGrant)));
    }

    /**
     *
     * @param data
     * @return
     */
    public Connection<Wechat> createConnection(ConnectionData data) {
        return new OAuth2Connection<Wechat>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }
    /**
     * @Description: WeChatAdapter 是一个多实例对象, 因为 每次获取都会返回 用户唯一的信息 openId 所以 要new WeChatAdapter()
     * @throws
     * @author yangpan
     */
    private ApiAdapter<Wechat> getApiAdapter(String providerUserId) {
        return new WeChatAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<Wechat> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<Wechat>) getServiceProvider();
    }
}
