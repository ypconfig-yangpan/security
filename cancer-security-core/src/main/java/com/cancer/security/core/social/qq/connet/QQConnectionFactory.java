package com.cancer.security.core.social.qq.connet;

import com.cancer.security.core.social.qq.api.QQ;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.social.ServiceProvider;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.qq.connet
 * @Description:  qq连接工厂,获取连接
 * @date 2018/7/517:05
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    /**
     * @Description:  获取 providerId,appId  new QQAdapter()用户适配信息
     * @throws
     * @author yangpan
     */
    public QQConnectionFactory(String providerId,String appId,String clientSecret ) {
        super(providerId, new QQServiceProvider(appId,clientSecret), new QQAdapter());
    }
}
