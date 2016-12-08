package com.mxep.model.flash;

import com.mxep.model.BaseSortedEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity - 秒杀
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
public class FlashSale extends BaseSortedEntity {

    private static final long serialVersionUID = -1311029249385216749L;

    /**
     * 秒杀名称
     */
    private String name;

    /**
     * 秒杀商品
     */
    private List<FlashGoods> flashGoods;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 显示标识位
     *
     * @see com.mxep.model.enums.EnumDisplay
     */
    private byte isShow;

    /**
     * 获取抢购名称
     *
     * @return 抢购名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置抢购名称
     *
     * @param name 抢购名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取限时抢购商品
     *
     * @return 限时抢购商品
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flashSale")
    public List<FlashGoods> getFlashGoods() {
        return flashGoods;
    }

    /**
     * 设置限时抢购商品
     *
     * @param flashGoods 限时抢购商品
     */
    public void setFlashGoods(List<FlashGoods> flashGoods) {
        this.flashGoods = flashGoods;
    }

    /**
     * 获取开始时间
     *
     * @return 开始时间
     */
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return 结束时间
     */
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取显示标识位
     *
     * @return 显示标识位
     */
    @Column(name = "is_show")
    public byte getIsShow() {
        return isShow;
    }

    /**
     * 设置显示标识位
     *
     * @param isShow 显示标识位
     */
    public void setIsShow(byte isShow) {
        this.isShow = isShow;
    }

}
