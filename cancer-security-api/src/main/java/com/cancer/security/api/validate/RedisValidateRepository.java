package com.cancer.security.api.validate;

import com.cancer.security.api.utils.RedisUtils;
import com.cancer.security.core.exception.ValidateCodeException;
import com.cancer.security.core.validate.code.ValidateCode;
import com.cancer.security.core.validate.code.ValidateCodeType;
import com.cancer.security.core.validate.code.repository.ValiDateCodeRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.api.validate
 * @Description:  redis 的 验证码存储器
 * @date 2018/7/1210:41
 */
@ConditionalOnProperty(prefix = "spring.redis" ,name = "host" )
@Component
public class RedisValidateRepository implements ValiDateCodeRepository {

    private  String REDIS_KEY_PREFIX = "REDIS_KEY_FOR_CODE_";
    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        RedisUtils.del(createRedisKey(request,codeType));
    }

    @Override
    public <C extends ValidateCode> C get(ServletWebRequest request, ValidateCodeType codeType) {
        Object code = RedisUtils.get(createRedisKey(request, codeType));
        return (C) code;
    }

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType codeType) {
        RedisUtils.set(createRedisKey(request,codeType),code.getCode(), 3000);
    }

    String  createRedisKey(ServletWebRequest request, ValidateCodeType codeType){
        String deviceId = request.getHeader("deviceId");//设备id
        if (StringUtils.isBlank(deviceId)){
            throw new ValidateCodeException("设备id不存在");
        }
        String key = REDIS_KEY_PREFIX+codeType.toString().toLowerCase()+deviceId;
        return key;
    }
}
