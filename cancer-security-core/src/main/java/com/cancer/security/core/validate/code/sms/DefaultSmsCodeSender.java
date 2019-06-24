package com.cancer.security.core.validate.code.sms;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code.sms
 * @Description:
 * @date 2018/7/316:37
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String telephone, String content) {
        System.out.printf("发送短信验证码成功手机号 %s  , 短信内容 %s",telephone,content);
    }
}
