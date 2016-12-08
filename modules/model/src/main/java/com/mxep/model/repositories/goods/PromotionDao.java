package com.mxep.model.repositories.goods;

import java.util.List;

import com.mxep.model.BaseDao;
import org.springframework.data.jpa.repository.Query;

import com.mxep.model.common.Promotion;

/**
 * Dao 
 */
public interface PromotionDao extends BaseDao<Promotion, Integer> {

	
	@Query("select a from Promotion a where now() between a.startTime and a.endTime and a.status = 1 and a.promotionType = ?1 order by a.sort desc")
	List<Promotion> findPromotionByType(Integer promotionType);
}
