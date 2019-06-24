package com.cancer.security.browser.config;

import com.cancer.security.browser.handler.CancerAuthenticationFailureHandler;
import com.cancer.security.browser.handler.CancerAuthenticationSuccessHandler;
import com.cancer.security.core.config.SmsAuthencationSecurityConfig;
import com.cancer.security.core.config.ValidateCodeSecurityConfig;
import com.cancer.security.core.properties.SecurityConstants;
import com.cancer.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
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
    private SmsAuthencationSecurityConfig  smsAuthencationSecurityConfig;


    @Autowired
    private SpringSocialConfigurer cancerSpringSocialConfigurer;

    /**
     * 记住我的 token仓库
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true); //启动时创建表
        return jdbcTokenRepository;
    }

    /**
     *
     * ingore是完全绕过了spring security的所有filter，相当于不走spring security
     * permitall没有绕过spring security，其中包含了登录的以及匿名的。
     * @param// web  WebSecurity主要是配置跟web资源相关的，比如css、js、ima     ges
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .apply(smsAuthencationSecurityConfig)
                .and()
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(cancerSpringSocialConfigurer)
                .and()
                .formLogin().loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(cancerAuthenticationSuccessHandler)
                .failureHandler(cancerAuthenticationFailureHandler)
            .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememeberMe())
                .userDetailsService(userDetailsService)
            .and()
                .authorizeRequests()
            .antMatchers(
            SecurityConstants.DEFAULT_UNAUTHENTICATION_URL, //"/authentication/require"
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,      // "/authentication/mobile"
                        securityProperties.getBrowser().getLoginPage(),             // "/login.html" demoLogin.html
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/**",  // "/code/*"
                        securityProperties.getBrowser().getSignUpUrl(),
                        "/user/regist"
                        //securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
                       // securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html",
            ).permitAll()
                .anyRequest()
                .authenticated();

    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return this.userDetailsService;
    }


}
