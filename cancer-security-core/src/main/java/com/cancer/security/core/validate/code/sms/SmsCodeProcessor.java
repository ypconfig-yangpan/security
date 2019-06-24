package com.cancer.security.core.validate.code.sms;

import com.cancer.security.core.properties.SecurityConstants;
import com.cancer.security.core.validate.code.ValidateCode;
import com.cancer.security.core.validate.code.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code.sms
 * @Description:
 * @date 2018/7/414:18
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor {


    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;
    /**
     * 短信验证码发送器
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;  //"mobile"
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        System.out.println(validateCode.getCode());
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
