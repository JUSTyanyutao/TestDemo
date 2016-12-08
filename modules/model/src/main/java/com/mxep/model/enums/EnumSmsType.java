package com.mxep.model.enums;

/**
 * Enum - 短信类型
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumSmsType {
    Notification((byte)1, "通知类短信"), Verification((byte)2, "验证类短信")
    ;
    public byte value;
    public String label;

    EnumSmsType(byte value, String label) {
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
