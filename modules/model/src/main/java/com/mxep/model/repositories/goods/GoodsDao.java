package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Goods;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao - 商品
 */
public interface GoodsDao extends BaseDao<Goods, Integer> {

    @Query("select a from Goods a where a.flag = 1")
    List<Goods> findAllGoods();

    @Query("select a from Goods a where a.flag = 1 and a.categoryId = 6 or a.categoryId = 5")
    List<Goods> findServiceGoods();

}
