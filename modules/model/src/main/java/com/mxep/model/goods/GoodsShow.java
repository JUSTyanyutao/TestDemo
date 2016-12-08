package com.mxep.model.goods;

import com.mxep.model.BaseEntity;
import com.mxep.model.member.Member;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/11/9 0009.
 *  大家说
 */
@Entity
public class GoodsShow extends BaseEntity {

    /**
     *  1商品评价  2服务评价
     */
    private Integer type;

    /**
     *  商品 ID
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
     *  技师 id
     */
    private Integer workerId;

    /**
     *  技师
     */
    private Member worker;

    /**
     *  评论
     */
    private String comment;

    /**
     *  图片
     */
    private String pics;

    /**
     *  是否置顶  推荐置顶 0不置顶  1置顶
     */
    private Integer recommend;

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
     *  审核 状态  审核 0待审核 1通过 2 驳回
     */
    private byte status;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    @Column(name = "worker_id")
    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Member getWorker() {
        return worker;
    }

    public void setWorker(Member worker) {
        this.worker = worker;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
