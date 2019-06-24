package com.cancer.security.demo.controller;


import com.cancer.security.demo.entity.User;
import com.cancer.security.demo.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController

public class UserController {


    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/user/getUser")
    public Object  getUserByUsername(Authentication user,HttpServletRequest request) throws UnsupportedEncodingException {
        //User user =  userServiceImpl.getUserByUsername();
//        User user =  new User("1","yangpan","123456");
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        Claims claims = Jwts.parser().setSigningKey("yangpan".getBytes("utf-8")).parseClaimsJws(token).getBody();
        String company = (String)claims.get("company");
        System.out.println(company);
        return user;
    }


    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    /**
     * @Description:
     * @throws
     * @author yangpan
     */
    @RequestMapping("/user/regist")
    public void  register(User user,HttpServletRequest request) {
        // 不管是注册还是绑定用户 都会获取 用户一个唯一标识  userId
        providerSignInUtils.doPostSignUp(user.getUsername(),new ServletWebRequest(request));
        //userServiceImpl.register(user);
        System.out.println("注册成功");
    }
}
