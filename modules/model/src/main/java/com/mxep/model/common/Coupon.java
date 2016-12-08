package com.mxep.model.common;

import com.mxep.model.BaseEntity;
import com.mxep.model.member.Member;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity - 优惠券
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
public class Coupon extends BaseEntity {

    private static final long serialVersionUID = 8095589941672666883L;

    /**
     * 标题
     */
    private String title;

    /**
     * 说明
     */
    private String desc;

    /**
     * 状态
     *
     * -2:过期  -1:无效  0:待激活  1:已激活  2:已使用
     *
     * @see com.mxep.model.enums.EnumCouponStatus
     */
    private byte status;

    /**
     * 面额
     */
    private BigDecimal denomination;

    /**
     * 会员id
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;

    /**
     * 激活时间
     */
    private Date activationTime;

    /**
     * 使用时间
     */
    private Date usedTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 限制金额
     */
    private BigDecimal restrictionMoney;

    /**
     * 获取标题
     *
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取说明
     *
     * @return 说明
     */
    @Column(name = "`desc`")
    public String getDesc() {
        return desc;
    }

    /**
     * 设置说明
     *
     * @param desc 说明
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取状态 （-2:过期  -1:无效  0:待激活  1:已激活  2:已使用）
     *
     * @see com.mxep.model.enums.EnumCouponStatus
     * @return 状态
     */
    public byte getStatus() {
        return status;
    }

    /**
     * 设置状态 (-2:过期  -1:无效  0:待激活  1:已激活  2:已使用)
     *
     * @see com.mxep.model.enums.EnumCouponStatus
     * @param status 状态
     */
    public void setStatus(byte status) {
        this.status = status;
    }

    /**
     * 获取面额
     *
     * @return 面额
     */
    public BigDecimal getDenomination() {
        return denomination;
    }

    /**
     * 设置面额
     *
     * @param denomination 面额
     */
    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    /**
     * 获取会员id
     *
     * @return 会员id
     */
    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 设置会员id
     *
     * @param memberId 会员id
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取会员
     *
     * @return 会员
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    public Member getMember() {
        return member;
    }

    /**
     * 设置会员
     *
     * @param member 会员
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * 获取激活时间
     *
     * @return 激活时间
     */
    @Column(name = "activation_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getActivationTime() {
        return activationTime;
    }

    /**
     * 设置激活时间
     *
     * @param activationTime 激活时间
     */
    public void setActivationTime(Date activationTime) {
        this.activationTime = activationTime;
    }

    /**
     * 获取使用时间
     *
     * @return 使用时间
     */
    @Column(name = "used_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUsedTime() {
        return usedTime;
    }

    /**
     * 获取使用时间
     *
     * @param usedTime 使用时间
     */
    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    /**
     * 获取开始时间
     *
     * @return 开始时间
     */
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取过期时间
     *
     * @return 过期时间
     */
    @Column(name = "expire_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置过期时间
     *
     * @param expireTime 过期时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 获取限制金额
     *
     * @return 限制金额
     */
    @Column(name = "restriction_money")
    public BigDecimal getRestrictionMoney() {
        return restrictionMoney;
    }

    /**
     * 设置限制金额
     *
     * @param restrictionMoney 限制金额
     */
    public void setRestrictionMoney(BigDecimal restrictionMoney) {
        this.restrictionMoney = restrictionMoney;
    }

}
