package com.cancer.security.api.config;

import com.cancer.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.api.config
 * @Description:  认证服务
 * @date 2018/7/917:27
 */
@Configuration
@EnableAuthorizationServer
public class CancerAuthenticationServer extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

//    @Autowired
//    private TokenStore redisTokenStore;
    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired(required = false) // 这个配置只有 在jwt 的时候才需要依赖注入,并不是必须的
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false) // 这个配置只有 在jwt 的时候才需要依赖注入,并不是必须的
    private TokenEnhancer jwtTokenEnhancer;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(jwtTokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
        /**
         * 如果 jwtAccessTokenConverter 不为空 则使用它
         */
        if (jwtAccessTokenConverter!=null&&jwtTokenEnhancer!=null){
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain(); //装饰器,责任链
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);   // 添加token额外信息
            enhancers.add(jwtAccessTokenConverter);  // 添加增强器
            tokenEnhancerChain.setTokenEnhancers(enhancers); // 添加到过滤器链
            endpoints.tokenEnhancer(tokenEnhancerChain); // 先转换 jwt,在添加自己的数据

        }
    }

    /**
     * @Description: 配置连接客户端
     * @throws
     * @author yangpan
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//        密码模式（resource owner password credentials）
//        授权码模式（authorization code）
//        简化模式（implicit）
//        客户端模式（client credentials）

        clients.inMemory().withClient(securityProperties.getOauth2().getClient()).secret(securityProperties.getOauth2().getSecret())
                .accessTokenValiditySeconds(3600)  //令牌有效期
                .authorizedGrantTypes("refresh_token","password")  // 授权模式
                .scopes(securityProperties.getOauth2().getScopes()) ;   //权限

    }

}
