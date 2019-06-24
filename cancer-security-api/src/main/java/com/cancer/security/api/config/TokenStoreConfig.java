package com.cancer.security.api.config;

import com.cancer.security.api.jwt.CancerjwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.api.config
 * @Description:
 * @date 2018/7/179:01
 */
@Configuration
public class TokenStoreConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    /**
     * @Description:  redis 做令牌存储
     * @throws
     * @author yangpan
     */
    @Bean
    @ConditionalOnProperty(prefix = "cancer.security.oauth2",name = "storeType",havingValue = "redis")
    public TokenStore redisTokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        return redisTokenStore;
    }


    @Configuration
    @ConditionalOnProperty(prefix = "cancer.security.oauth2",
            name = "storeType",havingValue = "jwt",matchIfMissing = true) //matchIfMissing=true 如果没有这个配置项,会默认生效
    public static  class jwtTokenStoreConfig{
        @Bean
        public TokenStore jwtTokenStore(){
            JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter());
            return jwtTokenStore;
        }

        @Bean(name="jwtAccessTokenConverter")
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey("yangpan");
            return converter;
        }

        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
            return new CancerjwtTokenEnhancer() ;
        }
    }


}
