package com.mxep.model.enums;

/**
 * Enum - 支付记录来源
 *
 * @author xingkong1221
 * @since 2016-09-05
 */
public enum EnumPaymentSource {

    Balance((byte)1, "余额"), Alipay((byte)2, "支付宝"), MicroMessage((byte)3, "微信支付"), Cash((byte)4, "现金");

    public byte value;
    public String label;

    EnumPaymentSource(byte value, String label) {
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
