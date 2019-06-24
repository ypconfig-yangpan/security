# s
security 一个根据springsecurity 封装的安全组件
```java
     <module>../cancer-security-api</module>
        <module>../cancer-security-browser</module>
        <module>../cancer-security-core</module>
        <module>../cancer-security-demo</module>
```

api 适用于手机端
browser 适用于浏览器

实现DetailService 加载用户信息

```java
@Service
public class MyUserDetailService implements UserDetailsService ,SocialUserDetailsService{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 表单登录用户名
        return getUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
        // 社交登录用户id
        return getUser(username);
    }

    private SocialUser getUser(String username) {
        return new SocialUser("yangpan",passwordEncoder.encode("123456"),
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
```

// 配置
social 社交信息

oauth2 集成oauth2
```java
#security.oauth2.client.client-id= yangpan
#security.oauth2.client.client-secret= yangpan

cancer.secutiry.oauth2.clinet=yangpan
cancer.secutiry.oauth2.secret=yangpan
cancer.secutiry.oauth2.scopes=all,read,write

spring.session.store-type = none

spring.redis.host=127.0.0.1
spring.redis.pool.max-active=8
spring.redis.pool.max-idle=8
spring.redis.pool.max-wait=1
spring.redis.pool.min-idle=0
spring.redis.port=6379

cancer.secutiry.browser.loginPage= /demoLogin.html
cancer.secutiry.browser.signUpUrl= /demo-signUp.html
cancer.secutiry.rememeberMe= 600
#cancer.secutiry.code.image.width= 70
#cancer.secutiry.code.image.height=25
#cancer.secutiry.code.image.length=6
#cancer.secutiry.code.image.expireIn=120
#cancer.secutiry.code.image.url=/user,/user/*
cancer.secutiry.code.sms.length=6
cancer.secutiry.code.sms.expireIn=120

#QQ
cancer.secutiry.social.qq.app-id=101472659 
cancer.secutiry.social.qq.app-Secret=f7506a0ab71ebc772826335dc651c09b
cancer.secutiry.social.qq.providerId=qq
cancer.secutiry.social.filterProcessesUrl=/login
#回调地址 http://yangshiqi.cf/login/qq

cancer.secutiry.social.weChat.app-id=wx6ad144e54af67d87
cancer.secutiry.social.weChat.app-Secret=66bb4566de776ac699ec1dbed0cc3dd1
cancer.secutiry.social.weChat.providerId=weChat
```
