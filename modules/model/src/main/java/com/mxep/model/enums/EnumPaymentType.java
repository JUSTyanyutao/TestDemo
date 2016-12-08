package com.mxep.model.enums;

/**
 * Enum - 支付记录类型
 *
 * @author xingkong1221
 * @since 2016-09-05
 */
public enum EnumPaymentType {

    Recharge((byte)1, "支付宝"), Order((byte)2, "微信支付");

    private byte value;
    private String label;

    EnumPaymentType(byte value, String label) {
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
