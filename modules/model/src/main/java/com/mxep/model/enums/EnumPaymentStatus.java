package com.mxep.model.enums;

/**
 * Enum - 支付状态
 *
 * @author xingkong1221
 * @since 2016-09-05
 */
public enum EnumPaymentStatus {

    Overtime((byte)-2, "支付超时"), Failure((byte)-1, "支付失败"), Pending((byte)1, "待支付"),
    Success((byte)2, "支付成功");

    private byte value;
    private String label;

    EnumPaymentStatus(byte value, String label) {
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
