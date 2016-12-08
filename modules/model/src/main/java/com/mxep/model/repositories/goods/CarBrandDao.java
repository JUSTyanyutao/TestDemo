package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.CarBrand;
import com.mxep.model.goods.Goods;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao -  车的品牌
 */
public interface CarBrandDao extends BaseDao<CarBrand, Integer> {

    /**
     * 查找 所有 可用的  车的品牌
     * @return
     */
    @Query("select a from CarBrand a where a.flag = 1 and a.status = 1")
    List<CarBrand> findCarBrand();

}
