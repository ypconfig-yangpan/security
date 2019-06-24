package com.cancer.security.core.validate.code;

import com.cancer.security.core.properties.SecurityConstants;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.controller
 * @Description: 验证码类型
 * @date 2018/7/414:16
 */
public enum ValidateCodeType {
    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;  // "smsCode"
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;  // "imageCode"
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();
}
