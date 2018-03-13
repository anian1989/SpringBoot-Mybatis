package com.qinshihuang.Service.impl;

import com.qinshihuang.Service.UserService;
import com.qinshihuang.dao.cluster.CityDao;
import com.qinshihuang.dao.master.UserDao;
import com.qinshihuang.domain.City;
import com.qinshihuang.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by junshuaizhang1 on 2018/3/12.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao cityDao; // 从数据源

    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("温岭市");
        user.setCity(city);
        return user;
    }
}
