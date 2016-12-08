package com.mxep.model.common;

import com.mxep.model.BaseEntity;
import com.mxep.model.member.Member;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity - 签到
 */
@Entity
public class SignIn extends BaseEntity {

    private static final long serialVersionUID = -7406738104526738507L;

    /**
     * 签到日期
     */
    private Date signInDate;

    /**
     * 连续签到次数
     */
    private Integer continuedTimes;

    /**
     * 会员id
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;

    /**
     * 获取签到日期
     *
     * @return 签到日期
     */
    @Column(name = "signin_date")
    @Temporal(TemporalType.DATE)
    public Date getSignInDate() {
        return signInDate;
    }

    /**
     * 设置签到日期
     *
     * @param signInDate 签到日期
     */
    public void setSignInDate(Date signInDate) {
        this.signInDate = signInDate;
    }

    /**
     * 获取连续签到次数
     *
     * @return 连续签到次数
     */
    @Column(name = "continued_times")
    public Integer getContinuedTimes() {
        return continuedTimes;
    }

    /**
     * 设置连续签到次数
     *
     * @param continuedTimes 连续签到次数
     */
    public void setContinuedTimes(Integer continuedTimes) {
        this.continuedTimes = continuedTimes;
    }

    /**
     * 获取会员编号
     *
     * @return 会员编号
     */
    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 设置会员编号
     *
     * @param memberId 会员编号
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
}
