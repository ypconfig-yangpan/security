package com.cancer.security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.browser.response
 * @Description:
 * @date 2018/7/29:09
 */
@Service
public class MyUserDetailService implements UserDetailsService ,SocialUserDetailsService{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 表单登录用户名
        return getUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
        // 社交登录用户id
        return getUser(username);
    }

    private SocialUser getUser(String username) {
        return new SocialUser("yangpan",passwordEncoder.encode("123456"),
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
