package com.mxep.model.flash;

import com.mxep.model.BaseEntity;
import com.mxep.model.member.Member;

import javax.persistence.*;

/**
 * Entity -  秒杀预约
 *
 * @author xingkong1221
 * @since 2016-08-28
 */
@Entity
public class FlashReservation extends BaseEntity {

    private static final long serialVersionUID = 2728070939833990888L;

    /**
     * 获取会员编号
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;

    /**
     * 秒杀商品id
     */
    private Integer flashGoodsId;

    /**
     * 秒杀商品
     */
    private FlashGoods flashGoods;

    /**
     * 状态 (0:待使用  1:已使用  2:已退还)
     *
     *
     * @see com.mxep.model.enums.EnumReservationStatus
     */
    private byte status;

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
     * 获取秒杀商品编号
     *
     * @return 秒杀商品编号
     */
    @Column(name = "flash_goods_id")
    public Integer getFlashGoodsId() {
        return flashGoodsId;
    }

    /**
     * 设置秒杀商品编号
     *
     * @param flashGoodsId 秒杀商品编号
     */
    public void setFlashGoodsId(Integer flashGoodsId) {
        this.flashGoodsId = flashGoodsId;
    }

    /**
     * 获取秒杀商品
     *
     * @return 秒杀商品
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flash_goods_id", insertable = false, updatable = false)
    public FlashGoods getFlashGoods() {
        return flashGoods;
    }

    /**
     * 设置秒杀商品
     *
     * @param flashGoods 秒杀商品
     */
    public void setFlashGoods(FlashGoods flashGoods) {
        this.flashGoods = flashGoods;
    }

    /**
     * 获取状态
     *
     * @see com.mxep.model.enums.EnumReservationStatus
     * @return 状态
     */
    public byte getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     *  @see com.mxep.model.enums.EnumReservationStatus
     * @param status 状态
     */
    public void setStatus(byte status) {
        this.status = status;
    }
}
