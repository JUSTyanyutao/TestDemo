package com.mxep.model.common;

import com.mxep.model.BaseEntity;
import com.mxep.model.member.Member;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Entity - 购物车
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
@Where(clause = "flag=1")
public class ShoppingCart extends BaseEntity {

    private static final long serialVersionUID = 2636394952765732725L;

    /**
     * 会员编号
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;


    /**
     * 数量
     */
    private Integer quantity;

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

    /**
     * 获取数量
     *
     * @return 数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置数量
     *
     * @param quantity 数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
