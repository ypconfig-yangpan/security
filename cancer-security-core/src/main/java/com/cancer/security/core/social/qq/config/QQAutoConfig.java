package com.cancer.security.core.social.qq.config;

import com.cancer.security.core.properties.QQProperties;
import com.cancer.security.core.properties.SecurityProperties;
import com.cancer.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.qq.config
 * @Description:
 * @date 2018/7/615:41
 */
@Configuration
@ConditionalOnProperty(prefix = "cancer.secutiry.social.qq", name = "app-id") // 配置 app-id
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    public JdbcUsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
//                connectionFactoryLocator, Encryptors.noOpText());
//        // repository.setTablePrefix("你数据前缀");
//
//        return repository;
//    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
     * #createConnectionFactory()
     */
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
    }
}
