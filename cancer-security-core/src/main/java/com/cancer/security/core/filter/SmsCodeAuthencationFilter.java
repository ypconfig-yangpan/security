package com.cancer.security.core.filter;

import com.cancer.security.core.mobile.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.mobile
 * @Description:
 * @date 2018/7/49:15
 */
public class SmsCodeAuthencationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_TELEPHONE_KEY = "mobile";
    private String mobileParameter = SPRING_SECURITY_FORM_TELEPHONE_KEY;
    private boolean postOnly = true;  // 是否只处理post请求

    public SmsCodeAuthencationFilter() {
        super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainUsername(request);
            if (mobile == null) {
                mobile = "";
            }

            mobile = mobile.trim();
            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }


    /**
     *  吧请求的信息 设置到  SmsCodeAuthenticationToken
     * @param request
     * @param authRequest
     */
    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.mobileParameter = usernameParameter;
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return this.mobileParameter;
    }

}
