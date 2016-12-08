package com.mxep.model.goods;

import com.mxep.model.BaseEntity;
import com.mxep.model.common.City;
import com.mxep.model.common.CityTemplate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
@Entity
public class ServicePrice extends BaseEntity {

    /**
     *  商品 id
     */
    private Integer serviceId;

    /**
     *  商品
     */
    private Goods goods;

    /**
     *  尺寸
     */
    private String carSize;

    /**
     *  模版 id
     */
    private Integer cityTemplateId;

    /**
     *  城市
     */
    private CityTemplate cityTemplate;

    /**
     *  价格
     */
    private BigDecimal price;

    /**
     *  状态  1可用 0  不可用
     */
    private byte status;


    @Column(name = "service_id")
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getCarSize() {
        return carSize;
    }

    public void setCarSize(String carSize) {
        this.carSize = carSize;
    }





    @Column(name = "city_template_id")
    public Integer getCityTemplateId() {
        return cityTemplateId;
    }

    public void setCityTemplateId(Integer cityTemplateId) {
        this.cityTemplateId = cityTemplateId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_template_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public CityTemplate getCityTemplate() {
        return cityTemplate;
    }

    public void setCityTemplate(CityTemplate cityTemplate) {
        this.cityTemplate = cityTemplate;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
