package com.qinshihuang.dao.master;

import com.qinshihuang.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by junshuaizhang1 on 2018/3/12.
 */
@Mapper
public interface UserDao {
    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    User findByName(@Param("userName") String userName);
}
