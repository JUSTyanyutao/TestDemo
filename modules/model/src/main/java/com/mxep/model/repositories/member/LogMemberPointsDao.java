package com.mxep.model.repositories.member;

import com.mxep.model.BaseDao;
import com.mxep.model.log.LogMemberPoints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao - 会员积分记录
 */
public interface LogMemberPointsDao extends BaseDao<LogMemberPoints, Integer> {

    /**
     * 分页查询会员积分记录
     *
     * @param memberId 会员编号
     * @param type 类型：1收入 2支付
     * @param pageable 分页
     * @return 会员积分记录
     */
    @Query("select a from LogMemberPoints a where a.memberId = ?1 and a.type = ?2 and a.flag = 1")
    Page<LogMemberPoints> pageByMemberIdAndType(Integer memberId, byte type, Pageable pageable);

    @Query(value = "from LogMemberPoints a where a.memberId = ?1 order by a.id desc ")
    List<LogMemberPoints> findMemberPointsList(int memberId);


}
