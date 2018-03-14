package com.qinshihuang.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

/**
 * Created by junshuaizhang1 on 2018/3/13.
 */
@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig  extends CachingConfigurerSupport {
    @Autowired
    private Environment env;
    /*定义缓存数据 key 生成策略的bean
    包名+类名+方法名+所有参数
    */
    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };

    }
    /***
     * 缓存管理器
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate){
        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(env.getProperty("redis.host").trim());
        jedisConnectionFactory.setPort(Integer.parseInt(env.getProperty("redis.port").trim()));
        jedisConnectionFactory.setPassword(env.getProperty("redis.password").trim());
        jedisConnectionFactory.setDatabase(Integer.parseInt(env.getProperty("redis.database").trim()));

        jedisConnectionFactory.setUsePool(true);
        jedisPoolConfig.setMaxIdle(Integer.parseInt(env.getProperty("redis.maxIdle").trim()));
        jedisPoolConfig.setMinIdle(Integer.parseInt(env.getProperty("redis.minIdle").trim()));
        jedisPoolConfig.setMaxTotal(Integer.parseInt(env.getProperty("redis.maxTotal").trim()));
        jedisPoolConfig.setMaxWaitMillis(Integer.parseInt(env.getProperty("redis.maxWaitMillis").trim()));
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String ,String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
