package com.mxep.model.goods;

import com.mxep.model.BaseEntity;
import com.mxep.model.member.Member;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
@Entity
@Where(clause = "flag = 1")
public class GoodsComment extends BaseEntity {


    /**
     *  图片
     */
    private String pics;

    /**
     *  评论
     */
    private String comment;

    /**
     *  商品  id
     */
    private Integer goodsId;

    /**
     *  商品
     */
    private Goods goods;

    /**
     *  会员 id
     */
    private Integer memberId;

    /**
     *  会员
     */
    private Member member;

    /**
     *  推荐
     */
    private byte recommend;

    /**
     *  商品 评分
     */
    private Integer goodsScore;

    /**
     *  场地评分
     */
    private Integer shopScore;

    /**
     *  技师评分
     */
    private Integer workerScore;

    /**
     *  审核 状态
     */
    private byte status;

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "goods_id")
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public byte getRecommend() {
        return recommend;
    }

    public void setRecommend(byte recommend) {
        this.recommend = recommend;
    }

    public Integer getGoodsScore() {
        return goodsScore;
    }

    public void setGoodsScore(Integer goodsScore) {
        this.goodsScore = goodsScore;
    }

    public Integer getShopScore() {
        return shopScore;
    }

    public void setShopScore(Integer shopScore) {
        this.shopScore = shopScore;
    }

    public Integer getWorkerScore() {
        return workerScore;
    }

    public void setWorkerScore(Integer workerScore) {
        this.workerScore = workerScore;
    }
}
