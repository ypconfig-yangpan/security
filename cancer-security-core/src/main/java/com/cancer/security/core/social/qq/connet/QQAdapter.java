package com.cancer.security.core.social.qq.connet;

import com.cancer.security.core.social.qq.api.QQ;
import com.cancer.security.core.social.qq.api.QQUserInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.qq.connet
 * @Description:
 * @date 2018/7/516:58
 */
@Configuration
@ConfigurationProperties
public class QQAdapter implements ApiAdapter<QQ> {
    /**
     * @Description: 测试
     * @throws
     * @author yangpan
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    /**
     * @Description:
     * @throws
     * @author yangpan
     */
    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo userInfo = qq.getUserInfo();
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
        //connectionValues.setProfileUrl();  个人主页, qq无 如果是微薄 写微薄个人主页
        connectionValues.setProviderUserId(userInfo.getOpenId()); // 用户唯一标识

    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    /**
     * @Description: 更新..   qq无
     * @throws
     * @author yangpan
     */
    @Override
    public void updateStatus(QQ qq, String s) {
        // do nothing
    }
}
