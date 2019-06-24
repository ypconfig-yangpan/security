package com.cancer.security.core.social;

import com.cancer.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.qq.config
 * @Description:
 * @date 2018/7/517:09
 */
@Configuration
@EnableSocial
@Order(1)
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

//
//    @Autowired(required = false)
//    private ConnectionSignUp connectionSignUp;



    @Override
    public JdbcUsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
       // repository.setTablePrefix("你数据前缀");

//        if(connectionSignUp != null) {
//            repository.setConnectionSignUp(connectionSignUp);
//        }
        return repository;
    }


    @Bean("cancerSpringSocialConfigurer")
    public SpringSocialConfigurer cancerSpringSocialConfigurer() {
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        CancerSpringSocialConfigurer configurer = new CancerSpringSocialConfigurer(filterProcessesUrl);
        configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return configurer;
    }


    /**
     * @Description:  social 注册处理的工具类
     * @throws
     * @author yangpan
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator)) {
        };

        return providerSignInUtils;
    }


}
