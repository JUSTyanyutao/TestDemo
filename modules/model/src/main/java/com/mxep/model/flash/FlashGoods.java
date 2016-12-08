package com.mxep.model.flash;

import com.mxep.model.BaseSortedEntity;
import com.mxep.model.goods.Goods;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Entity - 秒杀商品
 *
 * @author xingkong1221
 * @since 2015-11-30
 */
@Entity
public class FlashGoods extends BaseSortedEntity {

    private static final long serialVersionUID = 2616340134559677016L;

    /**
     * 商品编号
     */
    private Integer goodsId;

    /**
     * 商品
     */
    private Goods goods;

    /**
     * 列表图片
     */
    private String thumb;

    /**
     * 抢购价（梦想币）
     */
    private BigDecimal flashPrice;

    /**
     * 预支付(梦想币)
     */
    private BigDecimal prepay;

    /**
     * 抢购库存
     */
    private Integer stock;

    /**
     * 限时抢购
     */
    private FlashSale flashSale;

    /**
     * 显示标识位
     *
     * @see com.mxep.model.enums.EnumDisplay
     */
    private byte isDisplay;

    /**
     * 时间段
     *
     * @see com.mxep.model.enums.EnumFlashSaleSection
     */
    private List<String> sections;

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
     * 获取列表缩略图
     *
     * @return 列表缩略图
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * 设置列表缩略图
     *
     * @param thumb 列表缩略图
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     * 获取秒杀价格
     *
     * @return 秒杀价格
     */
    @Column(name = "flash_price")
    public BigDecimal getFlashPrice() {
        return flashPrice;
    }

    /**
     * 设置秒杀价格
     *
     * @param flashPrice 秒杀价格
     */
    public void setFlashPrice(BigDecimal flashPrice) {
        this.flashPrice = flashPrice;
    }

    /**
     * 获取预支付价格
     *
     * @return 预支付价格
     */
    public BigDecimal getPrepay() {
        return prepay;
    }

    /**
     * 设置预支付价格
     *
     * @param prepay 预支付价格
     */
    public void setPrepay(BigDecimal prepay) {
        this.prepay = prepay;
    }

    /**
     * 获取库存
     *
     * @return 库存
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置库存
     *
     * @param stock 库存
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取秒杀活动
     *
     * @return 秒杀活动
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flash_sale_id")
    public FlashSale getFlashSale() {
        return flashSale;
    }

    /**
     * 设置秒杀活动
     *
     * @param flashSale 秒杀活动
     */
    public void setFlashSale(FlashSale flashSale) {
        this.flashSale = flashSale;
    }

    /**
     * 获取显示标识位（0:隐藏  1:显示）
     *
     * @see com.mxep.model.enums.EnumDisplay
     * @return 显示标识位
     */
    public byte getIsDisplay() {
        return isDisplay;
    }

    /**
     * 设置显示标识位（0:隐藏  1:显示）
     *
     * @see com.mxep.model.enums.EnumDisplay
     * @param isDisplay 显示标识位
     */
    public void setIsDisplay(byte isDisplay) {
        this.isDisplay = isDisplay;
    }

    /**
     * 获取时间段
     *
     * @return 时间段
     */

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "mx_flash_goods_section", joinColumns = @JoinColumn(name = "flash_goods_id"))
    public List<String> getSections() {
        return sections;
    }

    /**
     * 设置时间段
     *
     * @param sections 时间段
     */
    public void setSections(List<String> sections) {
        this.sections = sections;
    }
}
