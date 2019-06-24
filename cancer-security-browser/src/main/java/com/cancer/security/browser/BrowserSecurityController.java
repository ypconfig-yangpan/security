package com.cancer.security.browser;


import com.cancer.security.core.properties.SecurityProperties;
import com.cancer.security.core.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @throws
 * @author yangpan
 */
@RestController
public class BrowserSecurityController {
    private  static Logger log = LoggerFactory.getLogger(BrowserSecurityController.class);
    @Autowired
    private SecurityProperties securityProperties;   // 读取配置

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    RequestCache cache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * @Description: 处理跳转的请求  loginPrecessingUrl
     * @throws
     * @author yangpan
     */
    @RequestMapping(value="/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED) //401 未授权`
    public SimpleResponse requestAuthencation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest saveRequest = cache.getRequest(request, response);
        log.info(Thread.currentThread()+"request缓存获取的url====>{}", saveRequest.getRedirectUrl());

        if (saveRequest!=null){
            // 引发跳转的请求
            String redirectUrl = saveRequest.getRedirectUrl();
            // 判断来源  .html 或者其他
            if (StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                log.info(Thread.currentThread()+"跳转的页面====>{}",securityProperties.getBrowser().getLoginPage());
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }

        }
        return new SimpleResponse("访问的服务需要身份认证,请去登录页");
    }


    /**
     * @Description: 从连接中获取  social 用户信息
     * @throws
     * @author yangpan
     */
    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;
    }


}
