package com.mxep.model.member;

import com.mxep.model.BaseEntity;
import com.mxep.model.common.City;
import com.mxep.model.goods.CarBrand;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 */
@Entity
@Where(clause = "flag = 1")
public class Shop extends BaseEntity {

    /**
     *  公司名称
     */
    private String name;

    /**
     *  联系人
     */
    private String contact;

    /**
     * 手机号码
     */
    private String phone;

    /**
     *   经度
     */
    private String lng;

    /**
     *  纬度
     */
    private String lat;

    /**
     *  地址
     */
    private String address;

    /**
     *   类型  1 4s店   2 街边店铺
     */
    private byte type;

    /**
     *   图片
     */
    private String pictures;

    /**
     *  状态   0待审核  1已审核 2反驳
     */
    private byte status;

    /**
     *   车的品牌
     */
    private Integer carBrandId;

    /**
     *  车的品牌
     */
    private CarBrand carBrand;


    /**
     * 备注
     */
    private String remark;


    /**
     * 服务上午时间
     * @see com.mxep.model.enums.EnumAttribute
     */
    private String serviceTimeAm;


    /**
     * 服务下午时间
     */
    private String serviceTimePm;


    /**
     *  城市 id
     */
    private Integer cityId;


    /**
     *  城市
     */
    private City city;

    /**
     *   场地 和 技师 关系
     */
    private List<ShopWorker> shopWorkerList;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "shop")
    public List<ShopWorker> getShopWorkerList() {
        return shopWorkerList;
    }

    public void setShopWorkerList(List<ShopWorker> shopWorkerList) {
        this.shopWorkerList = shopWorkerList;
    }

    //    /**
//     *  会员场地
//     */
//    private List<MemberShop> memberShopList;


    @Column(name = "car_brand_id")
    public Integer getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Integer carBrandId) {
        this.carBrandId = carBrandId;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_brand_id",insertable = false,updatable = false)
    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public String getServiceTimeAm() {
        return serviceTimeAm;
    }

    public void setServiceTimeAm(String serviceTimeAm) {
        this.serviceTimeAm = serviceTimeAm;
    }

    public String getServiceTimePm() {
        return serviceTimePm;
    }

    public void setServiceTimePm(String serviceTimePm) {
        this.serviceTimePm = serviceTimePm;
    }

    @Column(name = "city_id")
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id",updatable = false,insertable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "shop")
//    public List<MemberShop> getMemberShopList() {
//        return memberShopList;
//    }
//
//    public void setMemberShopList(List<MemberShop> memberShopList) {
//        this.memberShopList = memberShopList;
//    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }


}
