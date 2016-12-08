package com.mxep.model.repositories.member;

import com.mxep.model.BaseDao;
import com.mxep.model.common.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Dao - 优惠券
 *
 * @author xingkong1221
 * @date 2015-12-21
 */
public interface CouponDao extends BaseDao<Coupon, Integer> {

    /**
     * 查询会员的优惠券
     *
     * @param memberId 会员编号
     * @param status 状态
     * @param pageable 分页信息
     * @return 会员的优惠券
     */
    @Query("select a from Coupon a where a.memberId = :memberId and a.status in :status and a.flag = 1")
    Page<Coupon> findCouponByMemberId(@Param("memberId") Integer memberId,
                                      @Param("status") List<Byte> status, Pageable pageable);

    /**
     * 更新已经过期的优惠券
     *
     * @param memberId 会员编号
     */
    @Modifying
    @Query("UPDATE Coupon a SET a.status = -2 WHERE a.memberId = ?1 AND a.expireTime < ?2 AND a.flag = 1")
    void updateExpiredCoupons(Integer memberId, Date today);

    /**
     * 查询可用优惠券数量
     *
     * @param memberId 会员编号
     * @return 可用优惠券数量
     */
    @Query("select count(a) from Coupon a where a.memberId = ?1 and a.status = 1 and a.flag = 1")
	Integer countEnableCoupons(Integer memberId);
}
