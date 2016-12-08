package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.CarPart;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1 0001.
 */
public interface CarPartDao extends BaseDao<CarPart,Integer> {

    /**
     * 查找所有可用的 部位
     * @return
     */
    @Query("select a from CarPart a where a.status = 1")
    List<CarPart> findAll();
}
