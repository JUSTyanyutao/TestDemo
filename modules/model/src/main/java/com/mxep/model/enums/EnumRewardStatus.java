package com.mxep.model.enums;

/**
 * Enum - 中奖状态
 *
 * @author xingkong1221
 * @since 2016-09-19
 */
public enum EnumRewardStatus {
    Win((byte)1, "中奖"), NotWin((byte)0, "未中奖")
    ;
    public byte value;
    public String label;

    EnumRewardStatus(byte value, String label) {
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
