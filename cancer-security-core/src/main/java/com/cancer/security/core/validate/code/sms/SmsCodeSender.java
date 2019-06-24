package com.cancer.security.core.validate.code.sms;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code.sms
 * @Description:
 * @date 2018/7/316:36
 */
public interface SmsCodeSender {

    void send(String telephone,String content);
}
