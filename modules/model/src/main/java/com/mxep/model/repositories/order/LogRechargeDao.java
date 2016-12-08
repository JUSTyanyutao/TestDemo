package com.mxep.model.repositories.order;

import com.mxep.model.BaseDao;
import com.mxep.model.log.LogRecharge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Dao - 充值日志
 */
public interface LogRechargeDao extends BaseDao<LogRecharge, Integer> {

    /**
     * 分页查询充值日志
     *
     * @param memberId 会员编号
     * @param pageable 分页
     * @return 分页结果
     */
    @Query("select a from LogRecharge a where a.flag = 1 and a.memberId = ?1")
    Page<LogRecharge> findPageByMemberId(Integer memberId, Pageable pageable);

}
