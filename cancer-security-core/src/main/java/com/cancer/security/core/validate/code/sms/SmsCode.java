package com.cancer.security.core.validate.code.sms;

import com.cancer.security.core.validate.code.ValidateCode;

import java.time.LocalDateTime;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code.sms
 * @Description: 短信验证码
 * @date 2018/7/316:21
 */
public class SmsCode extends ValidateCode{

    public SmsCode(String code, LocalDateTime exprieTime) {
        super(code, exprieTime);
    }

    public SmsCode(String code, int exprieIn) {
        super(code, exprieIn);
    }
}
