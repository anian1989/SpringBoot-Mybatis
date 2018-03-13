package com.qinshihuang.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by junshuaizhang1 on 2018/3/13.
 */
@Repository
public class ValueRedisDao {

    @Autowired
    public RedisBaseDao redisBaseDao;

    private String getKey(){
        return "param";
    }

    public void save(String param){
        this.redisBaseDao.addValue(this.getKey(), param);
    }

    public String getParam(){
        return this.redisBaseDao.getValue(this.getKey());
    }
}
