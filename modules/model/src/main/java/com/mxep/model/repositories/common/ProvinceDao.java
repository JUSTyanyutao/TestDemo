package com.mxep.model.repositories.common;

import com.mxep.model.BaseDao;
import com.mxep.model.common.Faq;
import com.mxep.model.common.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Dao -  省
 *
 */
public interface ProvinceDao extends BaseDao<Province, Integer> {

    /**
     * 查找所有的 省
     * @return
     */
    @Query("select a from Province a ")
    List<Province> findAllProvince();

    /**
     * 根据省名称 查找  省
     * @param name
     * @return
     */
    @Query("select a from Province a where a.name = :name")
    Province getProvinceByName(@Param("name") String name);


}
