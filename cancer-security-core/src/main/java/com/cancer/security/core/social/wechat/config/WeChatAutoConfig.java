package com.cancer.security.core.social.wechat.config;

import com.cancer.security.core.properties.SecurityProperties;
import com.cancer.security.core.properties.WeChatProperties;
import com.cancer.security.core.social.CancerConnectView;
import com.cancer.security.core.social.wechat.connet.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

import javax.sql.DataSource;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.wechat.config
 * @Description:
 * @date 2018/7/615:30
 */
@Configuration
@ConditionalOnProperty(prefix = "cancer.secutiry.social.weChat", name = "app-id") // 配置 app-id
public class WeChatAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @Description:  ProviderId 默认是 weChat
     * @throws
     * @author yangpan
     */
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        // 获取配置信息 appId  providerId app-Secret
        WeChatProperties weChatConfig = securityProperties.getSocial().getWeChat();

        return new WeChatConnectionFactory(weChatConfig.getProviderId(), weChatConfig.getAppId(),
                weChatConfig.getAppSecret());
    }


    /**
     * /connect/weixin POST请求,绑定微信返回connect/weixinConnected视图
     * /connect/weixin DELETE请求,解绑返回connect/weixinConnect视图
     * @return
     */
//    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
//    @ConditionalOnMissingBean(name = "weixinConnectedView")
//    public View weixinConnectedView() {
//        return new CancerConnectView();
//    }

}
