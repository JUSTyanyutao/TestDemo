package com.mxep.model.goods;

import com.mxep.model.BaseEntity;
import com.mxep.model.enums.EnumResaleType;
import com.mxep.model.member.Member;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity - 转卖
 *
 * @author xingkong1221
 * @since 2016-09-19
 */
@Entity
public class Resale extends BaseEntity {

    private static final long serialVersionUID = 2305617594002598726L;

    /**
     * 会员编号
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;

    /**
     * 商品编号
     */
    private Integer goodsId;

    /**
     * 商品
     */
    private Goods goods;

    /**
     * 出售方式
     *
     * @see EnumResaleType
     */
    private byte saleType;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 折扣系数
     */
    private BigDecimal discount;

    /**
     * 转卖价格
     */
    private BigDecimal price;

    /**
     * 转账平台
     *
     * @see com.mxep.model.enums.EnumTransferPlatform
     */
    private byte transferPlatform;

    /**
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String realname;

    /**
     * 状态
     *
     * @see com.mxep.model.enums.EnumResaleStatus
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
    @OneToOne(fetch = FetchType.LAZY)
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
     * 获取转卖类型
     *
     * @see EnumResaleType
     * @return 转卖类型
     */
    public byte getSaleType() {
        return saleType;
    }

    /**
     * 设置转卖类型
     *
     * @see EnumResaleType
     * @param saleType 转卖类型
     */
    public void setSaleType(byte saleType) {
        this.saleType = saleType;
    }

    /**
     * 获取商品原价
     *
     * @return 商品原价
     */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /**
     * 设置商品原价
     *
     * @param originalPrice 商品原价
     */
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * 获取折扣
     *
     * @return 折扣
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * 设置折扣
     *
     * @param discount 折扣
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * 获取商品售价
     *
     * @return 商品售价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品售价
     *
     * @param price 商品售价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取转账平台
     *
     * @see com.mxep.model.enums.EnumTransferPlatform
     * @return 转账平台
     */
    public byte getTransferPlatform() {
        return transferPlatform;
    }

    /**
     * 设置转账平台
     *
     * @see com.mxep.model.enums.EnumTransferPlatform
     * @param transferPlatform 转账平台
     */
    public void setTransferPlatform(byte transferPlatform) {
        this.transferPlatform = transferPlatform;
    }

    /**
     * 获取收款账户
     *
     * @return 收款账户
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置收款账户
     *
     * @param account 收款账户
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取姓名
     *
     * @return 姓名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置姓名
     *
     * @param realname 姓名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 获取转卖状态
     *
     * @return 转卖状态
     */
    public byte getStatus() {
        return status;
    }

    /**
     * 设置转卖状态
     *
     * @param status 转卖状态
     */
    public void setStatus(byte status) {
        this.status = status;
    }
}
