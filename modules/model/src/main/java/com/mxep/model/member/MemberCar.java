package com.mxep.model.member;

import com.mxep.model.BaseEntity;
import com.mxep.model.goods.CarBrand;
import com.mxep.model.goods.CarModel;
import com.mxep.model.goods.CarSeries;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.omg.CORBA.INTERNAL;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
@Entity
public class MemberCar implements Serializable {

    private static final long serialVersionUID = -2108076528223780636L;

    private Integer id;

    /**
     * 会员id
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;


    /**
     *  车的 品牌
     */
    private Integer carBrandId;

    /**
     *  车的品牌
     */
    private CarBrand carBrand;

    /**
     * 车系 id
     */
    private Integer carSeriesId;

    /**
     *  车 系
     */
    private CarSeries carSeries;

    /**
     * 车型 id
     */
    private Integer carModelId;

    /**
     *  车型
     */
    private CarModel carModel;


    /**
     * 备注
     */
    private String remark;

    /**
     * 状态  1可用  0不可用
     */
    private byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     *  更新时间
     */
    private Date updateTime;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "car_brand_id")
    public Integer getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Integer carBrandId) {
        this.carBrandId = carBrandId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_brand_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_series_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public CarSeries getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(CarSeries carSeries) {
        this.carSeries = carSeries;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_model_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",updatable = false,insertable = false)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Column(name = "car_series_id")
    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
    }

    @Column(name = "car_model_id")
    public Integer getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Integer carModelId) {
        this.carModelId = carModelId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
