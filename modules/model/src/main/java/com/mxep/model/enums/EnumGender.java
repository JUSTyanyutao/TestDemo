package com.mxep.model.enums;

/**
 * Enum - 性别
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumGender {

    Unknown((byte)0, "未知"), Male((byte)1, "男性"), Female((byte)2, "女性")

    ;private byte value;
    private String label;

    EnumGender(byte value, String label) {
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
