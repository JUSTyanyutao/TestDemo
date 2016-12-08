package com.mxep.model.repositories.common;

import com.mxep.model.BaseDao;
import com.mxep.model.common.City;
import com.mxep.model.common.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Dao -  城市
 *
 */
public interface CityDao extends BaseDao<City, Integer> {


    /**
     * 根据 省份id 查找 城市
     * @param id
     * @return
     */
    @Query("select a from City a where a.pid = ?1")
    List<City> findCityByProvinceId(Integer id);


    /**
     *  查找所有的 城市
     * @return
     */
    @Query("select a from City a ")
    List<City> findCity();


    /**
     * 根据城市  名称 查找  城市
     * @param name
     * @return
     */
    @Query("select a from City a where a.name like %:name%")
    City getCityByName(@Param("name") String name);

    /**
     * 根据城市  名称 查找  城市  准确查找
     * @param name
     * @return
     */
    @Query("select a from City a where a.name = :name")
    City findCityByName(@Param("name") String name);



}
