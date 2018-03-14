package com.qinshihuang.Cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by junshuaizhang1 on 2018/3/13.
 */
@Service("concurrenmapcache.cacheService")
public class CacheService {
    //@Cacheable 在方法执行前 Spring 先查看缓存中是否有数据，
    // 如果有数据，则直接返回缓存数据；若没有数据，调用方法并将方法返回值放进缓存。
    // 有两个重要的值， value，返回的内容将存储在 value 定义的缓存的名字对象中。
    // key，如果不指定将使用默认的 KeyGenerator 生成。
    @Cacheable(value = "concurrenmapcache",keyGenerator="wiselyKeyGenerator")//缓存名称为 concurrenmapcache
    public String getByCache() {
            try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
    }

    /**
     * @CachePut 与 @Cacheable 类似，但是它无论什么情况，都会将方法的返回值放到缓存中, 主要用于数据新增和修改方法。
     * @return
     */
    @CachePut(value = "concurrenmapcache")
    public String save() {
        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        System.out.println("进行缓存：" + timestamp);
        return String.valueOf(timestamp);
    }

    /**
     * @CacheEvict 将一条或多条数据从缓存中删除, 主要用于删除方法，用来从缓存中移除相应数据。
     */
    @CacheEvict(value = "concurrenmapcache")
    public void delete() {
        System.out.println("删除缓存");
    }
}
