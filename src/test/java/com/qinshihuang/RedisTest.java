package com.qinshihuang;

import com.qinshihuang.Redis.ValueRedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by junshuaizhang1 on 2018/3/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DemoApplication.class)
public class RedisTest {

    @Autowired
    private ValueRedisDao valueRedisDao;

    @Test
    public void test() throws Exception {
        this.valueRedisDao.save("LiangGzone");
        System.out.println(this.valueRedisDao.getParam());
    }
}

