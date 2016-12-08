package com.mxep.model.repositories.common;

import com.mxep.model.BaseDao;
import com.mxep.model.common.City;
import com.mxep.model.common.CityTemplate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Dao -  城市 模版
 *
 */
public interface CityTemplateDao extends BaseDao<CityTemplate, Integer> {

    /**
     * 查找全部的 城市模版
     * @return
     */
    @Query("select a from CityTemplate a where a.status = 1")
    List<CityTemplate> findAll();

}
