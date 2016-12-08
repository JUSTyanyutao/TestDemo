package com.mxep.model.enums;

/**
 * Enum - 转台平台类型
 * @author xingkong1221
 * @since 2016-09-19
 */
public enum EnumTransferPlatform {
    Alipay((byte)1, "支付宝")
    ;
    public byte value;
    public String label;

    EnumTransferPlatform(byte value, String label) {
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
