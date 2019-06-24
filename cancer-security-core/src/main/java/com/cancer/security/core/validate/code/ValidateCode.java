package com.cancer.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code
 * @Description: 验证码父类
 * @date 2018/7/316:27
 */
public class ValidateCode {

    private String code;
    private LocalDateTime exprieTime;


    public ValidateCode(String code, int exprieIn) {
        this.code = code;
        this.exprieTime = LocalDateTime.now().plusSeconds(exprieIn);
    }
    public ValidateCode(String code, LocalDateTime exprieTime) {
        this.code = code;
        this.exprieTime = exprieTime;
    }

    /**
     * 是否过期
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(exprieTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExprieTime() {
        return exprieTime;
    }

    public void setExprieTime(LocalDateTime exprieTime) {
        this.exprieTime = exprieTime;
    }
}
