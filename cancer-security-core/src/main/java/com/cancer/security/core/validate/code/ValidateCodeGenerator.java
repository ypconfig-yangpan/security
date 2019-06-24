package com.cancer.security.core.validate.code;

import com.cancer.security.core.validate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code
 * @Description: 验证码生成器
 * @date 2018/7/311:27
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
