package com.mxep.model.enums;

/**
 * Enum - 短信通道
 *
 * @author xingkong1221
 * @since 2016-09-09
 */
public enum EnumSmsChannel {
    YTX((byte)1, "云通讯")
    ;
    public byte value;
    public String label;

    EnumSmsChannel(byte value, String label) {
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
