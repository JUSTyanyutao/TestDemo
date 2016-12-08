package com.mxep.model.enums;

/**
 * Enum － 转卖状态
 *
 * @author xingkong1221
 * @since 2016-09-19
 */
public enum EnumResaleStatus {
    OnSale((byte)1, "转卖中"), Waiting((byte)2, "等待到账"), Complete((byte)3, "完成")
    ;
    public byte value;
    public String label;

    EnumResaleStatus(byte value, String label) {
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
