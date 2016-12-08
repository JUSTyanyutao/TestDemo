package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Goods;
import com.mxep.model.goods.GoodsComment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao - 商品 评论
 */
public interface GoodsCommentDao extends BaseDao<GoodsComment, Integer> {

}
