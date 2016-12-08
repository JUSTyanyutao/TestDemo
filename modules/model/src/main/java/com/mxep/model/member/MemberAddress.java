package com.mxep.model.member;

import com.mxep.model.BaseEntity;
import com.mxep.model.base.Area;
import com.mxep.model.common.City;
import com.mxep.model.common.District;
import com.mxep.model.common.Province;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Entity - 会员收货地址
 *
 * @author xingkong1221
 * @since  2015-11-16
 */
@Entity
public class MemberAddress extends BaseEntity {

    private static final long serialVersionUID = -2108076528223780636L;

    /**
     * 会员id
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 收获地址（街道地址）
     */
    private String street;

    /**
     *  省份 id
     */
    private Integer provinceId;

    /**
     * 省
     */
    private Province province;

    /**
     *  城市 id
     */
    private Integer cityId;

    /**
     * 市
     */
    private City city;

    /**
     * 区  名称
     */
    private Integer districtId;

    /**
     *  区
     */
    private District district;


    /**
     * 默认地址
     *
     * 1:默认地址  0:非默认地址
     */
    private byte isDefault;


    /**
     *    经度
     */
    private String lng;


    /**
     *    纬度
     */
    private String lat;




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
    @NotFound(action = NotFoundAction.IGNORE)
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
     * 获取收货人
     *
     * @return 收货人
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置收货人
     *
     * @param consignee 收货人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 获取手机号码
     *
     * @return 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取街道地址
     *
     * @return 街道地址
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置街道地址
     *
     * @param street 街道地址
     */
    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "province_id")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Column(name = "city_id")
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Column(name = "district_id")
    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
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

    /**
     * 获取默认地址标识
     * 0:非默认地址  1:默认地址
     *
     * @see com.mxep.model.enums.EnumDefault
     * @return 默认地址标识
     */
    @Column(name = "is_default")
    public byte getIsDefault() {
        return isDefault;
    }

    /**
     * 设置默认地址标识
     *
     * @see com.mxep.model.enums.EnumDefault
     * @param isDefault 默认地址标识
     */
    public void setIsDefault(byte isDefault) {
        this.isDefault = isDefault;
    }
}
