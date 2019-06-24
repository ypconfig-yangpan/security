package com.cancer.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.exception
 * @Description:
 * @date 2018/7/215:34
 */
public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = 6991695216447387364L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
