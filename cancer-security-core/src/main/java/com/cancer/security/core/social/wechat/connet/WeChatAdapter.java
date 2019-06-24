package com.cancer.security.core.social.wechat.connet;

import com.cancer.security.core.social.wechat.api.WeChatUserInfo;
import com.cancer.security.core.social.wechat.api.Wechat;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.social.wechat.connet
 * @Description: 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 * @date 2018/7/615:23
 */
public class WeChatAdapter implements ApiAdapter<Wechat> {



    private String openId;

    public WeChatAdapter() {}

    public WeChatAdapter(String openId){
        this.openId = openId;
    }

    @Override
    public boolean test(Wechat wechat) {
        return true;
    }

    @Override
    public void setConnectionValues(Wechat wechat, ConnectionValues connectionValues) {
        WeChatUserInfo userInfo = wechat.getUserInfo(openId);
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setProviderUserId(userInfo.getOpenid());
        connectionValues.setImageUrl(userInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(Wechat wechat) {
        return null;
    }

    @Override
    public void updateStatus(Wechat wechat, String s) {
        // 什么都不做
    }
}
