package com.mxep.model.enums;

/**
 * Enum - 优惠券状态
 *
 * @author xingkong1221
 * @since 2016-09-02
 */
public enum EnumCouponStatus {

    Expired((byte)-2, "过期"), InValid((byte)-1, "无效"), PendingActivate((byte)0, "待激活"), Activated((byte)1, "已激活"), Used((byte)2, "已使用");

    public byte value;
    public String label;

    EnumCouponStatus(byte value, String label) {
        this.value = value;
        this.label = label;
    }

    public byte getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
