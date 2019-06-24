package com.cancer.security.core.validate.code.repository;

import com.cancer.security.core.validate.code.ValidateCode;
import com.cancer.security.core.validate.code.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest; /**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate
 * @Description:
 * @date 2018/7/1117:41
 */
public interface ValiDateCodeRepository {

    void remove(ServletWebRequest request, ValidateCodeType codeType);

    <C extends ValidateCode> C get(ServletWebRequest request, ValidateCodeType codeType);

    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);
}
