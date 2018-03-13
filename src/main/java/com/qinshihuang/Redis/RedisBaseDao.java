package com.qinshihuang.Redis;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by junshuaizhang1 on 2018/3/13.
 */
@Repository
public class RedisBaseDao {

    @Resource(name="redisTemplate")
    protected ValueOperations<String, String> valueOperations;

    public void addValue(String key, String value){
        valueOperations.set(key, value);
    }

    public String getValue(String key){
        return valueOperations.get(key);
    }
}
