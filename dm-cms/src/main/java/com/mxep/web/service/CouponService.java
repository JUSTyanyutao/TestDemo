package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.Coupon;
import com.mxep.model.enums.EnumCouponStatus;
import com.mxep.model.member.Member;
import com.mxep.model.repositories.member.CouponDao;
import com.mxep.model.repositories.member.MemberDao;
import com.mxep.web.common.exception.WebRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Service - 优惠券
 *
 * @author xingkong1221
 * @since  2015-12-22
 */
@Service
public class CouponService extends BaseService<Coupon, Integer> {

    private CouponDao couponDao;

    @Resource
    private MemberDao memberDao;

    @Override
    @Resource
    public void setBaseDao(BaseDao<Coupon, Integer> baseDao) {
        this.baseDao = baseDao;
        couponDao = (CouponDao) baseDao;
    }

    /**
     * 创建优惠券
     *
     * @param memberId 会员编号
     * @param denomination 面额
     * @param restrictionMoney 限制金额
     * @param title 名称
     * @param desc 描述说明
     * @param expireTime 过期日期
     * @param status 状态
     * @return 优惠券
     */
    @Transactional
    public Coupon createCoupon(Integer memberId, BigDecimal denomination, BigDecimal restrictionMoney, String title,
                               String desc, Date expireTime, EnumCouponStatus status) {

        // 检查会员信息
        Member member = null;
        if (memberId != null && memberId > 0) {
            member = memberDao.findOne(memberId);
        }
        if (member == null) {
            throw new WebRequestException("会员信息不存在");
        }

        // 检查面额和限制金额
        if (denomination == null || denomination.compareTo(BigDecimal.ZERO) <= 0) {
            throw new WebRequestException("优惠券面额必须大于0元");
        }
        if (restrictionMoney == null || restrictionMoney.compareTo(BigDecimal.ZERO) <= 0) {
            throw new WebRequestException("限制金额必须大于0元");
        }
        if (denomination.compareTo(restrictionMoney) > 0) {
            throw new WebRequestException("限制金额必须大于优惠券的面额");
        }

        // 检查标题和描述
        if (StringUtils.isBlank(title) || StringUtils.isBlank(desc)) {
            throw new WebRequestException("优惠券名称和描述说明不能为空");
        }

        // 检查过期时间
        if (expireTime == null || expireTime.compareTo(new Date()) <= 0) {
            throw new WebRequestException("过期时间不能早于当前时间");
        }

        Coupon coupon = new Coupon();
        coupon.setExpireTime(expireTime);
        coupon.setTitle(title);
        coupon.setDesc(desc);
        coupon.setDenomination(denomination);
        coupon.setMember(member);
        coupon.setRestrictionMoney(restrictionMoney);
        coupon.setStatus(status.value);

        return couponDao.save(coupon);
    }
}
