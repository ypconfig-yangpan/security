package com.cancer.security.browser.validate;

import com.cancer.security.core.validate.code.ValidateCode;
import com.cancer.security.core.validate.code.repository.ValiDateCodeRepository;
import com.cancer.security.core.validate.code.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code
 * @Description:  session 操作验证码的实现
 * @date 2018/7/1117:47
 */
@Component
public class SessionValiDateCodeRepository implements ValiDateCodeRepository {
    //  验证码放入session时的前缀
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    // 操作session的工具类
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType codeType) {
        sessionStrategy.setAttribute(request,getSessionKey(request,codeType),code);
    }


    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request,codeType));
    }

    @Override
    public <C extends ValidateCode> C get(ServletWebRequest request, ValidateCodeType codeType) {
       return (C)sessionStrategy.getAttribute(request, getSessionKey(request,codeType));
    }

    /**
     * 构建验证码放入session时的key
     *
     * @param request
     * @return
     */
    private String getSessionKey(ServletWebRequest request,ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }




}
