package com.cancer.security.core.config;

import com.cancer.security.core.properties.SecurityProperties;
import com.cancer.security.core.validate.code.ValidateCodeGenerator;
import com.cancer.security.core.validate.code.image.ImageCodeGenerator;
import com.cancer.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.cancer.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.config
 * @Description:
 * @date 2018/7/314:00
 */
@Configuration
public class ValidateBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 可配置的 图形验证码
     * @return
     */
//    @Bean(name = "imageValidateCodeGenerator")
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        ValidateCodeGenerator imageCodeGenerate = new ImageCodeGenerator(securityProperties);
        return imageCodeGenerate;
    }

    /**
     * 可配置的短信发送
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SmsCodeSender defaultSMSCodeSender(){
        DefaultSmsCodeSender defaultSMSCodeSender = new DefaultSmsCodeSender();
        return defaultSMSCodeSender;
    }
}
