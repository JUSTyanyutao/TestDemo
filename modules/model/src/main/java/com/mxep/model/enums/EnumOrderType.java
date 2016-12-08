package com.mxep.model.enums;

/**
 * Enum - 订单类型
 *
 * @author xingkong1221
 * @since 2016-09-05
 */
public enum EnumOrderType {
    Delivery((byte)1, "直接发货"), Service((byte)2, "到店服务"), Shop((byte)3, "企业订单")
    ;
    public byte value;
    public String label;

    EnumOrderType(byte value, String label) {
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
