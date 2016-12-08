package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Goods;
import com.mxep.model.goods.GoodsInfo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao - 商品
 */
public interface GoodsInfoDao extends BaseDao<GoodsInfo, Integer> {
}
