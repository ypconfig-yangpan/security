package com.cancer.security.api.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.api.utils
 * @Description: redisUtils
 * @date 2018/7/1211:01
 */
//@Component
public  class  RedisUtils  {

    private static StringRedisTemplate stringRedis;

    public RedisUtils(StringRedisTemplate stringRedis) {
        this.stringRedis = stringRedis;
    }

//    @Resource
//    private  StringRedisTemplate stringRedisTemplate;

//    @PostConstruct
//    public void init() {
//        this.stringRedis = stringRedisTemplate;
//    }


    /**
     *
     * @param key
     * @param value
     * @return boolean
     */
    public static boolean set(String  key, String value){
        try {
            stringRedis.opsForValue().set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @param key
     * @param value
     * @param time 秒
     * @return
     */
    public static boolean set(String  key, String value,long time){
        try {
            stringRedis.opsForValue().set(key,value,time,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @Description: 获取缓存
     * @throws
     * @author yangpan
     */
    public static Object get(String key){
        return key==null?null:stringRedis.opsForValue().get(key);
    }
    /**
     * @Description:  删除缓存 一个值
     * @throws
     * @author yangpan
     */
    public boolean del(String  key, String value){
        try {
            stringRedis.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @Description: key 可以传一个值 或多个
     * @throws
     * @author yangpan
     */
    public static void del(String ... key){

        if(key!=null&&key.length>0){
            if(key.length==1){
                stringRedis.delete(key[0]);
            }else{
                stringRedis.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public static long getExpire(String key){

        return stringRedis.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     *
     * @param key
     * @return  设置过期时间
     */
    public static boolean expire(String  key, long time){

        if (time>0){
            Boolean expire = stringRedis.expire(key, time, TimeUnit.SECONDS);
            return expire;
        }
        return false;
    }

    /**
     * @Description:  递增 -------- key键 , delta要增加几(大于0)
     * @throws
     * @author yangpan
     */
    public static  long incr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return stringRedis.opsForValue().increment(key, delta);
    }

    /**
     * @Description: 递减--------key键 , delta要增加几(大于0)
     * @throws
     * @author yangpan
     */
    public static long decr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return stringRedis.opsForValue().increment(key, -delta);
    }



}
