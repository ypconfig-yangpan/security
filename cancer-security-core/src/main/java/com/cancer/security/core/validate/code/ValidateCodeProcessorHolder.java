package com.cancer.security.core.validate.code;

import com.cancer.security.core.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.controller
 * @Description:  验证码处理器 持有者
 * @date 2018/7/414:12
 */
@Component
public class ValidateCodeProcessorHolder {


    /**
     * @ConditionalOnMissingBean(name = "imageValidateCodeGenerator") 图片生成器注册
     * @Component("smsValidateCodeGenerator")    // 短信验证码生成器注册
     * bean注入后string为该bean的id。
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
        ValidateCodeProcessor processor = findValidateCodeProcessor(type.toString().toLowerCase());
        return processor;
    }

    /**
     * 查找验证代码
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        // sms +  ValidateCodeProcessor
        // image + ValidateCodeProcessor
        // 获取的名字 去map集合 得到对象
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
