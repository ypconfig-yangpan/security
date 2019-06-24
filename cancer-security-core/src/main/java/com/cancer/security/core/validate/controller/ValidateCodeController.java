package com.cancer.security.core.validate.controller;

import com.cancer.security.core.properties.SecurityConstants;
import com.cancer.security.core.validate.code.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code
 * @Description: 验证码controller
 * @date 2018/7/214:55
 */
@RestController
public class ValidateCodeController {


    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     *  根据type 类型 处理不同的验证码请求
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/{type}")
    public void createImageCode(HttpServletRequest request,
                                HttpServletResponse response, @PathVariable String  type) throws Exception {

        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request,response));
    }

//
//    @GetMapping("/code/sms")
//    public void createSMSCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
//
//        ValidateCode smsCode = sMSCodeGenerate.generate(new  ServletWebRequest(request));
//        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KET_PREFIX+"SMS",smsCode); // 放入session
//        System.out.printf("后台生成的短信验证码 %s",smsCode.getCode());
//        // 发送短信
//        String mobile = ServletRequestUtils.getStringParameter(request, "mobile");
//        smsCodeSender.send(mobile,"您已经注册我公司");
//
//    }


}
