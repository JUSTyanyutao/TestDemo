package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Goods;
import com.mxep.model.goods.GoodsShow;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao - 大家说
 */
public interface GoodsShowDao extends BaseDao<GoodsShow, Integer> {
}
