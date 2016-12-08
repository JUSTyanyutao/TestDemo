package com.mxep.model.goods;

import com.mxep.model.BaseSortedEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entity - 商品 详情
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
@Table
public class GoodsInfo implements Serializable {

    private static final long serialVersionUID = 1200613867608461346L;

    private Integer id;

    private Integer goodsId;

    private Goods goods;

    private Integer salesNum = 0;

    private Integer commentNum = 0;

    private Integer shareNum = 0;

    /**
     *  详情
     */
    private String detailContent;

    /**
     *  规格
     */
    private String specContent;

    /**
     *  服务流程
     */
    private String serviceProcessContent;


    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "goods_id")
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public String getSpecContent() {
        return specContent;
    }

    public void setSpecContent(String specContent) {
        this.specContent = specContent;
    }

    public String getServiceProcessContent() {
        return serviceProcessContent;
    }

    public void setServiceProcessContent(String serviceProcessContent) {
        this.serviceProcessContent = serviceProcessContent;
    }
}
