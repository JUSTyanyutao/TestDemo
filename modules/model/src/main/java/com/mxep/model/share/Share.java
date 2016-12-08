package com.mxep.model.share;

import com.mxep.model.BaseSortedEntity;
import com.mxep.model.goods.Goods;
import com.mxep.model.member.Member;
import com.mxep.model.order.Order;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Entity - 晒单
 *
 * @author xingkong1221
 * @since 2016-08-29
 */
@Entity
@Where(clause = "flag=1")
public class Share extends BaseSortedEntity {

    private static final long serialVersionUID = -807148354699135232L;


    /**
     * 商品编号
     */
    private Integer goodsId;

    /**
     * 商品
     */
    private Goods goods;

    /**
     * 会员编号
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;

    /**
     * 订单编号
     */
    private Integer orderId;

    /**
     * 订单信息
     */
    private Order order;

    /**
     * 描述
     */
    private String desc;

    /**
     * 图片
     */
    private String pic;

    /**
     * 审核状态
     * @see com.mxep.model.enums.EnumShareCheck
     */
    private byte checkStatus;

    /**
     * 反馈内容
     */
    private String comment;

    /**
     * 获取商品编号
     *
     * @return 商品编号
     */
    @Column(name = "goods_id")
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品编号
     *
     * @param goodsId 商品编号
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品
     *
     * @return 商品
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id", insertable = false, updatable = false)
    public Goods getGoods() {
        return goods;
    }

    /**
     * 设置商品
     *
     * @param goods 商品
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
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

    /**
     * 获取订单编号
     *
     * @return 订单编号
     */
    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编号
     *
     * @param orderId 订单编号
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单信息
     *
     * @return 订单信息
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    public Order getOrder() {
        return order;
    }

    /**
     * 订单编号信息
     *
     * @param order 编号信息
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * 获取描述
     *
     * @return 描述
     */
    @Column(name = "`desc`")
    public String getDesc() {
        return desc;
    }

    /**
     * 设置描述
     *
     * @param desc 描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取图片
     *
     * @return 图片
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置图片
     *
     * @param pic 图片
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 获取审核状态
     * @return checkStatus 审核状态
     */
    public byte getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置审核状态
     *
     * @param checkStatus 审核状态
     */
    public void setCheckStatus(byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * 获取反馈内容
     * @return
     */
    public String getComment() {
        return comment;
    }


    /**
     * 设置反馈内容
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
