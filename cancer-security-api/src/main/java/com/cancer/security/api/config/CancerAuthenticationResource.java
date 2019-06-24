package com.cancer.security.api.config;

import com.cancer.security.api.handler.CancerAuthenticationFailureHandler;
import com.cancer.security.api.handler.CancerAuthenticationSuccessHandler;
import com.cancer.security.core.config.SmsAuthencationSecurityConfig;
import com.cancer.security.core.config.ValidateCodeSecurityConfig;
import com.cancer.security.core.properties.SecurityConstants;
import com.cancer.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.api.config
 * @Description:
 * @date 2018/7/917:28
 */

@EnableResourceServer
@Configuration
public class CancerAuthenticationResource extends ResourceServerConfigurerAdapter{
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private CancerAuthenticationFailureHandler cancerAuthenticationFailureHandler;

    @Autowired
    private CancerAuthenticationSuccessHandler cancerAuthenticationSuccessHandler;

    @Autowired
    private SmsAuthencationSecurityConfig smsAuthencationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer cancerSpringSocialConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .apply(smsAuthencationSecurityConfig)
//                .and()
//                .apply(validateCodeSecurityConfig)
                .and()
                .apply(cancerSpringSocialConfigurer)
                .and()
                .formLogin().loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(cancerAuthenticationSuccessHandler)
                .failureHandler(cancerAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL, //"/authentication/require"
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,      // "/authentication/mobile"
                        securityProperties.getBrowser().getLoginPage(),             // "/login.html" demoLogin.html
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/**"   // "/code/*"
                ).permitAll()
                .anyRequest()
                .authenticated();
    }
}
