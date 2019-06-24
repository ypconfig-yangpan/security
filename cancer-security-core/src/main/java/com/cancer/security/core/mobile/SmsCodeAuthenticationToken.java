package com.cancer.security.core.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.mobile
 * @Description:  存放手机号的 token
 * @date 2018/7/49:02
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 3868840703943547655L;
    private final Object principal;
//    private Object credentials; //密码

    public SmsCodeAuthenticationToken(Object mobile) {
        super((Collection)null);
        this.principal = mobile;
        this.setAuthenticated(false);  //未登录 不存信息
    }

    public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);  // must use super, as we override 登录成功存放信息
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }


    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
