package com.cancer.security.api.jwt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.api.jwt
 * @Description:
 * @date 2018/7/1710:02
 */

public   class CancerjwtTokenEnhancer implements TokenEnhancer {
    /**
     * @Description: 添加自定义信息到 accessToken
     * @throws
     * @author yangpan
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Map<String, Object> additionalUerInfo = additionalUerInfo();
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
        // 设置附加信息
        defaultOAuth2AccessToken.setAdditionalInformation(additionalUerInfo);
        return defaultOAuth2AccessToken;
    }

    public  Map<String, Object> additionalUerInfo(){
        HashMap<String, Object> map = new HashMap<>(4);
        map.put("company","yangshiqi");
        return map;};

}
