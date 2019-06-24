package com.cancer.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.qq.api
 * @Description:
 * @date 2018/7/516:14
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements  QQ  {
    private static final Logger logger = LoggerFactory.getLogger(QQImpl.class);

//    private final String accessToken;  //父类继承的 accessToken是单实例, 每一个用户都有一个accessToken 所以这个类QQImpl不能为单例
//    private RestTemplate restTemplate;

    // openid 获取链接(qq用户唯一标识)
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    // 获取用户信息链接
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 构造
     * @param accessToken  用户访问令牌
     * @param appId
     * @throws IOException
     */
    public QQImpl(String accessToken, String appId) throws IOException {
        // TokenStrategy.ACCESS_TOKEN_PARAMETER access_token 放入参数, //详见qq官方文档链接
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        logger.info("callBack = {}",result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {

        String url = String.format(URL_GET_USERINFO, appId,openId);
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);

        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {

            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
