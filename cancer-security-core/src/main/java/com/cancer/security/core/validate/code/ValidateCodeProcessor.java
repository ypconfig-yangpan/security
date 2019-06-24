package com.cancer.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.controller
 * @Description:  验证码处理器
 * @date 2018/7/414:13
 */
public interface ValidateCodeProcessor {



    /**
     * 创建校验码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param request
     * @throws Exception
     */
    void validate(ServletWebRequest request) throws Exception;
}
