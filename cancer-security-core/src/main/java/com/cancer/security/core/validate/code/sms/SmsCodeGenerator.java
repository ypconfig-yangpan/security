package com.cancer.security.core.validate.code.sms;

import com.cancer.security.core.properties.SecurityProperties;
import com.cancer.security.core.validate.code.ValidateCode;
import com.cancer.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code.sms
 * @Description:
 * @date 2018/7/316:50
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 生成短信验证码
     * @param request
     * @return
     */
    @Override
    public ValidateCode generate(ServletWebRequest request) {

        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());

    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
