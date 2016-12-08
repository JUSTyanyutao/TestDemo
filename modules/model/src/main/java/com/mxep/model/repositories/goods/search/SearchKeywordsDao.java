package com.mxep.model.repositories.goods.search;


import com.mxep.model.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.mxep.model.search.SearchKeywords;

import java.util.List;

/**
 * Dao - 搜索关键字
 */
public interface SearchKeywordsDao extends BaseDao<SearchKeywords, Integer> {

    /**
     * 分页查询搜索关键字
     *
     * @param pageable 分页参数
     * @return 分页结果
     */
	@Query("SELECT a FROM SearchKeywords a WHERE a.flag = 1 and a.isDisplay = 1")
	Page<SearchKeywords> findPage(Pageable pageable);

	/**
	 * 查询搜素关键字列表
	 *
	 * @return 搜素关键字列表
	 */
	@Query("SELECT a FROM SearchKeywords a WHERE a.flag = 1 and a.isDisplay = 1 ORDER BY a.sort, a.count DESC")
	List<SearchKeywords> findList();

	/**
	 * 根据关键字查询
	 *
	 * @param keywords 关键字
	 * @return 查询结果
	 */
	@Query("SELECT a FROM SearchKeywords a WHERE a.flag = 1 AND a.keywords = ?1")
	SearchKeywords findByKeywords(String keywords);
}
