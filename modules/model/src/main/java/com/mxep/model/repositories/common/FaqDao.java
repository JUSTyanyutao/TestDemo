package com.mxep.model.repositories.common;

import com.mxep.model.BaseDao;
import com.mxep.model.common.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Dao -  常见问题
 *
 * @author zlj
 * @date 2015-12-23
 */
public interface FaqDao extends BaseDao<Faq, Integer> {

    /**
     * 分页查询常见问题
     *
     * @param type 类型：1常见问题 2积分说明
     * @param isDisplay 显示标识位： 1显示 0不显示
     * @param pageable 分页
     * @return 分页结果
     */
    @Query("select a from Faq a where a.type = ?1 and a.isDisplay = ?2 and a.flag = 1")
    Page<Faq> findFaqPage(int type, byte isDisplay, Pageable pageable);

}
