package com.qinshihuang.dao.cluster;

import com.qinshihuang.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by junshuaizhang1 on 2018/3/12.
 */
@Mapper
public interface CityDao {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("cityName") String cityName);
}
