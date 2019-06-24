package com.cancer.security.api.config;

import com.cancer.security.api.utils.RedisUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author yangpan
 * @Title: B2B_Management
 * @Package com.citymine.appuser.config
 * @Description:
 * @date 2018/5/3117:28
 */
@Configuration
@EnableCaching
public class RedisCacheConfig implements ApplicationContextAware, InitializingBean {
    @Autowired
    private StringRedisTemplate stringRedis;
    @Override
    public void afterPropertiesSet() throws Exception {
        RedisUtils redisUtils = new RedisUtils(stringRedis);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.stringRedis = (StringRedisTemplate) applicationContext.getBean("stringRedisTemplate");
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate){
        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String,String>();
        RedisSerializer stringSerializer = new StringRedisSerializer();// Long类型不可以会出现异常信息;
        // key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
        // 所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
        // 或者JdkSerializationRedisSerializer序列化方式;
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }


}
