package com.mxep.model.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mxep.model.BaseEntity;
import com.mxep.model.base.WxUser;
import com.mxep.model.common.City;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Entity - 会员
 *
 * @author yyt
 * @since 2016/09/30
 */
@Entity
public class Member extends BaseEntity {

    private static final long serialVersionUID = -5515404539580470078L;

    /**
     * 积分
     */
    private Integer points = 0;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 角色
     *
     * @see com.mxep.model.enums.EnumMembeRole
     */
    private byte role;

    /**
     * 状态
     *
     * @see com.mxep.model.enums.EnumMemberStatus
     */
    private byte status;


    /**
     * 会员信息
     */
    private MemberProfile profile;


    /**
     * 收货地址
     */
    private List<MemberAddress> memberAddresses;

    /**
     *  会员 车
     */
    private List<MemberCar> memberCars;

    /**
     * 场地
     */
    private List<Shop> shopList;


    /**
     * 微信openid
     */
    private String openId;

    /**
     * 微信用户
     */
    private WxUser wxUser;

    /**
     *  区域经理  对应  区域
     */
    private List<City> cityList;

    /**
     *  场地 技师 关系表
     */
    private List<ShopWorker> shopWorkerList;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
    public List<ShopWorker> getShopWorkerList() {
        return shopWorkerList;
    }

    public void setShopWorkerList(List<ShopWorker> shopWorkerList) {
        this.shopWorkerList = shopWorkerList;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dm_manager_city",
            joinColumns = {@JoinColumn(name = "manager_id")},
            inverseJoinColumns = {@JoinColumn(name = "city_id")})
    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }


    /**
     * 获取积分
     *
     * @return 积分
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * 设置积分
     *
     * @param points 积分
     */
    public void setPoints(Integer points) {
        this.points = points;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
     * 获取头像地址
     *
     * @return 头像地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像地址
     *
     * @param avatar 头像地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取密码
     *
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取密码盐
     *
     * @return 密码盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置密码盐
     *
     * @param salt 密码盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取注册时间
     *
     * @return 注册时间
     */
    @Column(name = "register_time")
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取会员状态
     *
     * @see com.mxep.model.enums.EnumMemberStatus
     * @return 会员状态
     */
    public byte getStatus() {
        return status;
    }

    /**
     * 设置会员状态
     *
     * @see com.mxep.model.enums.EnumMemberStatus
     * @param status 会员状态
     */
    public void setStatus(byte status) {
        this.status = status;
    }

    /**
     * 获取会员信息
     *
     * @return 会员信息
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    @NotFound(action = NotFoundAction.IGNORE)
    public MemberProfile getProfile() {
        return profile;
    }

    /**
     * 设置会员信息
     *
     * @param profile 会员信息
     */
    public void setProfile(MemberProfile profile) {
        this.profile = profile;
    }

    /**
     * 获取收货地址
     *
     * @return 收货地址
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    public List<MemberAddress> getMemberAddresses() {
        return memberAddresses;
    }

    /**
     * 设置收货地址
     *
     * @param memberAddresses 收货地址
     */
    public void setMemberAddresses(List<MemberAddress> memberAddresses) {
        this.memberAddresses = memberAddresses;
    }


    /**
     * 获取微信open_id
     *
     * @return 微信open_id
     */
    @Column(name = "openid")
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置微信open_id
     *
     * @param openId 微信open_id
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取微信用户信息
     *
     * @return 微信用户信息
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "openid", insertable = false, updatable = false, referencedColumnName = "openid")
    public WxUser getWxUser() {
        return wxUser;
    }

    /**
     * 设置微信用户信息
     *
     * @param wxUser 微信用户信息
     */
    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }


    /**
     *  用户角色
     * @return
     */
    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    public List<MemberCar> getMemberCars() {
        return memberCars;
    }

    public void setMemberCars(List<MemberCar> memberCars) {
        this.memberCars = memberCars;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dm_member_shop",
            joinColumns = {@JoinColumn(name = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "shop_id")})
    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

}