package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.FilmBrand;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public interface FilmBrandDao extends BaseDao<FilmBrand,Integer> {

    @Query("select a from FilmBrand a where a.status = 1 and a.flag = 1")
    List<FilmBrand> findAll();
}
