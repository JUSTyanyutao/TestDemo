package com.mxep.model.order;

import com.mxep.model.BaseEntity;
import com.mxep.model.goods.Goods;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity - 订单 商品
 *
 * @author xingkong1221
 * @since 2016-09-19
 */
@Entity
public class OrderGoods extends BaseEntity {

    private static final long serialVersionUID = 7196847332868891737L;

    /**
     * 订单编号
     */
    private Integer orderId;

    /**
     * 订单
     */
    private Order order;

    /**
     *  商品 id
     */
    private Integer goodsId;

    /**
     *  商品
     */
    private Goods goods;

    /**
     *  商品  名称
     */
    private String goodsName;

    /**
     *  缩略图
     */
    private String  thumb;

    /**
     *  数量
     */
    private Integer quantity;

    /**
     *  价格
     */
    private BigDecimal price;

    /**
     *  单位
     */
    private String unit;

    /**
     *   单位
     */
    private String unitName;

    /**
     *   类型，1-商品  2-服务
     */
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
     * 获取订单
     *
     * @return 订单
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Order getOrder() {
        return order;
    }

    /**
     * 设置订单
     *
     * @param order 订单
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "goods_id")
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
