package com.mxep.model.enums;

/**
 * Enum - 显示标识
 *
 * @author xingkong1221
 * @since 2016-08-30
 */
public enum EnumDisplay {
    Show((byte)1, "显示"), Hidden((byte)0, "隐藏")
    ;
    public byte status;
    public String label;

    EnumDisplay(byte status, String label) {
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
