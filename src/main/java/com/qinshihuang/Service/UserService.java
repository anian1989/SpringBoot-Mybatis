package com.qinshihuang.Service;

import com.qinshihuang.domain.User;

/**
 * Created by junshuaizhang1 on 2018/3/12.
 */
public interface UserService {
    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param userName
     * @return
     */
    User findByName(String userName);
}
