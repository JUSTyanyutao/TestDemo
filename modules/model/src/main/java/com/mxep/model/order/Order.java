package com.mxep.model.order;

import com.mxep.model.BaseEntity;
import com.mxep.model.common.City;
import com.mxep.model.enums.EnumOrderStatus;
import com.mxep.model.goods.CarModel;
import com.mxep.model.goods.Category;
import com.mxep.model.member.Member;
import com.mxep.model.member.MemberAddress;
import com.mxep.model.member.Shop;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Entity - 订单
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
public class Order extends BaseEntity {

    private static final long serialVersionUID = 7596363157875061178L;


    /**
     * 订单序列号
     */
    private String orderSn;

    /**
     * 会员编号
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;

    /**
     * 收货地址编号
     */
    private Integer memberAddressId;

    /**
     * 会员收送地址
     */
    private MemberAddress memberAddress;

    /**
     * 场地 id
     */
    private Integer shopId;

    /**
     * 场地
     */
    private Shop shop;

    /**
     * 商品 价格
     */
    private BigDecimal goodsPrice;

    /**
     *  原始的 服务价格
     */
    private BigDecimal oldServicePrice;

    /**
     * 服务价格
     */
    private BigDecimal servicePrice;

    /**
     *  运费
     */
    private BigDecimal freightPrice;

    /**
     * 总支付金额
     */
    private BigDecimal totalPrice;

    /**
     * 实际支付价格
     */
    private BigDecimal payPrice;

    /**
     * 平台类型  1：Android 2：IOS 3：微信
     */
    private byte platType;

    /**
     * 类型  订单类型
     * 1: 直接发货  2：到店服务 3.企业订单
     *
     * @see com.mxep.model.enums.EnumOrderType
     */
    private byte orderType;

    /**
     * 上门服务日期
     */
    private String serviceDate;

    /**
     * 上门服务时间区间端
     */
    private String serviceTime;


    /**
     * 备注
     */
    private String remark;


    /**
     * 状态  订单状态
     *
     *
     * @see com.mxep.model.enums.EnumOrderStatus
     */
    private byte status;

    /**
     *  付款时间
     */
    private Date payTime;

    /**
     *   汽车 id
     */
    private Integer carModelId;

    /**
     *  模型
     */
    private CarModel carModel;

    /**
     *  城市 id
     */
    private Integer cityId;

    /**
     *  城市
     */
    private City city;

    /**
     *  1 支付宝  2微信
     */
    private Integer payType;

    /**
     *  分类 id
     */
    private Integer categoryId;

    /**
     *   分类
     */
    private Category category;

    /**
     * 订单日志
     */
    private List<OrderLog> orderLogs;


    /**
     * 订单商品
     */
    private List<OrderGoods> orderGoodsList;

    /**
     * 订单服务
     */
    private List<OrderWorker> orderWorkerList;


//    /**
//     * 支付记录编号
//     */
//    private Integer paymentId;
//
//    /**
//     * 支付记录
//     */
//    private LogPayment payment;


    public BigDecimal getOldServicePrice() {
        return oldServicePrice;
    }

    public void setOldServicePrice(BigDecimal oldServicePrice) {
        this.oldServicePrice = oldServicePrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Column(name = "car_model_id")
    public Integer getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Integer carModelId) {
        this.carModelId = carModelId;
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "shop_id")
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public byte getOrderType() {
        return orderType;
    }

    public void setOrderType(byte orderType) {
        this.orderType = orderType;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    public List<OrderWorker> getOrderWorkerList() {
        return orderWorkerList;
    }

    public void setOrderWorkerList(List<OrderWorker> orderWorkerList) {
        this.orderWorkerList = orderWorkerList;
    }

    /**
     * 获取订单序列号
     *
     * @return 订单序列号
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * 设置订单序列号
     *
     * @param orderSn 订单序列号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取订单状态
     *
     * @return 订单状态
     * @see com.mxep.model.enums.EnumOrderStatus
     */
    public byte getStatus() {
        return status;
    }

    /**
     * 设置订单状态
     *
     * @param status 订单状态
     * @see com.mxep.model.enums.EnumOrderStatus
     */
    public void setStatus(byte status) {
        this.status = status;
    }


    /**
     * 获取价格
     *
     * @return payPrice 价格
     */
    public BigDecimal getPayPrice() {
        return payPrice;
    }

    /**
     * 设置价格
     *
     * @param payPrice 价格
     */
    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

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
     * 获取会员地址编号
     *
     * @return 会员地址编号
     */
    @Column(name = "address_id")
    public Integer getMemberAddressId() {
        return memberAddressId;
    }

    /**
     * 设置会员地址编号
     *
     * @param memberAddressId 会员地址编号
     */
    public void setMemberAddressId(Integer memberAddressId) {
        this.memberAddressId = memberAddressId;
    }

    /**
     * 获取会员地址
     *
     * @return 会员地址
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public MemberAddress getMemberAddress() {
        return memberAddress;
    }

    /**
     * 设置会员地址
     *
     * @param memberAddress 会员地址
     */
    public void setMemberAddress(MemberAddress memberAddress) {
        this.memberAddress = memberAddress;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取订单日志
     *
     * @return 获取订单日志
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    public List<OrderLog> getOrderLogs() {
        return orderLogs;
    }

    /**
     * 设置获取订单日志
     *
     * @param orderLogs 获取订单日志
     */
    public void setOrderLogs(List<OrderLog> orderLogs) {
        this.orderLogs = orderLogs;
    }

    /**
     * 获取平台类型
     *
     * @return 平台类型
     * @see com.mxep.model.enums.EnumPaymentType
     */
    public byte getPlatType() {
        return platType;
    }

    /**
     * 设置平台类型
     *
     * @param platType 平台类型
     * @see com.mxep.model.enums.EnumPlatformType
     */
    public void setPlatType(byte platType) {
        this.platType = platType;
    }

    @Transient
    public String getStatusName()
    {
        return EnumOrderStatus.getStatus(status)!= null?EnumOrderStatus.getStatus(status).getLabel():"";
    }

    @Transient
    public String getPlatTypeName()
    {
        String s = "";
        switch (platType)
        {
            case 1: s="安卓"; break;
            case 2: s="IOS"; break;
            case 3: s="微信"; break;
        }
        return  s;

//        String s = "";
//        if(platType == 1)
//        {
//            s = "安卓";
//        }else if(platType == 2)
//        {
//            s = "IOS";
//        }else if(platType == 3)
//        {
//            s = "微信";
//        }
//        return  s;
    }


}
