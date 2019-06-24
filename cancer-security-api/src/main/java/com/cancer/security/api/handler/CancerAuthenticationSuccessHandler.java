package com.cancer.security.api.handler;

import com.cancer.security.core.properties.LoginResponseType;
import com.cancer.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.browser.handler
 * @Description:
 * @date 2018/7/216:53
 */
@Component("cancerAuthenticationSuccessHandler")
public class CancerAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(CancerAuthenticationSuccessHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    /**
     * @Description: 根据 cilentId  和clientSecret 返回对应的 token信息
     * @throws
     * @author yangpan
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        logger.info("登录成功");
        String header = request.getHeader("Authorization");

        if (header != null && !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中午client信息");
        }

        String[] tokens = this.extractAndDecodeHeader(header, request);
        assert tokens.length == 2;

        String clinetId = tokens[0];
        String clinetSecret = tokens[1];

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clinetId);
        if (clientDetails==null){
            throw new UnapprovedClientAuthenticationException("clinetId 配置信息不存在"+clinetId);
        }else if (!StringUtils.equalsIgnoreCase(clinetSecret,clientDetails.getClientSecret())){
            throw new UnapprovedClientAuthenticationException("clinetSecret 不匹配"+clinetId);
        }

        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP,clinetId, clientDetails.getScope(), "custmer");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request,authentication);

        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(accessToken));
    }

    /**
     * @Description: 解析请求头 Authorization
     * @throws
     * @author yangpan
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");

        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException var7) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }

}
