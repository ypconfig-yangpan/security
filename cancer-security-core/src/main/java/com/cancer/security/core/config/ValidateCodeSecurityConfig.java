package com.cancer.security.core.config;

import com.cancer.security.core.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.config
 * @Description:
 * @date 2018/7/414:46
 */
@Component("validateCodeSecurityConfig")
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.addFilterAfter(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
