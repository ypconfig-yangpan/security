package com.cancer.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social
 * @Description:  配置个性化  登录连接  /auth/qq   /auth的自定义
 * @date 2018/7/615:45
 */
public class CancerSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public CancerSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }


    /**
     * @Description:  SocialAuthenticationFilter 在这个过滤器之前  执行这个方法 postProcess
     * @throws
     * @author yangpan
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }

}
