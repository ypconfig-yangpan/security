package com.cancer.security.core.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.mobile
 * @Description:
 * @date 2018/7/49:00
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {


    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }


    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authencationToken = (SmsCodeAuthenticationToken) authentication;
        String mobile = (String)authencationToken.getPrincipal();
        UserDetails user = userDetailsService.loadUserByUsername(mobile);

        if (user==null){
            throw new InternalAuthenticationServiceException("user must not be null");
        }
        SmsCodeAuthenticationToken authencationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
        // 未认证的token 设置了 details 信息, 这里copy 过来
        authencationResult.setDetails(authencationToken.getDetails());
        return authencationResult;
    }

    /**
     * 这个方法 是 决定使用哪一种方式 去认证
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }


}
