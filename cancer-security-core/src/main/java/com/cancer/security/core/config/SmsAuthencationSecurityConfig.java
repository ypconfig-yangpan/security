package com.cancer.security.core.config;

import com.cancer.security.core.filter.SmsCodeAuthencationFilter;
import com.cancer.security.core.mobile.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.config
 * @Description:
 * @date 2018/7/410:10
 */
@Component
public class SmsAuthencationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {
    @Autowired
    protected UserDetailsService userDetailsService;
    @Autowired
    protected AuthenticationSuccessHandler CancerAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler CancerAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthencationFilter authencationFilter = new SmsCodeAuthencationFilter();
        authencationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authencationFilter.setAuthenticationFailureHandler(CancerAuthenticationFailureHandler);
        authencationFilter.setAuthenticationSuccessHandler(CancerAuthenticationSuccessHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
               .addFilterAfter(authencationFilter, UsernamePasswordAuthenticationFilter.class);
//               .addFilterBefore(authencationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
