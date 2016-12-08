package com.mxep.model.repositories.base;

import java.util.List;

import com.mxep.model.BaseDao;
import com.mxep.model.common.Carousel;
import org.springframework.data.jpa.repository.Query;

/**
 * Dao - 轮播图
 */
public interface CarouselDao extends BaseDao<Carousel, Integer> {

	/**
	 * 根据平台类型查询轮播图
	 *
	 * @param platform 平台
	 * @return 轮播图列表
	 */
	@Query("SELECT a FROM Carousel a where a.platform = ?1 and a.flag = 1 and a.isDisplay = 1 order by a.sort desc")
	List<Carousel> findByPlatform(byte platform);
}
