package com.qinshihuang.Controller;

import com.qinshihuang.Cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by junshuaizhang1 on 2018/3/14.
 */
@RestController("concurrenmapcache.cacheController")
@RequestMapping(value = "/concurrenmapcache/cache")
public class CacheController {
    @Autowired
    private CacheService cacheService;

    /**
     * 查询方法
     */
    @RequestMapping(value = "/getByCache", method = RequestMethod.GET)
    public String getByCache() {
        Long startTime = System.currentTimeMillis();
        String timestamp = this.cacheService.getByCache();
        Long endTime = System.currentTimeMillis();
//        System.out.println(789465123);
        System.out.println("耗时: " + (endTime - startTime));
        return timestamp+"";
    }

    /**
     * 保存方法
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save() {
        this.cacheService.save();
    }

    /**
     * 删除方法
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete() {
        this.cacheService.delete();
    }
}