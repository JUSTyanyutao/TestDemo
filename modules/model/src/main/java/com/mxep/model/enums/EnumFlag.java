package com.mxep.model.enums;

/**
 * Enum - 标识位
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumFlag {
    Normal((byte) 1, "正常"), Deleted((byte)-1, "删除")
    ;

    public byte value;
    public String label;

    EnumFlag(byte value, String label) {
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
