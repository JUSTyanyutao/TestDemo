package com.mxep.model.enums;

/**
 * Enum - 默认标识位
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumDefault {
    Yes((byte) 1, "是"), No((byte)0, "否")
    ;
    public byte status;
    public String label;

    EnumDefault(byte status, String label) {
        this.status = status;
        this.label = label;
    }

    public byte getStatus() {
        return status;
    }

    public String getLabel() {
        return label;
    }
}
